import tensorflow as tf
'''
a = tf.constant([1.0,2.0])      #声明张量，默认数据类型tf.float32
b = tf.constant([3.0,4.0])

result = a + b
print(result)       #计算图只负责搭建不负责计算
'''
'''
#构建一个两层三神经元的神经网络计算图
x = tf.constant([[1.0,2.0]])    #声明输入层输入变量x1与x2
w = tf.constant([[3.0],[4.0]])  #声明权重w1与w2

y = tf.matmul(x,w)  #矩阵乘法

print(y)

with tf.Session() as sess:      #在tensorflow中以会话开启计算图的运算
    print(sess.run(y))
'''
