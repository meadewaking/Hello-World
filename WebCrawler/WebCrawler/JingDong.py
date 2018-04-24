import requests

url = "https://item.jd.com/4088579.html"

kv = {'user-agent':'Mozilla/5.0'}       #浏览器表示键值对
r = requests.Response()
r = requests.get(url,headers = kv)      #伪装成浏览器
r.raise_for_status()
#r.encoding = r.apparent_encoding       #对response对象重编码，经常会出现不能编译

print(r.text[:1000])        #text会自行猜测编码