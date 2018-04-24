from tvtk.api import tvtk
'''
#imagedata数据集是规则的网格
img = tvtk.ImageData(spacing = (1,1,1),origin = (1,2,3),dimensions = (3,4,5))   #（spacing为网格间距，origin为原点，dimension为各个轴上网格数）
for n in range(6):
    print("%f,%f,%f"%img.get_point(n))

'''
#RectilinearGrid为线性不规则网格
import numpy as np  #可以使用numpy创建
 
x = np.array([0,3,9,15])
y = np.array([0,1,5])
z = np.array([0,2,3])
r = tvtk.RectilinearGrid()
r.x_coordinates = x
r.y_coordinates = y
r.z_coordinates = z
r.dimensions = len(x),len(y),len(z)
for n in range(6):
    print("%f,%f,%f"%r.get_point(n))

#此外还有polydata网格（点构成的多边形组成）和structuredgrid网格（任意形状网格（通过确定每个点的位置））较为常用