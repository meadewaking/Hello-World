import numpy as np
'''
a = np.arange(10)       #生成自增长数组
print(a)

a = np.ones((3,6))      #生成3行6列的全为1的矩阵
print(a)

a = np.zeros((3,6), dtype = np.int32)       #生成全0的3行6列的矩阵元素类型为int32
print(a)

a = np.eye(5)           #生成5阶单位矩阵
print(a)

a = np.ones((2,3,4))
print(a)
print(a.shape)          #n维数组的形状

a = np.zeros_like(a)    #创建全0同形数组
print(a)
a = np.ones_like(a)     #创建全1同形数组
print(a)
a = np.full_like(a,5)   #创建元素全为5同形数组
print(a)

a = np.linspace(1,10,4)     #根据起止位置等间距生成数组
print(a)
a = np.linspace(1,10,4,endpoint = False)     #endpoint可以控制末尾值是否包含
print(a)
b = np.zeros_like(a)
c = np.concatenate((a,b))               #concatenate可以组合两个数组
print(c)   
     
a = np.ones((2,3))
print(a)
b = a.reshape((3,2))            #reshape重塑数组不改变原数组
print(a)
print(b)
a.resize((3,2))         #resize改变原数组
print(a)
b = a.flatten()         #降维至一维
print(b)


a = np.ones((2,3), dtype = np.int)
print(a)
b = a.astype(np.float)      #元素类型转换
print(b)
c = a.tolist()          #数组转换为列表
print(c)
   

a = np.arange(24).reshape((2,3,4))
print(a.mean())         #取平均值
print(a / a.mean())
a = np.square(a)        #取平方
print(a)
a = np.modf(a)        #将整数与小数分裂并返回
print(a)
a = np.sqrt(a)        #取平方根
print(a)
b = a/a.mean()
c = np.maximum(a, b)    #取最大值
print(c)
print(a>b)      #返回布尔值数组


a = np.arange(24).reshape((2,3,4))
print(a)
print(a[1,2,3])         #按每个维度的值索引
print(a[:,1,-3])        #忽略第一个维度，截取其余维度位置为1和-3的值
print(a[:,1:2,:])       #截取的规则遵循起始：结束（不包括）：步长


a = np.arange(24).reshape((2,3,4))
np.save("F:\\test.npy",a)               #numpy库自带的save和load文件会被保存成特有的npy格式以二进制格式存储
a = np.load("F:\\test.npy")
print(a)


a = np.arange(100).reshape((5,20))
np.savetxt('F:\\a.csv', a, fmt = '%.1f', delimiter = ',')       #csv文件可以有效存取二维以内数组，并可以直接被excel打开,以明文存储
a = np.loadtxt('F:\\a.csv',delimiter = ',')
print(a)


a = np.arange(100).reshape((5,10,2))
a.tofile('F:\\a.dat',format = '%d')                 #高维数组的通用存储必须要知道元素类型和形状
a = np.fromfile('F:\\a.dat',dtype = np.int).reshape((5,10,2))
print(a)


a = np.random.rand(3,4,5)           #生成均匀分布的0-1的浮点数
print(a)
a = np.random.randn(3,4,5)          #生成标准正态分布的0-1的浮点数
print(a)
np.random.seed(10)          #取随机数种子
a = np.random.randint(100,200,(3,4))    #随机整数生成（low，high，shape）
print(a)
np.random.shuffle(a)            #按第一维度打乱顺序，并改变原数组
np.random.permutation(a)        #按第一维度打乱顺序，不改变原数组

a = np.random.randint(0,100,(10))
a = np.random.choice(a,(3,2),replace = False,p = a/np.sum(a))       #choice只能从一维数组中取值（取值数组，形状，是否允许重复默认为true，p为每个值取到的概率）
print(a)


a = np.random.uniform(0,10,(3,4))       #产生均匀分布数组（low，high，shape）
print(a)
a = np.random.normal(10,5,(3,4))        #产生正态分布数组(μ，σ，shape)
print(a)
a = np.random.poisson(10,5,(3,4))        #产生正态泊松数组(λ，shape)
print(a)


a = np.arange(15).reshape(3,5)
print(np.sum(a))            #求和函数
print(np.mean(a,axis = 1))      #第二维度取平均，axis为要操作的维度
print(np.average(a,axis = 0,weights = [10,5,1]))        #取加权平均，这里的axis与mean（）中相反
print(np.std(a))        #取标准差
print(np.var(a))        #取方差


a = np.arange(15,0,-1).reshape(3,5)
print(np.max(a))            #取最大值
print(np.argmax(a))         #取最大值序数（一维）
print(np.unravel_index(np.argmax(a),a.shape))       #取最大值序数（一维转换为多维）
print(np.ptp(a))        #计算极差
print(np.median(a))     #取中值
''' 

a = np.random.randint(0,20,(5))
print(np.gradient(a))       #中间值的梯度为（右值-左值）/2，边缘值的梯度为（右值/本值-左值/本值）/1
a = np.random.randint(0,50,(3,5))
print(np.gradient(a))       #多个维度的梯度按照维度的顺序分别计算梯度并返回每个维度的梯度数组，梯度在多媒体处理中找边缘十分有效