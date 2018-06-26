import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

STEPS = 40000
BATCH_SIZE = 30
LEARNING_RATE_BASE = 0.001
LEARNING_RATE_DECAY = 0.999
REGULARIZER = 0.01
LEARNING_RATE_STEP = 10
seed = 2

rdm = np.random.RandomState(seed)   
X = rdm.randn(300,2)    #使用随机种子产生随机数，返回300X2的矩阵，表示坐标点
Y_ = [int(x0 * x0 + x1 * x1 < 2) for (x0,x1) in X]  #依次判断坐标平方和，小于2给y赋值1否则赋值0
Y_c = [['red' if y else 'blue'] for y in Y_]    #1为红色，0为蓝色
X = np.vstack(X).reshape(-1,2)
Y_ = np.vstack(Y_).reshape(-1,1)

def get_weight(shape,regularizer):
    w = tf.Variable(tf.random_normal(shape),dtype = tf.float32)
    tf.add_to_collection('losses',tf.contrib.layers.l2_regularizer(regularizer)(w)) #使用L2正则即平方正则
    return w

def get_bias(shape):
    b = tf.Variable(tf.constant(0.01,shape = shape))
    return b

def forward(x,regularizer):
    w1 = get_weight([2,11],0.01)
    b1 = get_bias([11])
    y1 = tf.nn.relu(tf.matmul(x,w1)+b1)
    w2 = get_weight([11,1],0.01)
    b2 = get_bias([1])
    y = tf.matmul(y1,w2) + b2       #输出单元不使用激活函数
    return y

def backward():
    x = tf.placeholder(tf.float32,shape = (None,2))
    y_ = tf.placeholder(tf.float32,shape = (None,1))
    y = forward(x,REGULARIZER)
    global_step = tf.Variable(0,trainable = False)
    learning_rate = tf.train.exponential_decay(LEARNING_RATE_BASE,global_step,LEARNING_RATE_STEP,LEARNING_RATE_DECAY,staircase = True)

    loss_mse = tf.reduce_mean(tf.square(y-y_))
    loss_total = loss_mse + tf.add_n(tf.get_collection('losses'))

    train_step = tf.train.AdamOptimizer(learning_rate).minimize(loss_total)

    with tf.Session() as sess:
        init_op = tf.global_variables_initializer()
        sess.run(init_op)
        for i in range(STEPS):
            start = (i*BATCH_SIZE) % 300
            end = start + BATCH_SIZE
            sess.run(train_step,feed_dict = {x:X[start:end],y_:Y_[start:end]})
            if i%2000 == 0:
                loss_v = sess.run(loss_total,feed_dict = {x:X,y_:Y_})
                print("第%d轮，loss为%f"%(i,loss_v))

        xx,yy = np.mgrid[-3:3:.01,-3:3:.01]     #网格坐标3X3步长0.01
        grid = np.c_[xx.ravel(),yy.ravel()]     #转换为一维并组合成网格坐标
        probs = sess.run(y,feed_dict = {x:grid})    #获取训练后的神经网络给出的值
        probs = probs.reshape(xx.shape)

    plt.scatter(X[:,0],X[:,1],c = np.squeeze(Y_c))
    plt.contour(xx,yy,probs,levels = [.5])      #绘制分类边界
    plt.show()

backward()