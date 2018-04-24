import requests

keyword = input("输入关键词：")
try:
    kv = {'wd':keyword}
    r = requests.Response
    r = requests.get("http://www.baidu.com/s",params = kv)      #params关键字向url中添加值
    print(r.request.url)
    r.raise_for_status()
    print(len(r.text))
except:
    print("爬取失败")