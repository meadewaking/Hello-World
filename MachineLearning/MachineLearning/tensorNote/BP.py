import tensorflow as tf
import numpy as np
BATCH_SIZE = 8
seed = 23455

#基于seed产生随机数
rng = np.random.RandomState(seed)
#随机数返回32行2列的矩阵 表示32组 体积和重量 作为输入数据集
X = rng.rand(32,2)
#从X这个32行2列的矩阵中 取出一行 判断如果和小于1 给Y赋值1 如果和不小于1 给Y赋值0
#作为输入数据集的标签
Y = [[int(x0 + x1 < 1)] for (x0,x1) in X]
print("X：\n",X)
print("Y：\n",Y)

#搭建计算图
x = tf.placeholder(tf.float32, shape = (None,2))
y_ = tf.placeholder(tf.float32, shape = (None,1))

w1 = tf.Variable(tf.random_normal([2,3],stddev = 1,seed = 1))
w2 = tf.Variable(tf.random_normal([3,1],stddev = 1,seed = 1))

a = tf.matmul(x,w1)
y = tf.matmul(a,w2)

#定义损失函数与优化方法
loss = tf.reduce_mean(tf.square(y-y_))
train_step = tf.train.GradientDescentOptimizer(0.001).minimize(loss)    #本次使用梯度下降方法（学习率为0.001）
#train_step = tf.train.MomentumOptimizer(0.001,0.9).minimize(loss)
#train_step = tf.train.AdamOptimizer(0.001).minimize(loss)

#开始训练
with tf.Session() as sess:
    init_op = tf.global_variables_initializer()
    sess.run(init_op)
    #输出初始参数值
    print("w1:\n",sess.run(w1))
    print("w2:\n",sess.run(w2))
    print()

    #训练
    STEPS = 3000        #迭代3000次
    for i in range(STEPS):
        start = (i*BATCH_SIZE) % 32
        end = start + BATCH_SIZE
        sess.run(train_step,feed_dict = {x:X[start:end], y_:Y[start:end]})
        if i % 500 == 0:
            total_loss = sess.run(loss,feed_dict = {x:X,y_:Y})  #输出每500次训练后的损失值
            print("after %d training steps，loss on all data is %g" % (i,total_loss))
    print("\nw1:\n",sess.run(w1))