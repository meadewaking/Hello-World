import matplotlib.pyplot as plt
import numpy as np

def initCenters(dataSet, k):        #初始化聚类中心
    numSamples, dim = dataSet.shape
    centers = np.zeros((k, dim))        #初始中心
    for i in range(k):
        index = int(np.random.uniform(0, numSamples)) #随机获取k个聚类中心
        centers[i, :] = dataSet[index,:]
    return centers

def Dist2Centers(sample, centers):  #计算样本与中心的差异（距离）
    k = centers.shape[0]        #获取类数
    dis2cents = np.zeros(k)     #初始距离矩阵
    for i in range(k):
        dis2cents[i]=np.sqrt(np.sum(np.power(sample - centers[i, :], 2)))       #求单个样本点到各个中心的距离
    return dis2cents

def showCluster(dataSet, k, centers, clusterAssignment):
    numSamples, dim = dataSet.shape
    
    mark = ['or' , 'ob' , 'og' ,'om' ,'oy']

    for i in range(numSamples):
        markIndex = int(clusterAssignment[i])
        plt.plot(dataSet[i, 0], dataSet[i, 1], mark[markIndex])     #显示样本点

    mark = [ 'Dr' , 'Db' , 'Dg' , 'Dm','Dy']

    for i in range(k):
        plt.plot(centers[i, 0], centers[i, 1], mark[i], markersize = 12) #显示聚类中心
    plt.show()      #执行显示

def kmeans(dataSet, k, iterNum):        #   K-means运算
    numSamples = dataSet.shape[0]       #统计样本点个数，shape函数表示矩阵的维度和维度的元祖数（行数，列数）
    iterCount = 0           #保证至少运行一次
    # 将样本分类
    clusterAssignment = np.zeros (numSamples)   #生成样本数个元素的全0数组
    clusterChanged = True       

    # 初始化中心
    centers = initCenters(dataSet, k)
    while clusterChanged and iterCount < iterNum:
        clusterChanged = False      #迭代基例，当样本所属类不再变动，则聚类结束
        iterCount = iterCount + 1
        #对每个样本进行计算
        for i in range(numSamples):
            dis2cent=Dist2Centers(dataSet[i,:], centers)
            minIndex = np.argmin (dis2cent)     #取最小索引（距离）
            ## 更新所属类
            if clusterAssignment[i] != minIndex:    #找到最相似（接近）的中心，并判断下一次迭代是否进行，并记录差别（距离）最小的中心
                clusterChanged = True           
                clusterAssignment[i] = minIndex
        #更新中心
        for j in range(k):
            pointsInCluster = dataSet[np.nonzero(clusterAssignment[:] == j)[0]]
            centers[j, :] = np.mean(pointsInCluster, axis = 0)      #求样本点平均值，重新确定中心

    print ('聚类成功！')
    return centers, clusterAssignment

def main():
    #载入数据
    print ("载入数据……")
    dataSet =[]         #声明数据集
    dataSetFile = open('D:\\Mis\\testSet.dat')
    for line in dataSetFile:
        lineArr = line.strip().split('\t')      #以“\t”分裂每行数据并存储
        dataSet.append([float(lineArr[0]), float(lineArr[1])])      #用二维列表保存样本

    #聚类
    print ("聚类……")
    dataSet = np.mat(dataSet)       #样本信息矩阵化（numpy以二维列表存储矩阵）

    k = 4       #类的个数
    centers_result, clusterAssignment_result = kmeans(dataSet,k, 100)
   
    #显示结果
    print ("绘制结果……")
    showCluster(dataSet, k, centers_result, clusterAssignment_result)
  
main()