from numpy import pi, sin, cos, mgrid
from mayavi import mlab
import numpy as np
'''
#建立数据
dphi, dtheta = pi/250.0, pi/250.0
[phi,theta] = mgrid[0:pi+dphi*1.5:dphi,0:2*pi+dtheta*1.5:dtheta]
m0 = 4; m1 = 3; m2 = 2; m3 = 3; m4 = 6; m5 = 2; m6 = 6; m7 = 4;
r = sin(m0*phi)**m1 + cos(m2*phi)**m3 + sin(m4*theta)**m5 + cos(m6*theta)**m7
x = r*sin(phi)*cos(theta)
y = r*cos(phi)
z = r*sin(phi)*sin(theta)
'''
'''
#mayavi绘图按照对应的点依次连接，并将点之间围成的面显示出来
x = [[-1,1,1,-1,-1],[-1,1,1,-1,-1]]
y = [[-1,-1,-1,-1,-1],[1,1,1,1,1]]
z = [[1,1,-1,-1,1],[1,1,-1,-1,1]]

#对该数据进行三维可视化
s = mlab.mesh(x, y, z)      #mesh函数每三个点确定一个平面
mlab.show()
'''
'''
x, y, z = np.ogrid[-5:5:64j, -5:5:64j, -5:5:64j]        #ogrid函数返回等差二维ndarray（开始：结束：步长j）
scalars = x * x + y * y + z * z
#obj = mlab.contour3d(scalars, contours=8, transparent=True)         #绘制等值面(绘制数据，绘制等值面个数（实际显示比设定少两个面），是否透明)
mlab.show()
'''
'''
def f(x, y):
    return np.sin(x - y)+np.cos(x + y)
x, y = np.mgrid[-7.:7.05:0.1, -5.:5.05:0.05]
con_s = mlab.contour_surf(x, y, f)  #contour_surf函数绘制等值线，其中调用标量函数可以直接调用命名
s = mlab.surf(x, y, f)      #surf函数将标量大小以高度表现出来
mlab.show()
'''
'''
s = np.random.random((10, 10))
img = mlab.imshow(s, colormap='gist_earth')     #imshow函数将二维数组按图片形式展示出来，gist_earth为地表颜色
mlab.show()
'''
'''
#建立数据
n_mer, n_long = 6, 11
dphi = np.pi / 1000.0
phi = np.arange(0.0, 2 * np.pi + 0.5 * dphi, dphi)
mu = phi * n_mer
x = np.cos(mu) * (1 + np.cos(n_long * mu / n_mer) * 0.5)
y = np.sin(mu) * (1 + np.cos(n_long * mu / n_mer) * 0.5)
z = np.sin(n_long * mu / n_mer) * 0.5
 
#对数据进行可视化
l = mlab.plot3d(x, y, z, np.sin(mu), tube_radius=0.025, colormap='Spectral')    #plot3d函数绘制线，tube_radius为绘制线的半径
mlab.show()
'''
'''
#建立数据
t = np.linspace(0, 4 * np.pi, 20)
x = np.sin(2 * t)
y = np.cos(t)
z = np.cos(2 * t)
s = 2 + np.sin(t)
 
#对数据进行可视化
points = mlab.points3d(x, y, z, s, colormap="Reds", scale_factor=.25)   #points3d函数绘制点，scale_factor为放缩比例
mlab.show()
'''
'''
x, y, z = np.mgrid[-2:3, -2:3, -2:3]
r = np.sqrt(x ** 2 + y ** 2 + z ** 4)
u = y * np.sin(r)/(r + 0.001)
v = -x * np.sin(r)/(r+0.001)
w = np.zeros_like(z)
 
obj = mlab.quiver3d(x, y, z, u, v, w, line_width=3, scale_factor=1)     #quiver3d函数绘制矢量图，xyz表示位置，uvw表示矢量分量
mlab.show()
'''