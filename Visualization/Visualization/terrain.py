import zipfile
import numpy as np
from mayavi import mlab
 
#读取压缩文件
hgt = zipfile.ZipFile('N36W113.hgt.zip').read('N36W113.hgt')       #hgt文件为NASA开发的地形扫描格式
data = np.fromstring(hgt,'>i2')     #其中>代表大端序（big-endian），i代表integer，2代表2bytes
data.shape = (3601, 3601)
data = data.astype(np.float32)
data = data[:1000, 900:1900]
data[data == -32768] = data[data > 0].min()     #将缺省值转换为最小值
 
#渲染地形hgt的数据data
mlab.figure(size=(800, 640), bgcolor=(0.16, 0.28, 0.46))        #绘制大小为800X640背景为蓝色的显示窗口
mlab.surf(data, colormap='gist_earth', warp_scale=0.2,
            vmin=1200, vmax=1610)
 
#清空内存
del data
#创建交互式的可视化窗口
mlab.view(-5.9, 83, 570, [5.3, 20, 238])        #设置camera初始点
mlab.show()