import requests
from bs4 import BeautifulSoup
import traceback
import re
 
def getHTMLText(url, code="utf-8"):         #获得网页内容
    try:
        r = requests.get(url)
        r.raise_for_status()
        r.encoding = code
        return r.text
    except:
        return ""
 
def getStockList(lst, stockURL):        #获取股票名称列表
    html = getHTMLText(stockURL, "GB2312")      #获取股票名称页面
    soup = BeautifulSoup(html, 'html.parser')       #解析成beautifulsoup对象
    a = soup.find_all('a')      #找到所有<a>标签
    for i in a:
        try:
            href = i.attrs['href']      #找到每个标签下的href属性
            lst.append(re.findall(r"[s][hz]\d{6}", href)[0])    #用正则表达式获取找到名称
        except:
            continue
 
def getStockInfo(lst, stockURL, fpath):     #获取股票信息
    count = 0
    for stock in lst:
        url = stockURL + stock + ".html"        #整理要获取的股票信息链接
        html = getHTMLText(url)
        try:
            if html=="":
                continue
            infoDict = {}
            soup = BeautifulSoup(html, 'html.parser')       #解析html
            stockInfo = soup.find('div',attrs={'class':'stock-bets'})       #找到属性为class=stock-bets的<div>标签并获取
 
            name = stockInfo.find_all(attrs={'class':'bets-name'})[0]   #在stockInfo中找到所有属性为class=bets-name的<div>标签并获取
            infoDict.update({'股票名称': name.text.split()[0]})     #获取到名称并记录到字典中
             
            keyList = stockInfo.find_all('dt')          #在stockInfo中找到所有的<dt>标签并获取
            valueList = stockInfo.find_all('dd')        #在stockInfo中找到所有的<dd>标签并获取
            for i in range(len(keyList)):
                key = keyList[i].text
                val = valueList[i].text
                infoDict[key] = val                 #将所获得的股票信息整理到字典中
             
            with open(fpath, 'a', encoding='utf-8') as f:       #创建外部文件并写入
                f.write( str(infoDict) + '\n' )
                count = count + 1
                print("\r当前进度: {:.2f}%".format(count*100/len(lst)),end="")      #\r可以实现动态显示
        except:
            count = count + 1
            print("\r当前进度: {:.2f}%".format(count*100/len(lst)),end="")
            continue
 
def main():
    stock_list_url = 'http://quote.eastmoney.com/stocklist.html'    #股票名称    
    stock_info_url = 'https://gupiao.baidu.com/stock/'      #股票信息（由于东方财富的股票信息是由js脚本生成所以信息从百度股票获取）
    output_file = 'D:/BaiduStockInfo.txt'       #信息保存在外部文件
    slist=[]
    getStockList(slist, stock_list_url)     #获取股票名称列表
    getStockInfo(slist, stock_info_url, output_file)        #获取股票信息
 
main()