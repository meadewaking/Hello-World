import tensorflow as tf         #加载TensorFlow
import cv2              #加载OpenCV
import sys
sys.path.append("game/")        #引入game文件夹到环境变量
import wrapped_flappy_bird as game      #加载游戏
import random
import numpy as np          
from collections import deque           #加载双端队列

#超参数设置
GAME = 'bird'   #游戏名称
ACTIONS = 2     #操作状态数目
GAMMA = 0.99    # decay rate of past observations
OBSERVE = 100000.   #设置观察期迭代次数为10000次
EXPLORE = 2000000.  #探索期训练次数2000000次
FINAL_EPSILON = 0.0001  #ε最终最小值为0.0001
INITIAL_EPSILON = 0 #0.0001 #ε初始值为0.1，达到预期训练结果后将随机值置为0
REPLAY_MEMORY = 50000   #reply memory为容量为50000
BATCH = 32  #设置每次网络参数更新时用的样本数目 
FRAME_PER_ACTION = 1    #每1帧图像进行一次动作

def weight_variable(shape):             #建立形状为shape的张量
    initial = tf.truncated_normal(shape, stddev = 0.01) #初始化数值服从正态分布，方差为0.01
    return tf.Variable(initial)

def bias_variable(shape):           #生成偏置项，初始值为0.01
    initial = tf.constant(0.01, shape = shape)
    return tf.Variable(initial)

def conv2d(x, W, stride):       #定义卷积操作（卷积核W在x上卷积，strides为移动步长，padding为卷积模式）
    return tf.nn.conv2d(x, W, strides = [1, stride, stride, 1], padding = "SAME")

def max_pool_2x2(x):        #定义池化操作大小为2X2，stride步长为2
    return tf.nn.max_pool(x, ksize = [1, 2, 2, 1], strides = [1, 2, 2, 1], padding = "SAME")

def createNetwork():        #构建深度卷积神经网络

    W_conv1 = weight_variable([8, 8, 4, 32])    #第一卷积层
    b_conv1 = bias_variable([32])

    W_conv2 = weight_variable([4, 4, 32, 64])   #第二卷积层
    b_conv2 = bias_variable([64])

    W_conv3 = weight_variable([3, 3, 64, 64])   #第三卷积层
    b_conv3 = bias_variable([64])

    W_fc1 = weight_variable([1600, 512])        #第一全连接层
    b_fc1 = bias_variable([512])

    W_fc2 = weight_variable([512, ACTIONS])     #第二全连接层
    b_fc2 = bias_variable([ACTIONS])

    
    s = tf.placeholder("float", [None, 80, 80, 4])  #总输入层

    
    h_conv1 = tf.nn.relu(conv2d(s, W_conv1, 4) + b_conv1)       #输入层第一次卷积
    h_pool1 = max_pool_2x2(h_conv1)     #第一卷积层池化

    h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2, 2) + b_conv2)     #第二次卷积
  
    h_conv3 = tf.nn.relu(conv2d(h_conv2, W_conv3, 1) + b_conv3)     #第三次卷积

    h_conv3_flat = tf.reshape(h_conv3, [-1, 1600])      #数据重整（可以视为神经网络输入层，即第一神经层）

    h_fc1 = tf.nn.relu(tf.matmul(h_conv3_flat, W_fc1) + b_fc1)      #隐藏层（1600X512）

    readout = tf.matmul(h_fc1, W_fc2) + b_fc2       #输出层

    return s, readout, h_fc1 

def trainNetwork(s, readout, h_fc1, sess):      #训练神经网络
    #定义损失函数
    a = tf.placeholder("float", [None, ACTIONS])		#创建占位符
    y = tf.placeholder("float", [None])
    readout_action = tf.reduce_sum(tf.multiply(readout, a), reduction_indices=1)
    cost = tf.reduce_mean(tf.square(y - readout_action))
    train_step = tf.train.AdamOptimizer(1e-6).minimize(cost)

    #打开游戏模拟器，实时显示训练过程
    game_state = game.GameState()

    #生成双端队列存放replay memory
    D = deque()

    #获取游戏初始状态，设置动作为不执行跳跃
    do_nothing = np.zeros(ACTIONS)
    do_nothing[0] = 1
    x_t, r_0, terminal = game_state.frame_step(do_nothing)

    #使用OpenCV对游戏图像进行预处理，将游戏画面转成黑白图
    x_t = cv2.cvtColor(cv2.resize(x_t, (80, 80)), cv2.COLOR_BGR2GRAY)
    ret, x_t = cv2.threshold(x_t,1,255,cv2.THRESH_BINARY)
    s_t = np.stack((x_t, x_t, x_t, x_t), axis=2)

    #加载训练数据
    saver = tf.train.Saver()		#初始保存记载器
    sess.run(tf.initialize_all_variables())		#初始化所有参数
    checkpoint = tf.train.get_checkpoint_state("saved_networks")
    if checkpoint and checkpoint.model_checkpoint_path:			#判断加载是否成功
        saver.restore(sess, checkpoint.model_checkpoint_path)
        print("Successfully loaded:", checkpoint.model_checkpoint_path)
    else:
        print("Could not find old network weights")

    #开始训练
    epsilon = INITIAL_EPSILON		#初始化ε值为初始值
    t = 0
    while "flappy bird" != "angry bird":		#死循环，不过不同于flappy bird ，angry bird依然是人工智能难解问题（时间2017.9.5）
        #使用ε贪心策略选择动作
        readout_t = readout.eval(feed_dict={s : [s_t]})[0]
        a_t = np.zeros([ACTIONS])
        action_index = 0        #初始化全0[点击，不点击]
        if t % FRAME_PER_ACTION == 0:       #每一帧采取一次动作
            #执行随机动作
            if random.random() <= epsilon:          #随着ε的减小出现随机动作的概率将会减小
                print("----------Random Action----------")
                action_index = random.randrange(ACTIONS)    #选取随机动作（跳跃或者不做动作）
                a_t[random.randrange(ACTIONS)] = 1      #将随机动作添加到动作列表中
            else:
                #由神经网络执行动作
                action_index = np.argmax(readout_t)
                a_t[action_index] = 1       #将执行动作添加到动作列表中
        else:
            a_t[0] = 1      #不做动作

        #不断降低ε值，减少随机动作
        if epsilon > FINAL_EPSILON and t > OBSERVE:
            epsilon -= (INITIAL_EPSILON - FINAL_EPSILON) / EXPLORE

        #执行选择动作，并获得下一状态及回报
        x_t1_colored, r_t, terminal = game_state.frame_step(a_t)
        x_t1 = cv2.cvtColor(cv2.resize(x_t1_colored, (80, 80)), cv2.COLOR_BGR2GRAY)		#画面由RGB表示转成灰度表示(即黑白画面)
        ret, x_t1 = cv2.threshold(x_t1, 1, 255, cv2.THRESH_BINARY)
        x_t1 = np.reshape(x_t1, (80, 80, 1))		#重整画面比例
        s_t1 = np.append(x_t1, s_t[:, :, :3], axis=2)

        #将状态转移存储到D中，用于更新参数时采样
        D.append((s_t, a_t, r_t, s_t1, terminal))
        if len(D) > REPLAY_MEMORY:		#在超过最大reply_memory时舍弃旧的参数
            D.popleft()

        #观察期之后才会对网络参数更新
        if t > OBSERVE:
            #从D中采样，用于参数更新
            minibatch = random.sample(D, BATCH)

            #将当前状态，采取的动作，获得的回报，下一状态存入，分组存放
            s_j_batch = [d[0] for d in minibatch]
            a_batch = [d[1] for d in minibatch]
            r_batch = [d[2] for d in minibatch]
            s_j1_batch = [d[3] for d in minibatch]
            #计算Q（s,a）值
            y_batch = []
            readout_j1_batch = readout.eval(feed_dict = {s : s_j1_batch})
            for i in range(0, len(minibatch)):
                terminal = minibatch[i][4]
                #游戏结束则只有反馈值
                if terminal:
                    y_batch.append(r_batch[i])
                else:
                    y_batch.append(r_batch[i] + GAMMA * np.max(readout_j1_batch[i]))

            #使用梯度下降更新参数
            train_step.run(feed_dict = {
                y : y_batch,
                a : a_batch,
                s : s_j_batch}
            )

        #改变状态用于下一次训练
        s_t = s_t1
        t += 1

        #每30000次训练保存一次参数
        if t % 30000 == 0:
            saver.save(sess, 'saved_networks/' + GAME + '-dqn', global_step = t)

        #打印游戏信息
        state = ""
        if t <= OBSERVE:
            state = "observe"
        elif t > OBSERVE and t <= OBSERVE + EXPLORE:
            state = "explore"
        else:
            state = "train"

        print("TIMESTEP", t, "/ STATE", state, \
            "/ EPSILON", epsilon, "/ ACTION", action_index, "/ REWARD", r_t, \
            "/ Q_MAX %e" % np.max(readout_t))

def playGame():
    sess = tf.InteractiveSession()      #建立session开启训练过程，tensorflow以session为控制方法
    s, readout, h_fc1 = createNetwork()     #创建深度卷积神经网络（返回输入（80X80X4），输出（1X2），隐藏层输出（1X512））
    trainNetwork(s, readout, h_fc1, sess)

def main():
    playGame()

if __name__ == "__main__":
    main()
