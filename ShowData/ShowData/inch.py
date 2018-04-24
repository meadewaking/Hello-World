import os
import numpy as np
from PIL import Image
import cv2  
from matplotlib import pyplot as plt  

def openFile():	#遍历文件夹下所有文件
	path = r'image'
	imgs = []
	for parent,dirnames,filenames in os.walk(path):		#遍历文件夹
		for filename in filenames:
			'''
			f = open(path + "/" + filename,"r")
			content = f.readlines()
			f.close()
			for l in content:
				print(l)
			'''
			img = Image.open(path + "/" + filename)		#打开单张图片
			a = np.array(img)			#图片转成数组
			if a.shape[0] < a.shape[1]:		#判断图片是否倒置，并将其旋转至竖立
				a = np.transpose(a, (1,0,2))	#对倒置图片进行旋转
			im = Image.fromarray(a.astype('uint8'))
			im = im.resize((720, 1280),Image.ANTIALIAS)		#转回图片并对图片大下归一化
			a = np.array(im)		
			imgs.append(a)		#转回数组并添加至列表
	return imgs

def newA4():	#生成空白A4纸大小模板
	a = np.zeros((3508,2480,3), dtype = 'uint8')
	b = [255,255,255] + a
	im = Image.fromarray(b.astype('uint8'))
	im.save('test.jpg')
	
def inch():		#裁剪至一寸大小
	inchs = []
	imgs = openFile()
	for img in imgs:
		a = img[259:824,174:577,:]			#对图片数组进行切片
		im = Image.fromarray(a.astype('uint8'))
		im = im.resize((295, 413),Image.ANTIALIAS)		#转回图片并对图片大下归一化
		a = np.array(im)
		inchs.append(a)
	return inchs
	'''
	a = np.zeros((413,295,3), dtype = 'uint8')
	b = [255,255,255] + a
	im = Image.fromarray(b.astype('uint8'))
	im.save('test.jpg')
	'''

def sub():
	imgs = inch()
	subs = []
	for s in imgs:
		#img = Image.open(s)  
		img = s
		mask = np.zeros(img.shape[:2], np.uint8)  

		bgdModel = np.zeros((1, 65), np.float64)  
		fgdModel = np.zeros((1, 65), np.float64)  
		  
		rect = (20, 20, 267, 450)  
		cv2.grabCut(img, mask, rect, bgdModel, fgdModel, 5, cv2.GC_INIT_WITH_RECT)  #利用grabcut算法进行抠图
		  
		mask2 = np.where((mask == 2) | (mask == 0), 0, 1).astype('uint8')	#将背景标记为0人像标记为1
		img = img * mask2[:, :, np.newaxis]		#背景色标黑
		img[img == 0] = 255		#将背景变为白色
		#img = mask2
		#img += 255 * (1 - cv2.cvtColor(mask2, cv2.COLOR_GRAY2BGR))  
		# plt.imshow(img)  
		# plt.show()
		'''
		img = np.array(img)  
		mean = np.mean(img)  
		img = img - mean  
		img = img * 0.9 + mean * 0.9  
		img /= 255
		  
		plt.imshow(img)  
		#plt.colorbar()
		plt.show()  
		'''
		subs.append(img)

	return subs

def last():
	a = np.zeros((3508,2480,3), dtype = 'uint8')
	b = [255,255,255] + a 		#生成a4纸大小模板
	subs = sub()
	x = 0		#x用作x方向计数
	y = 0		#y用作y方向计数
	for s in subs:
		length = s.shape[0]
		width = s.shape[1]
		for i in range(12):			#每张照片打印12次
			b[y*length:(y+1)*length,x*width:(x+1)*width] = s
			x += 1
			if x % 8 == 0:		#每8个一换行
				x = 0
				y += 1

	im = Image.fromarray(b.astype('uint8'))
	im.save('一寸照.jpg')

'''
subs = sub()
x = 0
for i in subs:
	x += 1
	im = Image.fromarray(i.astype('uint8'))
	im.save('%s.jpg'%x)
'''
last()