import tensorflow as tf
'''
w = tf.Variable(tf.random_normal([2,3],stddev = 2,mean = 0,seed = 1))   #给权重赋初值（正态分布，2X3矩阵，标准差为2，均值为0，随机种子为1（如果不改变随机种子则每一次初值相同））
tf.truncated_normal()   #去掉过大偏离点的正态分布
tf.random_uniform() #平均分布
tf.zeros()  #全0数组
tf.ones()   #全1数组
tf.fill()   #全定值数组
tf.constant()   #直接给值
'''

#定义输入和参数
#x = tf.constant([[0.7,0.5]])
x = tf.placeholder(tf.float32,shape = (1,2))    #使用placeholder占位，定义数据类型和形状
w1 = tf.Variable(tf.random_normal([2,3],stddev = 1,seed = 1))
w2 = tf.Variable(tf.random_normal([3,1],stddev = 1,seed = 1))
#定义前向传播过程
a = tf.matmul(x,w1)
y = tf.matmul(a,w2)
#使用会话计算结果
with tf.Session() as sess:
    init_op = tf.global_variables_initializer()
    sess.run(init_op)
    print(sess.run(y,feed_dict = {x: [[0.7,0.5]]}))     #使用feed_dict喂入数据，如果placeholder使用none缺省数据形状可以一次喂入多组数据