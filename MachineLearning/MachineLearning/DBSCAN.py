import numpy as np
import sklearn.cluster as skc
from sklearn import metrics
import matplotlib.pyplot as plt
 
 
mac2id=dict()
onlinetimes=[]

f=open('TestData.txt',encoding='utf-8')     #读取数据
for line in f:
    mac=line.split(',')[2]      #录入mac地址
    onlinetime=int(line.split(',')[6])
    starttime=int(line.split(',')[4].split(' ')[1].split(':')[0])       #只录入小时
    if mac not in mac2id:
        mac2id[mac]=len(onlinetimes)        #通过onlinetimes的录入增加自增长id
        onlinetimes.append((starttime,onlinetime))
    else:
        onlinetimes[mac2id[mac]]=[(starttime,onlinetime)]

real_X=np.array(onlinetimes).reshape((-1,2))        #参数-1会自动计算对应维度的值

X=real_X[:,0:1]     #取starttime

db=skc.DBSCAN(eps=0.01,min_samples=20).fit(X)   #eps为聚类半径，min_samples为簇内样本数，metric为距离计算方式，fit调用数据
labels = db.labels_     #返回簇标签，-1为噪声点
 
print('所属类：')
print(labels)
raito=len(labels[labels[:] == -1]) / len(labels)    #计算噪声率
print('噪声率:',format(raito, '.2%'))
 
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)#计算簇个数，可以用set函数集合化标签获得不重复元素，Python中 if else可以作为三元运算符等同于 ？ ：
 
print('簇个数: %d' % n_clusters_)
print("轮廓系数: %0.3f"% metrics.silhouette_score(X, labels))     #计算轮廓系数，越接近1说明聚类效果越理想
 
for i in range(n_clusters_):
    print('簇 ',i,':')
    print(list(X[labels == i].flatten()))       #输出各个簇内数据
     
plt.hist(X,24)      #绘制直方图
plt.show()