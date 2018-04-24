'''
from scipy import constants         #引入sci所包含的常量库
print(constants.c)          #真空中的光速
print(constants.h)          #普朗克常量
print(constants.physical_constants["electron mass"])    #电子质量（常数值，单位，误差）
print(constants.mile,constants.inch,constants.gram,constants.pound)     #所有单位转换为标准单位制（英里，英寸，克，磅）
'''
'''
import numpy as np
from scipy.optimize import leastsq          #引入optimize库（包含各类数值优化算法）中的leastsq模块（最小二乘法数值拟合）

x = np.array([8.19,2.72,6.39,8.71,4.7,2.66,3.78])
y = np.array([7.01,2.78,6.47,6.71,4.1,4.23,4.85])

#计算拟合误差
def f(p):
    k,b = p
    return(y-(k*x+b))

r = leastsq(f,[1,0])        #（误差函数，【待定参数的初始值】）
k,b = r[0]
print("K =",k,"b =",b)
'''
'''
from scipy.optimize import fsolve   #fsolve可以对非线性方程组进行求解
from math import sin

def f(x):
    x0, x1, x2 = x.tolist()     #直接计算浮点数比在列表中计算更快
    return[5*x1+3,
           4*x0*x0 - 2*sin(x1*x2),
           x1*x2-1.5]

result = fsolve(f, [1,1,1])     #设定未知数初始值
print(result)       #结果
print(f(result))    #误差
'''
'''
import numpy as np
from scipy import interpolate   #interpolate实现插值算法
import pylab as pl

x = np.linspace(0,10,11)
y = np.sin(x)
pl.plot(x,y,'ro')       #绘制数据点

xnew = np.linspace(0,10,101)
for kind in ['nearest','zero','linear','quadratic']:        #分别使用不同的插值方式
    f = interpolate.interp1d(x,y,kind = kind)
    ynew = f(xnew)      
    pl.plot(xnew, ynew, label = str(kind))
pl.legend(loc = 'lower right')      #右下角显示说明
pl.show()
'''
'''
import numpy as np
from scipy import linalg        #引入线性代数库
import timeit       #timeit模块可以测试一段代码的运行时间

m,n = 50,50
A = np.random.rand(m,n)     #生成随机的50X50的方阵
B = np.random.rand(m,n)

def func1():
    x1 = linalg.solve(A,B)      #求解Ax = B，即x = A^(-1)B

def func2():
    x2 = np.dot(linalg.inv(A),B)    #用np实现作对比

t1 = timeit.Timer(stmt = func1).timeit(number = 100)
t2 = timeit.Timer(stmt = func2).timeit(number = 100)        #比较两者运行时间
print(t1,t2)
'''
'''
import numpy as np
from scipy import linalg
A = np.array([[1,-0.3],[-0.1,0.9]])
evalues,evectors = linalg.eig(A)        #求解特征值和特征向量
print(evalues,"\n")
print(evectors)
'''
'''
import numpy as np
from scipy import linalg
A = np.array([[1,-0.3],[-0.1,0.9]])
U,s,V = linalg.svd(A)       #奇异值分解 M=UΣV（M为m X n矩阵，U为m X m矩阵，Σ为 m X n矩阵，V为 n X n矩阵。奇异值为Σ对角线上的元素）
print(U,end = "\n\n")
print(s,end = "\n\n")
print(V,end = "\n\n")
'''
'''
from scipy import stats
s = stats.norm.stats()      #获得标准正态分布的期望和方差
s = stats.norm(loc = 1.0,scale = 2.0).stats()   #可以使用loc和scale参数对随机变量进行偏移和缩放
s = stats.norm.rvs(size = 10000)    #对随机变量进行随机取样（10000次）
import numpy as np
print(np.mean(s),np.var(s))     #验证大数极限定理
print(s)
'''
'''
from scipy import stats
x = range(1,7)
p = (1/6,1/6,1/6,1/6,1/6,1/6)
dice = stats.rv_discrete(values=(x,p))  #stats下离散随机变量由rv_discrete类继承，此例创建骰子的随机变量
print(dice.rvs(size = 10))  #模拟掷十次骰子
import numpy as np
samples = dice.rvs(size=(2000,50))    #模拟两千次每次50下的掷骰子
samples_mean = np.mean(samples,axis = 1)    #求出每50次的期望
print(samples_mean)     #验证中心极限定理
import pylab as pl
_,bins,step = pl.hist(samples_mean, bins = 100, normed = True, histtype = "step", label = "Histogram")  #绘制离散期望的直方图
kde = stats.kde.gaussian_kde(samples_mean)  #核密度函数估计
x = np.linspace(bins[0],bins[-1],100)   #生成核密度函数x轴
pl.plot(x, kde(x), label = "kde")       #绘制核密度函数
mean, std = stats.norm.fit(samples_mean)    #拟合离散值得出期望和标准差
pl.plot(x, stats.norm(mean,std).pdf(x), alpha = 0.8, label = "normal fitting")  #绘制拟合曲线
pl.legend()     #绘制图注
pl.show()       #取值越多三条曲线越接近
'''
'''
def half_circle(x):     #定义半圆的曲线方程
    return (1-x**2)**0.5

import numpy as np
N = 10000
x = np.linspace(-1, 1, N)   #利用定积分的定义求解圆的面积（此处划分为10000个矩形）
dx = x[1] - x[0]
y = half_circle(x)
print(dx*np.sum(y)*2)

from scipy import integrate
pi_half, err = integrate.quad(half_circle, -1, 1)   #利用scipy中integrate模块进行积分计算结果更为精准
print(pi_half*2)
'''
'''
from scipy import integrate     #求解球的体积

def half_circle(x):
    return (1-x**2)**0.5

def half_sphere(x,y):
    return (1-x**2-y**2)**0.5

volume, error = integrate.dblquad(half_sphere, -1, 1, lambda x:-half_circle(x), lambda x:half_circle(x))    #利用dblquad进行二重积分，（被积分方程，x积分区间，y积分区间）
print(volume, error)
'''
'''
import numpy as np
def lorenz(w, t, a, b, c):
    x, y, z = w.tolist()
    return np.array([a * (y - x), x * (b - z) - y, x * y - c * z])

from scipy.integrate import odeint      #odeint用于求解常微分方程
t = np.arange(0, 30, 0.01)      #创建时间点
track1 = odeint(lorenz, (0.0, 1.0, 0.0), t, args = (10.0, 28.0, 3.0))    #（洛伦兹吸引子方程，初始位失，时间区间，常量）
track2 = odeint(lorenz, (0.0, 1.01, 0.0), t, args = (10.0, 28.0, 3.0))    #（洛伦兹吸引子方程，初始位失，时间区间，常量）

from mayavi import mlab
mlab.plot3d(track1[:, 0], track1[:, 1], track1[:, 2], color = (1, 0, 0), tube_radius = 0.1)
mlab.plot3d(track2[:, 0], track2[:, 1], track2[:, 2], color = (0, 0, 1), tube_radius = 0.1)
mlab.show()
'''
'''
import numpy as np
np.random.seed(42)
points2d = np.random.rand(10, 2)        #“随机”生成10个点（二维）
from scipy import spatial
ch2d = spatial.ConvexHull(points2d)     #计算点阵的凸包
print(ch2d.simplices)       #凸包的顶点
print(ch2d.vertices)        #顶点在点阵中的下标

import pylab as pl
poly = pl.Polygon(points2d[ch2d.vertices], fill = None, lw = 2, color = 'r', alpha = 0.5)       #绘制凸包边界
ax = pl.subplot(aspect = 'equal')   
pl.plot(points2d[:, 0], points2d[:, 1], 'go')       #绘制点阵
for i, pos in enumerate(points2d):
    pl.text(pos[0], pos[1], str(i), color = 'blue')     #绘制点标
ax.add_artist(poly)
pl.show()
'''

import numpy as np
np.random.seed(42)
point3d = np.random.rand(40, 3)         #建立三维点阵
from scipy import spatial
ch3d = spatial.ConvexHull(point3d)
print(ch3d.simplices.shape)
print(ch3d.simplices)
print(ch3d.vertices)

def convexhull(ch3d):
    #定义凸多面体tvtk的polydata（）对象
    poly = tvtk.PolyData()
    poly.points = ch3d.points
    poly.polys = ch3d.simplices
    #定义顶点样式，为小球
    sphere = tvtk.SphereSource(radius = 0.02)
    points3d = tvtk.Glyph3D()
    points3d.set_source_connection(sphere.output_port)
    points3d.set_input_data(poly)
    #绘制凸包的面，设置透明度
    m1 = tvtk.PolyDataMapper()
    m1.set_input_data(poly)
    a1 = tvtk.Actor(mapper = m1)
    a1.property.opacity = 0.3
    #绘制凸包的边，为红色
    m2 = tvtk.PolyDataMapper()
    m2.set_input_data(poly)
    a2 = tvtk.Actor(mapper = m2)
    a2.property.representation = "wireframe"
    a2.property.line_width = 2.0
    a2.property.color = (1.0, 0, 0)
    #绘制顶点
    m3 = tvtk.PolyDataMapper(input_connection = points3d.output_port)
    a3 = tvtk.Actor(mapper = m3)
    a3.property.color = (0.0, 1.0, 0.0)
    return [a1, a2, a3]


from tvtk.api import tvtk
from tvtkfunc import ivtk_scene, event_loop
actors = convexhull(ch3d)
win = ivtk_scene(actors)
win.scene.isometric_view()
event_loop()