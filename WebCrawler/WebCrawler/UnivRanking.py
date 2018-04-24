import requests
from bs4 import BeautifulSoup
import bs4
 
def getHTMLText(url):           #获取html页面
    try:
        r = requests.get(url, timeout=30)
        r.raise_for_status()
        r.encoding = r.apparent_encoding
        return r.text
    except:
        return ""           #发生异常则返回空字符串
 
def fillUnivList(ulist, html):          #筛选信息
    soup = BeautifulSoup(html, "html.parser")
    for tr in soup.find('tbody').children:
        if isinstance(tr, bs4.element.Tag):     #筛选出标签
            tds = tr('td')
            ulist.append([tds[0].string, tds[1].string, tds[3].string])
 
def printUnivList(ulist, num):
    tplt = "{0:^10}\t{1:{3}^10}\t{2:^10}"       #为排版整齐，选用中文空格填充
    print(tplt.format("排名","学校名称","总分",chr(12288))) #chr(12288)为中文空格
    for i in range(num):
        u=ulist[i]
        print(tplt.format(u[0],u[1],u[2],chr(12288)))
     
def main():
    uinfo = []
    url = 'http://www.zuihaodaxue.cn/zuihaodaxuepaiming2016.html'
    html = getHTMLText(url)
    fillUnivList(uinfo, html)
    printUnivList(uinfo, 20) # 只获取前20的信息
main()