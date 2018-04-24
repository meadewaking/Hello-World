import requests

r = requests.Response
url = "http://" + input()
r = requests.get(url)
r.encoding = "utf-8"
print(r.text)
