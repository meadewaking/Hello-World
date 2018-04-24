from numpy.random import RandomState
import matplotlib.pyplot as plt
from sklearn import decomposition
from sklearn.datasets import fetch_olivetti_faces
import numpy as np
 
 
n_row, n_col = 2, 3         #数据维度为2 X 3
n_components = n_row * n_col        #提取特征数目
image_shape = (64, 64)      #设定图像大小
 
 
###############################################################################
# Load faces data
dataset = fetch_olivetti_faces(shuffle=True, random_state=RandomState(0))   #指定随机抽取
faces = dataset.data    #数据维度共400 X 4096
 
###############################################################################
def plot_gallery(title, images, n_col=n_col, n_row=n_row):
    plt.figure(figsize=(2. * n_col, 2.26 * n_row))      #创建图片，并指定大小（英寸）
    plt.suptitle(title, size=16)    #设置标题
 
    for i, comp in enumerate(images):
        plt.subplot(n_row, n_col, i + 1)        #选择子图
        vmax = max(comp.max(), -comp.min())
 
        plt.imshow(comp.reshape(image_shape), cmap=plt.cm.gray,
                   interpolation='nearest', vmin=-vmax, vmax=vmax)      #数值归一化，以灰度显示
        plt.xticks(())      #去除子图坐标轴
        plt.yticks(())
    plt.subplots_adjust(0.01, 0.05, 0.99, 0.94, 0.04, 0.)   #调整子图位置及间隔
 
     
plot_gallery("First centered Olivetti faces", faces[:n_components])
###############################################################################
 
estimators = [
    ('PCA——principle component analysis',
         decomposition.PCA(n_components=6,whiten=True)),            #调用PCA算法作为对比
 
    ('NMF——non-negative matrix factorization',
         decomposition.NMF(n_components=6, init='nndsvda', tol=5e-3))       #调用NMF方法
]
 
###############################################################################
 
for name, estimator in estimators:
    print("目标维度 ：%d ，方法： %s..." % (n_components, name))
    print('原数据维度：',faces.shape,faces.dtype)
    estimator.fit(faces)        #处理数据
    components_ = estimator.components_         #选择方法并得到返回数据
    plot_gallery(name, components_[:n_components])      #只绘制6张图片
 
plt.show()