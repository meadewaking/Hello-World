from PIL import Image
import numpy as np

#负片效果
a = np.array(Image.open("F:\\1.jpg"))           #将图片转换为矩阵
b = [255,255,255]-a                             #对每个像素进行变换（负片效果）
im = Image.fromarray(b.astype('uint8'))         #对矩阵重编码（RGB模式的像素存储为三个数字的unit8格式）后转换会图片
im.save("F:\\2.jpg")

a = np.array(Image.open("F:\\1.jpg").convert('L'))      #convert（'L'）将图片数组转换为灰度模式，弧度模式下rgb的三组数字将变为一组
b = (100/255)*a+150                     #灰度区间变换一
im = Image.fromarray(b.astype('uint8'))
im.save("F:\\3.jpg")

b = 255*(a/255)**2                      #灰度区间变换二
im = Image.fromarray(b.astype('uint8'))
im.save("F:\\4.jpg")

