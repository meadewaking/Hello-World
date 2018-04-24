import requests
url = "http://www.ip138.com/ips138.asp?ip="
try:
    r = requests.Response
    r = requests.get(url+input("请输入IP地址："))
    r.raise_for_status()
    r.encoding = r.apparent_encoding
    print(r.text[-2000:-1000])
except:
    print("爬取失败")