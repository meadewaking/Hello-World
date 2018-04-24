import numpy as np
import matplotlib.pyplot as plt
from scipy.io import wavfile
 
rate_h, hstrain= wavfile.read(r"H1_Strain.wav","rb")    #读取波形文件，返回速率和数值
rate_l, lstrain= wavfile.read(r"L1_Strain.wav","rb")
reftime, ref_H1 = np.genfromtxt('wf_template.txt').transpose() #读取模板信息，返回
 
htime_interval = 1/rate_h       #速率取导数求出时间间隔
ltime_interval = 1/rate_l
fig = plt.figure(figsize=(12, 6))       #创建绘图空间
 
#绘制H1
htime_len = hstrain.shape[0]/rate_h     #计算横轴长度
htime = np.arange(-htime_len/2, htime_len/2 , htime_interval)   #根据时间间隔生成时间数列
plth = fig.add_subplot(221)             #选定区域后开始绘制
plth.plot(htime, hstrain, 'y')
plth.set_xlabel('Time (seconds)')
plth.set_ylabel('H1 Strain')
plth.set_title('H1 Strain')
#绘制L1
ltime_len = lstrain.shape[0]/rate_l
ltime = np.arange(-ltime_len/2, ltime_len/2 , ltime_interval)
pltl = fig.add_subplot(222)
pltl.plot(ltime, lstrain, 'g')
pltl.set_xlabel('Time (seconds)')
pltl.set_ylabel('L1 Strain')
pltl.set_title('L1 Strain')
#绘制引力波模板
pltref = fig.add_subplot(212)
pltref.plot(reftime, ref_H1)
pltref.set_xlabel('Time (seconds)')
pltref.set_ylabel('Template Strain')
pltref.set_title('Template')
fig.tight_layout()      #修正边缘
 
plt.savefig("Gravitational_Waves_Original.png")     #将绘制好的图像保存
plt.show()
plt.close(fig)