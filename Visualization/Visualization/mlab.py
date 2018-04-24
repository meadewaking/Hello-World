import numpy as np
from mayavi import mlab

#建立数据
x, y = np.mgrid[-10:10:200j, -10:10:200j]
z = 100 * np.sin(x * y) / (x * y)
# 对数据进行可视化
mlab.figure(bgcolor=(1, 1, 1))          #figure函数建立新的场景
surf = mlab.surf(z, colormap='cool')        #cool为冷色系颜色映射
lut = surf.module_manager.scalar_lut_manager.lut.table.to_array()   #获取surf对象的lut（look up table（colormap的定义颜色）），lut对象为255X4数组，每行表示RGBA
lut[:, -1] = np.linspace(0,255,256) #alpha通道表示透明度（0为完全透明 1为不透明）,等差的数组使其产生渐变的透明度
#lut[:, -1] = np.full_like(np.zeros((1,256)),100)
surf.module_manager.scalar_lut_manager.lut.table = lut
# 更新视图并显示出来
mlab.show()