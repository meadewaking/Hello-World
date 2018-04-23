#coding=utf-8
import re
import urllib       #添加url库
from urllib import request

def getHtml(url):           #声明gethtml函数    #获取页面内容
    page = request.urlopen(url)         #打开链接
    html = page.read()                  #读取页面内容
    return html     

def getImg(html):   #获取图片
    reg = r'<img .+src="(.+\.jpg)"'     #正则表达式
    html = html.decode('UTF-8','ignore')    #将网页转码为UTF-8编码，忽略不可转码字段
    imgre = re.compile(reg)     #编译reg为正则表达式对象
    imglist = re.findall(imgre,html)    #读取html中的正则对象
    
    x = 0
    for imgurl in imglist:
        request.urlretrieve(imgurl,'f:\\%s.jpg' % x)        #将集合中的图片下载到本地
        x+=1
    
    return imglist  

url = "http://" + input("请输入网址：")      #输入网址

html = getHtml(url)        #接受网页内容
jpg = getImg(html)      #接受图片集合

print (jpg)            #输出网页
'''
f = open("D:\\test.html",'wb')      #打开并创建文件
f.write(jpg)       #文件写入
f.close()       #关闭文件
'''