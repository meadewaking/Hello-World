import pandas as pd
import numpy as np  
 
from sklearn.preprocessing import Imputer               #引入数据预处理模块
from sklearn.cross_validation import train_test_split   #引入训练集生成模块
from sklearn.metrics import classification_report       #引入结果评估模块
   
from sklearn.neighbors import KNeighborsClassifier      #引入KNN
from sklearn.tree import DecisionTreeClassifier         #引入决策树
from sklearn.naive_bayes import GaussianNB              #引入高斯朴素贝叶斯

 
def load_datasets(feature_paths, label_paths):          #载入数据集
    feature = np.ndarray(shape=(0,41))      #数据共41个维度
    label = np.ndarray(shape=(0,1))         #标签数据为1维
    for file in feature_paths:
        df = pd.read_table(file, delimiter=',', na_values='?', header=None)     #读取数据为dataframe类型（原数据用，分割，用？标记空值，无表头）
        imp = Imputer(missing_values='NaN', strategy='mean', axis=0)        #对缺失值补全为平均值
        imp.fit(df)     #进行预处理训练
        df = imp.transform(df)      #获取预处理结果
        feature = np.concatenate((feature, df))     #将预处理后的结果合并到feature中
     
    for file in label_paths:
        df = pd.read_table(file, header=None)       #读取标签文件
        label = np.concatenate((label, df))         #合并到label中
         
    label = np.ravel(label)
    return feature, label
 

if __name__ == '__main__':
    ''' 数据路径 '''
    featurePaths = ['human/A.feature','human/B.feature','human/C.feature']
    labelPaths = ['human/A.label','human/B.label','human/C.label']
    ''' 读入数据  '''
    x_train,y_train = load_datasets(featurePaths[:2],labelPaths[:2])        #将前四个数据集作为训练集
    x_test,y_test = load_datasets(featurePaths[2:],labelPaths[2:])          #将最后一个数据集作为测试集
    x_train, x_, y_train, y_ = train_test_split(x_train, y_train, test_size = 0.0)      #生成训练集并对其打乱
     
    print('开始KNN训练')
    knn = KNeighborsClassifier().fit(x_train, y_train)          #调用KNN训练
    print('训练完成')
    answer_knn = knn.predict(x_test)            #测试
    print('预测完成')
     
    print('开始决策树训练')
    dt = DecisionTreeClassifier().fit(x_train, y_train)         #调用决策树训练
    print('训练完成')
    answer_dt = dt.predict(x_test)          #测试
    print('预测完成')
     
    print('开始朴素贝叶斯训练')
    gnb = GaussianNB().fit(x_train, y_train)            #调用高斯朴素贝叶斯训练
    print('训练完成')
    answer_gnb = gnb.predict(x_test)            #测试
    print('预测完成')
     
    print('\n\nKNN分类结果:')
    print(classification_report(y_test, answer_knn))        #评估结果（精确度，召回度，f1值，支持度）
    print('\n\n决策树分类结果:')
    print(classification_report(y_test, answer_dt))
    print('\n\n朴素贝叶斯分类结果:')
    print(classification_report(y_test, answer_gnb))