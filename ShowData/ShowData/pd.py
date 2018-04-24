import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
'''
a = pd.Series(np.arange(10))        #series是一种带索引的一维数组
print(a)


a = pd.Series([9, 8, 7, 6], index = ['a', 'b', 'c', 'd'])       #索引可以自定
print(a)


a = pd.Series(25, index = ['a', 'b', 'c', 'd'])       #也可以用标量建立
print(a)


a = pd.Series({'a':9, 'b':8, 'c':7}, index = ['c', 'a', 'b', 'd'])       #用字典建立时索引会自动匹配key
print(a)
print(a.index)          #显示索引
print(a.values)         #显示值（因为series类型只有一维）
print(a[['b','c']])     #自定索引和默认索引并存，但不能混用
print(a[0])


a = pd.Series([1, 2, 3], index = ['c', 'd', 'e'])
b = pd.Series([9, 8, 7, 6], index = ['a', 'b', 'c', 'd'])       
print(a+b)          #执行运算操作时会自动对齐索引，但是任何有空值参与的运算都会返回空值


a = pd.Series([9, 8, 7, 6], index = ['a', 'b', 'c', 'd'])
a.name = "Series对象"         #索引和series都可以命名
a.index.name = "索引"
print(a)


a = pd.DataFrame(np.arange(10).reshape(2,5))        #DataFrame是一种带索引的二维数组
dt = {'one':pd.Series([1, 2, 3],index = ['a', 'b', 'c']),       #也可以由多个series建立
     'two':pd.Series([9, 8, 7, 6], index = ['a', 'b', 'c', 'd'])}
a = pd.DataFrame(dt)
dt = {"one":[1, 2, 3, 4],"two":[9, 8, 7, 6]}        #可以由列表字典创建，列表长度必须要相等
a = pd.DataFrame(dt)
print(a)


dl = {'城市': ['北京', '上海', '广州', '深圳', '沈阳'],
      '环比': [101.5, 101.2, 101.3, 102.0, 100.1],
      '同比': [120.7, 127.3, 119.4, 140.9, 101.4],
      '定基': [121.4, 127.8, 120.0, 145.5, 101.6]}
a = pd.DataFrame(dl, index = ['c1', 'c2', 'c3', 'c4', 'c5'])
a = a.reindex(index = ['c1', 'c2', 'c3', 'c4', 'c5'])       #在建立dataframe时行列无序存储，有需求需要重排索引
a = a.reindex(columns = ['城市', '环比', '同比', '定基'])
print(a)
newc = a.columns.insert(4,'新增')             #通过insert方法新增一列
newd = a.reindex(columns = newc,fill_value = 200)   #fill_color来填充值
print(newd)
nc = a.columns.delete(2)
ni = a.index.insert(5, 'c6')        #index一定要是递增或递减的值，不能使用无规律值
nd = a.reindex(columns = nc)
nd = nd.reindex(index = ni,method = 'ffill')    #ffill前值填充方法要与index连用
print(nd)
print(nd.drop('c6'))
print(nd.drop('环比', axis = 1))      #通过drop删除元素默认轴为0


a = pd.DataFrame(np.arange(12).reshape(3,4))
b = pd.DataFrame(np.arange(20).reshape(4,5))
print(a+b)          #与series一样自动对齐，并默认填补为空值
print( b.add(a,fill_value = 100))       #使用算数方法可以修改填补值
c = pd.Series(np.arange(4))
print(b-c)          #不同纬度之间采用广播运算
print(b.sub(c,axis = 0))    #采用算数方法可以修改广播运算的轴
print(b>c)      #与ndarray一样返回广播运算后的布尔元素组成的类型


a = pd.DataFrame(np.arange(20).reshape(4,5), index = ['c', 'a', 'b', 'd'])
print(a.sort_index(axis = 0,ascending = True))     #对索引排序默认操作轴为0默认升序
print(a.sort_values('a',axis = 1,ascending = False))    #对数据排序，选取1轴中的index a，注意：遇到空值会放在末尾无论升降序


a = pd.DataFrame(np.arange(20).reshape(4,5), index = ['c', 'a', 'b', 'd'])
print(a.describe())         #返回同样的数据类型，默认统计1轴，统计信息包括（count（非空数据个数），mean（均值），std（标准差），min（最小值），各个占位的值，max（最大值））
print(a.describe()[2]['count'])     #可以根据索引调用
print(a.cumsum())       #前n项累加值
print(a.cumprod())      #前n项累乘值
print(a.cummin())       #前n项最小值值
print(a.cummax())       #前n项最大值值
print(a.rolling(2).sum())       #滚动计算，对前2项求和
'''

hprice = pd.Series([3.04, 22.93, 12.75, 22.6, 12.33], index = ['2008', '2009', '2010', '2011', '2012'])
m2 = pd.Series([8.18, 18.38, 9.13, 7.82, 6.69], index = ['2008', '2009', '2010', '2011', '2012'])
print(hprice.corr(m2))      #求出两组数据的pearson系数（0.8-1：极强相关，0.6-0.8：强相关，0.4-0.6：中等相关，0.2-0.4：弱相关，0.0-0.2：极弱相关或不相关）
plt.plot(hprice)            #同ndarray对象一样，series和dataframe对象可以直接被matplotlib绘制
plt.plot(m2)
plt.show()
