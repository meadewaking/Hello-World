import tensorflow as tf

LEARNING_RATE_BASE = 0.2   #初始学习率
LEARNING_RATE_DECAY = 0.99  #学习率衰减率
LEARNING_RATE_STEP = 1      #学习率更新频率

global_step = tf.Variable(0,trainable = False)      #BATCH_SIZE计数器，初始为0不可被训练
#定义指数下降学习率，staircase为true时，会对g_s/l_s取整，此时学习率会阶梯型下降，相反，学习率则会平滑下降
learning_rate = tf.train.exponential_decay(LEARNING_RATE_BASE,global_step,LEARNING_RATE_STEP,LEARNING_RATE_DECAY,staircase = True)
w = tf.Variable(tf.constant(5,dtype = tf.float32))
loss = tf.square(w + 1)
#train_step = tf.train.GradientDescentOptimizer(0.2).minimize(loss)  #合适的学习率，大约在15轮之后收敛到理想状态
#train_step = tf.train.GradientDescentOptimizer(1).minimize(loss)    #过大的学习率，使得loss值开始震荡，不收敛
#train_step = tf.train.GradientDescentOptimizer(1.2).minimize(loss)  #学习率特别大，loss值开始发散
#train_step = tf.train.GradientDescentOptimizer(0.0001).minimize(loss)   #学习率过小，loss值下降十分缓慢
train_step = tf.train.GradientDescentOptimizer(learning_rate).minimize(loss,global_step = global_step)

with tf.Session() as sess:
    init_op = tf.global_variables_initializer()
    sess.run(init_op)
    for i in range(40):
        sess.run(train_step)
        w_val = sess.run(w)
        loss_val = sess.run(loss)
        learning_rate_val = sess.run(learning_rate)
        print("第%d轮，w值为：%f，loss值为%f，学习率为%f"%(i,w_val,loss_val,learning_rate_val))
'''
学习率指数衰减只能帮助一定程度上放宽学习率的合适范围，对原本过大的学习率并无太大作用
学习率 = 基础学习率 * 衰减率 * 轮数/更新频数
'''