import numpy as np
import PIL.Image as image
from sklearn.cluster import KMeans
 
def loadData(filePath):             #加载数据
    f = open(filePath,'rb')         #以二进制读取文件
    data = []
    img = image.open(f)
    m,n = img.size          #读取图片大小
    for i in range(m):
        for j in range(n):
            x,y,z = img.getpixel((i,j))     #逐像素读取RGBֵ值   
            data.append([x/256.0,y/256.0,z/256.0])      #归一化并存储
    f.close()
    return np.mat(data),m,n     #像素矩阵化
 
imgData,row,col = loadData('test.jpg')
label = KMeans(n_clusters=3).fit_predict(imgData)       #调用kmeans聚类，聚类数
 
label = label.reshape([row,col])        #重整为矩阵
pic_new = image.new("L", (row, col))    #创建灰度图片
for i in range(row):
    for j in range(col):
        pic_new.putpixel((i,j), int(256/(label[i][j]+1)))       #逐像素保存灰度值
pic_new.save("result.jpg", "JPEG")