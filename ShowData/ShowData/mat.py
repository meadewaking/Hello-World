import matplotlib.pyplot as plt
import matplotlib
import numpy as np

'''
plt.plot([3,1,4,5,2])           #只有一维数组时自动生成x，y轴
plt.ylabel("test")              #y轴标题
plt.xlabel("test")              #x轴标题
plt.savefig("test",dpi = 600)   #保存为文件，默认为png
plt.show()                      #展示


plt.plot([0,2,4,6,8],[3,1,4,5,2])       #当有两组数组时变成对应点的坐标值
plt.axis([0,10,0,8])                    #axis（【x轴起始，x轴终止，y轴起始，y轴终止】）
plt.show()


def f(t):
    return np.exp(-t) * np.cos(2*np.pi*t)       #能量衰减函数

a = np.arange(0.0, 5.0, 0.02)
plt.subplot(211)            #选定绘图区域划分为2行1列中第1区域
plt.plot(a,f(a))
plt.subplot(2,1,2)          #可以用，分割
plt.plot(a,np.cos(2*np.pi*a),'r--')         #r为red，--表示虚线
plt.show()


a = np.arange(10)
plt.plot(a,a*1.5, a,a*2.5, a,a*3.5, a,a*4.5)        #绘制多条曲线时matplotlib自动选取颜色加以区分
plt.show()


a = np.arange(10)
plt.plot(a,a*1.5,'go-', a,a*2.5,'rx', a,a*3.5,'*', a,a*4.5,'b-')        #不同的符号用以标识不同的曲线，也可以使用color等关键字
plt.show()


a = np.arange(0.0, 5.0, 0.02)
plt.xlabel("横轴：时间",fontproperties = "SimHei",fontsize = 20)         #matplotib不支持中文需要在用到中文时自定义字体
plt.ylabel("纵轴：振幅",fontproperties = "SimHei",fontsize = 20,color = "green")
plt.title(r'正弦：$y=cos(2 \pi x)$',fontproperties = "SimHei",fontsize = 25)       #为绘图添加标题，$$中使用的是LaTeX语言
plt.annotate(r'$\mu = 100$',xy = (2,1),xytext = (3,1.5),\
    arrowprops = dict(facecolor = "black",shrink = 0.1,width = 2))  #绘制注释，xy为箭头指向位置，xytext为文本显示位置，arrowprops为字典类型（facecolor设置颜色，shrink为指向位置的留空缩进，width为箭头宽度）
plt.plot(a,np.cos(2*np.pi*a),'r--')         
plt.axis([-1,6,-2,2])
plt.grid(True)          #绘制网格
plt.show()

gs = matplotlib.gridspec.GridSpec(3,3)          #还可以用Gridspec类划分区域
ax1 = plt.subplot(gs[0,:])              #关于选取时：的使用，使用时选取的是点而不是区域
ax2 = plt.subplot(gs[1,:-1])
ax3 = plt.subplot(gs[1:,-1])
ax4 = plt.subplot(gs[2,0])
ax4 = plt.subplot(gs[2,1])
plt.show()


labels = "1","2","3","4"
sizes = [15,30,45,10]
explode = [0,0.1,0,0]

plt.pie(sizes,explode = explode,labels = labels,              
    autopct = '%1.1f%%',shadow = False,startangle = 90)    #绘制饼图（相对大小，向外突出比例，比例精度，是否有阴影，起始绘制角度）
plt.axis('equal')       #等轴绘制
plt.show()


np.random.seed(0)
mu,sigma = 100,20
a = np.random.normal(mu, sigma, size = 100)         #正态分布数组
plt.hist(a, 25, normed = 1, histtype = 'stepfilled', 
         facecolor = 'b', alpha = 0.75)         #绘制直方图（数组，绘制个数，是否以概率格式显示，去除中间线，颜色，透明度）
plt.title('直方图', fontproperties = 'SimHei')
plt.show()


N = 20
theta = np.linspace(0.0, 2 * np.pi, N, endpoint = False)        #起始绘制角度
radii = 10 * np.random.rand(N)      #绘制高度
width = np.pi/4 * np.random.rand(N)     #绘制宽度

ax = plt.subplot(111,projection = 'polar')      #面向对象建立极坐标图
bars = ax.bar(theta, radii, width = width, bottom = 0.0)        #bottom为底部绘制起始位置

for r, bar in zip(radii, bars):
    bar.set_facecolor(plt.cm.viridis(r / 10.))      #每一个绘制元素上色
    bar.set_alpha(0.5)
plt.show()
'''

ax = plt.subplot()
ax.plot(10*np.random.randn(100), 10*np.random.randn(100), 'o')      #绘制散点图
plt.show()