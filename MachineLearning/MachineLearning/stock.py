import pandas as pd
import numpy as np
from sklearn import svm                 #引入SVM算法
from sklearn import cross_validation        #引入交叉验证法
 
data=pd.read_csv('stock/000777.csv',encoding='gbk',parse_dates=[0],index_col=0)     #读取csv文件为dataframe类型，（文件，编码，第0列解析为日期，用于行索引的列编号）
data.sort_index(0,ascending=True,inplace=True)      #（按0列（日期）排序，是否升序，是否覆盖原数据）
 
dayfeature=150              #分析150天的数据
featurenum=5*dayfeature         #数据特征总数为数据维度（5）X 天数
x=np.zeros((data.shape[0]-dayfeature,featurenum+1))     #记录每150天的特征数据
y=np.zeros((data.shape[0]-dayfeature))      #记录涨跌
 
for i in range(0,data.shape[0]-dayfeature):         #处理要进行测试的数据
    x[i,0:featurenum]=np.array(data[i:i+dayfeature] \
          [[u'收盘价',u'最高价',u'最低价',u'开盘价',u'成交量']]).reshape((1,featurenum))   #将每150天的其中5个维度的信息存储在一行中
    x[i,featurenum]=data.ix[i+dayfeature][u'开盘价']       #保留一列记录当天开盘价（u为Unicode编码）

for i in range(0,data.shape[0]-dayfeature):
    if data.ix[i+dayfeature][u'收盘价']>=data.ix[i+dayfeature][u'开盘价']:
        y[i]=1      #记录后450天的涨跌情况(涨记录为1，跌记录为0)
    else:
        y[i]=0          
 
clf=svm.SVC(kernel='rbf')       #调用SVM算法
result = []
for i in range(5):
    x_train, x_test, y_train, y_test = \
                cross_validation.train_test_split(x, y, test_size = 0.2)    #调用交叉验证法训练集与测试集切割比为1:4
    clf.fit(x_train, y_train)
    result.append(np.mean(y_test == clf.predict(x_test)))   #记录每次测试的准确率
print("svm 分类准确度:")
print(result)