import numpy as np
from sklearn.cluster import KMeans
 
 
def loadData(filePath):
    fr = open(filePath,'r+')
    lines = fr.readlines()
    retData = []
    retCityName = []
    for line in lines:
        items = line.strip().split(",")         #数据按照，分割
        retCityName.append(items[0])            #存入省市名
        retData.append([float(items[i]) for i in range(1,len(items))])      #存入各方面的花销
    return retData,retCityName
 
     

data,cityName = loadData('city.txt')        #读取数据
km = KMeans(n_clusters=4)           #n_clusters为聚类中心个数，init（默认为K-means++）为初始中心方法，max_iter(默认为300)为最大迭代次数，一般只需设定中心个数
label = km.fit_predict(data)        #fit_predict返回每个元素的簇中心序号
expenses = np.sum(km.cluster_centers_,axis=1)       #将聚类中心所有维度数值之和作为标签
CityCluster = [[],[],[],[]]         #省市簇
for i in range(len(cityName)):
    CityCluster[label[i]].append(cityName[i])       #将省市名归类
for i in range(len(CityCluster)):
    print("簇中心和:%.2f" % expenses[i])
    print(CityCluster[i])