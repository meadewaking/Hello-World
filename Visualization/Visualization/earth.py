# 城市经纬度数据
cities_data = """
Bei Jing, 116.23,39.54
Shang Hai, 121.52, 30.91
Hong Kong,114.19,22.38
Delhi,77.21,28.67
Johannesburg,28.04,-26.19
Doha,51.53,25.29
Sao Paulo,-46.63,-23.53
Toronto,-79.38,43.65
New York,-73.94,40.67
San Francisco,-122.45,37.77
Dubai,55.33,25.27
Sydney,151.21,-33.87
"""
########## 读取数据#########
# 建立城市-城索引的字典、城市经纬度的列表
import csv
cities = dict()
coords = list()
for line in list(csv.reader(cities_data.split('\n')))[1:-1]:
    name, long_, lat = line     #分别读取城市名称，经度，维度
    cities[name] = len(coords)  #利用经纬度列表的长度增长创建序列
    coords.append((float(long_), float(lat)))   #将经纬度添加进列表
 
########## 坐标转换##########
# 将经纬度的位置转换为三维坐标
import numpy as np
coords = np.array(coords)
lat, long = coords.T * np.pi / 180
x = np.cos(long) * np.cos(lat)
y = np.cos(long) * np.sin(lat)
z = np.sin(long)
 
##########建立窗口##########
from mayavi import mlab
mlab.figure(bgcolor=(0.48, 0.48, 0.48), size=(400, 400))
 
##########绘制地球##########
# 绘制半透明球体表示地球
sphere = mlab.points3d(0, 0, 0, scale_factor=2,
                                color=(0.67, 0.77, 0.93),
                                resolution=50,
                                opacity=0.7,
                                name='Earth')
 
# 调整镜面反射参数
sphere.actor.property.specular = 0.45       #设置反射度
sphere.actor.property.specular_power = 5        #设置高光强度
# 设置背面剔除，以更好的显示透明效果
sphere.actor.property.backface_culling = True
 
##########绘制城市##########
# 绘制城市位置
points = mlab.points3d(x, y, z, scale_factor=0.03,color=(0, 0, 1))      #每个城市以一个小圆球表示
# 绘制城市名称
for city, index in cities.items():      #城市与经纬度有相同的序列
    label = mlab.text(x[index], y[index], city,
                      z=z[index], color=(0,0,0),
                      width=0.016 * len(city), name=city)
     
##########绘制大洲边界##########
from mayavi.sources.builtin_surface import BuiltinSurface
continents_src = BuiltinSurface(source='earth', name='Continents')
# 设置LOD为2，LOD（level of detail）即多层细节处理，对不同等级的细节进行不同程度的渲染
continents_src.data_source.on_ratio = 2
continents = mlab.pipeline.surface(continents_src, color=(0, 0, 0))
 
##########绘制赤道##########
theta = np.linspace(0, 2 * np.pi, 100)#平分360为100份，即正100边形
x = np.cos(theta)
y = np.sin(theta)
z = np.zeros_like(theta) 
mlab.plot3d(x, y, z, color=(1, 1, 1),opacity=0.2, tube_radius=None)
##########显示可交互窗口##########
mlab.view(100, 60, 4, [-0.05, 0, 0])
mlab.show()