import tensorflow as tf

w1 = tf.Variable(0,dtype = tf.float32)      
global_step = tf.Variable(0,trainable = False)
MOVING_AVERAGE_DECAY = 0.99     #滑动平均衰减率
ema = tf.train.ExponentialMovingAverage(MOVING_AVERAGE_DECAY,global_step)
ema_op = ema.apply(tf.trainable_variables())    #ema.apply为括号里的参数更新列表，每次执行时求出列表中的滑动平均，此处更新的是所有可训练的参数

with tf.Session() as sess:
    init_op = tf.global_variables_initializer()
    sess.run(init_op)
    print(sess.run([w1,ema.average(w1)]))   #当前的参数与滑动平均值

    sess.run(tf.assign(w1,1))   #将w1赋值为1，输出此时的滑动平均
    sess.run(ema_op)
    print(sess.run([w1,ema.average(w1)]))

    sess.run(tf.assign(w1,10))      #模拟第100轮，w1变为10
    sess.run(tf.assign(global_step,100))
    sess.run(ema_op)
    print(sess.run([w1,ema.average(w1)]))

    for i in range(6):
        sess.run(ema_op)
        print(sess.run([w1,ema.average(w1)]))   #多次运行滑动平均

'''
影子 = 衰减率 * 影子 + （1 - 衰减率）* 参数
衰减率 = min{MOVING_AVERAGE_DECAY，1+global_step/10+global_step}
影子初值 = 参数初值
'''