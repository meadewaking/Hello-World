import requests
import os

url = "http://image.nationalgeographic.com.cn/2017/0401/20170401110752902.jpg"
root = "F:\\"
path = root + url.split('/')[-1]
try:
    if not os.path.exists(root):
        os.mkdir(root)
    if not os.path.exists(path):
        r = requests.Response
        r = requests.get(url)
        with open(path,'wb') as f:      #with会抛出异常
            f.write(r.content)      #conten为文件二进制形式
            f.close()
            print("文件保存成功")
    else:
        print("文件已存在")
except:
    print("爬取失败")