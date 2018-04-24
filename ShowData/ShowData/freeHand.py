from PIL import Image
import numpy as np
 
#手绘效果实现的原理为手绘强调边界加强向同色调趋于白色，加强与周围像素点梯度较大的边界，削弱与周围像素点梯度较小的部分

a = np.asarray(Image.open('1.jpg').convert('L')).astype('float')
 
depth = 10.                      # (0-100)
grad = np.gradient(a)             #取图像灰度的梯度值
grad_x, grad_y = grad               #分别取横纵图像梯度值
grad_x = grad_x*depth/100.          #重整x方向的灰度梯度
grad_y = grad_y*depth/100.          #重整y方向的灰度梯度
A = np.sqrt(grad_x**2 + grad_y**2 + 1.)     #将单位各方向梯度归一化，这里本无第三个方向上的梯度，后面加的1代表图像整体灰度为了迎合后面的光源权重处理
uni_x = grad_x/A
uni_y = grad_y/A
uni_z = 1./A

#此部分为按照el-az-dp模型计算出的光源权重值，视不同图片对其作出调整以获得更好效果
vec_el = np.pi/2.2                   # 光源的俯视角度，弧度值
vec_az = np.pi/4.                    # 光源的方位角度，弧度值
dx = np.cos(vec_el)*np.cos(vec_az)   #光源对x 轴的影响
dy = np.cos(vec_el)*np.sin(vec_az)   #光源对y 轴的影响
dz = np.sin(vec_el)              #光源对z 轴的影响
 
b = 255*(dx*uni_x + dy*uni_y + dz*uni_z)     #光源归一化
b = b.clip(0,255)           #修正溢出
 
im = Image.fromarray(b.astype('uint8'))  #重构图像
im.save('2.jpg')