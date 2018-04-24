import numpy as np
from sklearn.linear_model import Ridge      #引入岭回归模型
from sklearn import cross_validation        #引入交叉验证
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures        #引入多项式回归模型

data = np.loadtxt('data.csv',delimiter = ',')       #读取文件

x = data[:,:4]      #保存时间数据
y = data[:,4]       #保存交通流量
poly = PolynomialFeatures(6)          #6次多项式回归
X = poly.fit_transform(x)

train_set_x, test_set_x, train_set_y, test_set_y = cross_validation.train_test_split(X,y,test_size=0.3,random_state=0)  #交叉验证分割比为0.3
clf = Ridge(alpha=1.0,fit_intercept=True)       #岭回归
clf.fit(train_set_x,train_set_y)
print(clf.score(test_set_x,test_set_y))        #评价拟合度

start = 200
end = 300
y_pre = clf.predict(X)
time = np.arange(start,end)
plt.plot(time,y[start:end],'b',label='real')        #绘制真实数据
plt.plot(time,y_pre[start:end],'r',label='predict')     #绘制拟合曲线

plt.legend(loc='upper left')        #左上角显示注释
plt.show()