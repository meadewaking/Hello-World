'''
import numpy as np
x, y, z = np.ogrid[-10:10:20j, -10:10:20j, -10:10:20j]
s = np.sin(x*y*z)/(x*y*z)           #生成数据
 
from mayavi import mlab     #引入mlab
from mayavi.tools import pipeline   #引入管线控制函数
src = mlab.pipeline.scalar_field(s)     #标量显示
mlab.pipeline.iso_surface(src, contours=[s.min()+0.1*s.ptp(), ], opacity=0.3)       #绘制外层等值面，透明度为0.3
mlab.pipeline.iso_surface(src, contours=[s.max()-0.1*s.ptp(), ])        #绘制内层等值面
mlab.pipeline.image_plane_widget(src,
                            plane_orientation='z_axes',
                            slice_index=10,
                        )       #绘制z轴切平面
mlab.show()
'''

import numpy as np
x, y, z = np.mgrid[0:1:20j, 0:1:20j, 0:1:20j]
u =    np.sin(np.pi*x) * np.cos(np.pi*z)
v = -2*np.sin(np.pi*y) * np.cos(2*np.pi*z)
w = np.cos(np.pi*x)*np.sin(np.pi*z) + np.cos(np.pi*y)*np.sin(2*np.pi*z)
 
from mayavi import mlab
#mlab.quiver3d(u,v,w)    #绘制
#mlab.outline()      #绘制轮廓线
src = mlab.pipeline.vector_field(u,v,w)
mlab.pipeline.vectors(src,mask_points = 10,scale_factor = 2)    #矢量mask采样
mlab.pipeline.vector_cut_plane(src,scale_factor = 2)
mlab.show()