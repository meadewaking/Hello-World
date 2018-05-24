import tensorflow as tf
import numpy as np

BATCH_SIZE = 8
SEED = 23455

rdm = np.random.RandomState(SEED)
X = rdm.rand(32,2)
Y_ = [[X1 + X2 + (rdm.rand()/10.0-0.05)] for (X1,X2) in X]  #Y_ = X1 + X2 加入0.05的噪点

#前向传播，层数1层，总参数2X1 + 1 = 3
x = tf.placeholder(tf.float32,shape = (None,2))
y_ = tf.placeholder(tf.float32,shape = (None,1))
w1 = tf.Variable(tf.random_normal([2,1],stddev = 1,seed = 1))
y = tf.matmul(x,w1)

#反向传播
loss_mse = tf.reduce_mean(tf.square(y-y_))  #损失函数使用均方差
train_step = tf.train.GradientDescentOptimizer(0.001).minimize(loss_mse)    #梯度下降

#生成会话，开启训练
with tf.Session() as sess:
    init_op = tf.global_variables_initializer()
    sess.run(init_op)
    STEPS = 20000
    for i in range(STEPS):
        start = (i * BATCH_SIZE)%32
        end = (i * BATCH_SIZE)%32 + BATCH_SIZE
        sess.run(train_step,feed_dict = {x:X[start:end],y_:Y_[start:end]})
        if i%500 == 0:
            print("经过%d论训练后，现在参数w1为:"%(i))
            print(sess.run(w1))

    print("最终结果为:",sess.run(w1))