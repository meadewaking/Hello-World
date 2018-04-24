import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
 
 
# 读取数据集
datasets_X = []         #存储房屋尺寸
datasets_Y = []         #存储价格
fr = open('prices.txt','r')
lines = fr.readlines()
for line in lines:          #读取数据
    items = line.strip().split(',')
    square = float(items[0])/10.7639104     #平方英尺转换为平方米
    datasets_X.append(int(square))
    datasets_Y.append(int(items[1]))
 
length = len(datasets_X)        #获取信息点个数
datasets_X = np.array(datasets_X).reshape([length,1])
datasets_Y = np.array(datasets_Y)
 
minX = min(datasets_X)      #获取尺寸最小值
maxX = max(datasets_X)      #获取尺寸最大值
X = np.arange(minX,maxX).reshape([-1,1])        #等差根据最大最小值生成数列作为X轴
 
 
linear = linear_model.LinearRegression()        #调用线性回归模型
linear.fit(datasets_X, datasets_Y)          
 
# 图像中显示
plt.scatter(datasets_X, datasets_Y, color = 'red')      #绘制散点图
plt.plot(X, linear.predict(X), color = 'blue')          #绘制回归线
plt.xlabel('尺寸', fontproperties = "SimHei",fontsize = 20)
plt.ylabel('价格',fontproperties = "SimHei",fontsize = 20)
plt.show()


