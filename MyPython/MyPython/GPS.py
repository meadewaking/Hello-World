from numpy import *

'''
(x - x0)^2 + (y - y0)^2 + (z - z0)^2 = [(t0 - t)*c]^2       公式 
'''

i = 1
c = 0.299792458         #真空中的光速取(Km/μs)
x = zeros((6 ,4))       #存储6个卫星的（x，y，z，t）的参数
while i<=6:         
    print("%s %d %s" % ("请输入卫星", i, "的（x，y，z，t）"))
    temp = input()
    x[i-1] = temp.split()
    j = 0
    while j < 4:            #参数浮点化
        x[i - 1][j] = float(x[i - 1][j])
        j = j + 1
    i = i + 1
a = zeros((4, 4)) #系数矩阵
b = zeros((4, 1))  #常数项
j = 0
while j<4:              #将方程化为线性矩阵
    a[j][0] = 2 * (x[5][0] - x[j][0]) 
    a[j][1] = 2 * (x[5][1] - x[j][1]) 
    a[j][2] = 2 * (x[5][2] - x[j][2]) 
    a[j][3] = 2 * c * c * (x[j][3] - x[5][3]) 
    b[j][0] = x[5][0] * x[5][0] - x[j][0] * x[j][0] + \
              x[5][1] * x[5][1] - x[j][1] * x[j][1] + \
              x[5][2] * x[5][2] - x[j][2] * x[j][2] + \
              c * c * (x[j][3] * x[j][3] - x[5][3] * x[5][3])
    j = j + 1
a = linalg.inv(a)   #系数矩阵求逆
print(dot(a, b))    #点乘矩阵