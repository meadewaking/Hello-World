import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.datasets import load_iris
import numpy as np
 
data = load_iris()      #加载鸢尾花数据集（sklearn中已包含）
y = data.target         #读取数据标签
X = data.data           #读取数据（4维）

pca = PCA(n_components=2)       #调用PCA降维算法，n_components为降维后的维度，svd_solver为特征分解方法
reduced_X = pca.fit_transform(X)        #对原始数据降维并保存,fit与fit_transform压缩不同方向

red_x, red_y = [], []
blue_x, blue_y = [], []
green_x, green_y = [], []
 
for i in range(len(reduced_X)):
    if y[i] == 0:
        red_x.append(reduced_X[i][0])       #保存第一类降维数据(降维后的数据顺序不变)
        red_y.append(reduced_X[i][1])
    elif y[i] == 1:
        blue_x.append(reduced_X[i][0])
        blue_y.append(reduced_X[i][1])
    else:
        green_x.append(reduced_X[i][0])
        green_y.append(reduced_X[i][1])
 
plt.scatter(red_x, red_y, c='r', marker='x')        #绘制散点图
plt.scatter(blue_x, blue_y, c='b', marker='D')
plt.scatter(green_x, green_y, c='g', marker='.')
plt.show()