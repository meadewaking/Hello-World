import os
import numpy as np
import PIL.Image as image
from sklearn.cluster import KMeans

def loadImage():
    list_dirs = os.walk("image/")       #遍历获得的目录树列表
    list_img = []           #数组形式的图片列表
    feature = []        #保存一维形式的用于聚类的图片信息
    ss = []         #找最小图片的临时列表
    imgs = np.ndarray(shape=(1,0))
    for root, dirs, files in list_dirs:         #遍历目录中所有图片
        for f in files:
            a = np.array(image.open(root + f))
            ss.append(a.size)

        small = min(ss)             #找到最小图片大小

        for f in files:
            a = np.array(image.open(root + f))
            list_img.append(a)          #保存图片数组
            b = a.astype(np.float)      #转元素格式为float
            b = b.flatten()             #降维至一维
            feature.append(b[:small])           #对图片切片至同样大小（牺牲部分数据的折中方法）
    print("加载数据完成\n")
    return list_img,feature

list_img,feature = loadImage()      #加载数据
print("开始聚类")

label = KMeans(n_clusters=3).fit_predict(feature)       #聚为3类
print("聚类成功")

x = 0
for i in list_img:
    i = i.clip(0,255)
    im = image.fromarray(i.astype('uint8'))
    im.save("result/" + str(label[x]) + "_" + str(x) + ".jpg")      #按簇标签保存数据
    x = x + 1