import requests
import re
 
def getHTMLText(url):               #获取页面
    try:
        r = requests.get(url, timeout=30)
        r.raise_for_status()
        r.encoding = r.apparent_encoding
        return r.text
    except:
        return ""
     
def parsePage(ilt, html):
    try:
        plt = re.findall(r'\"view_price\"\:\"[\d\.]*\"',html)       #获取所有价格
        tlt = re.findall(r'\"raw_title\"\:\".*?\"',html)        #获取所有名称
        for i in range(len(plt)):
            price = float(eval(plt[i].split(':')[1]))       #分割价格
            title = eval(tlt[i].split(':')[1])          #分割名称
            ilt.append([price , title])
            ilt.sort(key = lambda x:x[0])       #key值表示在底层排序时比较的值
    except:
        print("")
 
def printGoodsList(ilt):
    tplt = "{:4}\t{:8}\t{:16}"              #format格式
    print(tplt.format("序号", "价格", "商品名称"))
    count = 0
    for g in ilt:
        count = count + 1
        print(tplt.format(count, g[0], g[1]))
         
def main():
    goods = '书包'        #要获取的商品
    depth = 2           #获取多少个页面
    start_url = 'https://s.taobao.com/search?q=' + goods
    infoList = []
    for i in range(depth):
        try:
            url = start_url + '&s=' + str(44*i)     #整理页面链接
            html = getHTMLText(url)         #获取页面
            parsePage(infoList, html)
        except:
            continue
    printGoodsList(infoList)
     
main()