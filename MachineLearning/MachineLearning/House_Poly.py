import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
from sklearn.preprocessing import PolynomialFeatures
 
 
# 读取数据集
datasets_X = []     #保存尺寸
datasets_Y = []     #保存价格
fr = open('prices.txt','r')
lines = fr.readlines()
for line in lines:
    items = line.strip().split(',')
    datasets_X.append(int(items[0]))
    datasets_Y.append(int(items[1]))        #加载数据
 
length = len(datasets_X)
datasets_X = np.array(datasets_X).reshape([length,1])
datasets_Y = np.array(datasets_Y)       #重整数据格式
 
minX = min(datasets_X)
maxX = max(datasets_X)
X = np.arange(minX,maxX).reshape([-1,1])        #建立X轴
 
 
poly_reg = PolynomialFeatures(degree = 2)              
X_poly = poly_reg.fit_transform(datasets_X)     #求得多项式自变量
lin_reg_2 = linear_model.LinearRegression()     
lin_reg_2.fit(X_poly, datasets_Y)           #线性拟合
 
# 图像中显示
plt.scatter(datasets_X, datasets_Y, color = 'red')
plt.plot(X, lin_reg_2.predict(poly_reg.fit_transform(X)), color = 'blue')
plt.xlabel('Area')
plt.ylabel('Price')
plt.show()