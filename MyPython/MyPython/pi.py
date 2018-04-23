import math
import random   #蒙特卡洛法求π
import time

points = 10**7      #总点数
hits = 0        #击中数
time.clock()
for i in range(1,points):
    x, y = random.random(), random.random()     #生成随机坐标
    radius = math.sqrt(x**2 + y**2)     #判断坐标与原点距离
    if radius <=1.0:        #判断命中
        hits = hits + 1
pi = 4 * (hits/points)
print(pi)
print(time.clock())