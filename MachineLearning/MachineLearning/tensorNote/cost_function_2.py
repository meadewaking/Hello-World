import tensorflow as tf
import numpy as np

BATCH_SIZE = 8
SEED = 23455
COST = 1
PROFIT = 9

rdm = np.random.RandomState(SEED)
X = rdm.rand(32,2)
Y_ = [[X1 + X2 + (rdm.rand()/10.0-0.05)] for (X1,X2) in X]  #Y_ = X1 + X2 加入0.05的噪点

#前向传播，层数1层，总参数2X1 + 1 = 3
x = tf.placeholder(tf.float32,shape = (None,2))
y_ = tf.placeholder(tf.float32,shape = (None,1))
w1 = tf.Variable(tf.random_normal([2,1],stddev = 1,seed = 1))
y = tf.matmul(x,w1)

#反向传播
#划分出利润和成本，分别定义不同的损失函数,选择最小损失
loss = tf.reduce_sum(tf.where(tf.greater(y,y_),(y - y_)*COST,(y_ - y)*PROFIT))  #greater函数相当于三目运算符
#ce = -tf.reduce_mean(y_*tf.log(tf.clip_by_value(y,1e-12,1.0)))      #交叉熵定义了两个概率分布的远近clip_by_value函数的作用（输入值，最小值，最大值），规定了输入值的界限
#ce = tf.nn.sparse_softmax_cross_entropy_with_logits(logits = y,labels = tf.arg_max(y_,1))
#cem = tf.reduce_mean(ce)        #对于n分类问题使用softmax函数满足概率分布要求（范围0到1，和为1），上述函数可以softmax处理后直接求出交叉熵
train_step = tf.train.GradientDescentOptimizer(0.001).minimize(loss)    #梯度下降

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
