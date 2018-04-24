import requests
import re

def getURLs():      #获取可用链接
    f = open('F:\\JAV.txt','r')
    url = f.readlines()         #读取所有链接
    f.close()

    jav = []        #使用链接列表
    x = 0
    for l in url:
        if('javl'in l):          #链接关键词注意变动
            x+=1
            jav.append(l)           #筛选链接
    print("提取链接成功,共提取%d个链接"%x)
    return jav

def getPages(jav):     #获取页面内容
    pages = []      #网页列表
    kv = {'user-agent':'Mozilla/5.0'}       #浏览器表示键值对
    r = requests.Response()
    x = 0
    for l in jav:
        try:
            x+=1
            r = requests.get(l,headers = kv,timeout = 2)      #伪装成浏览器
            r.encoding = r.apparent_encoding
            r.text.encode('utf-8','ignore')
            if len(r.text) == 0:
                print("第%d条链接页面获取失败"%x)
            print("第%d条页面获取成功"%x)
            pages.append(r.text)
        except:
            print("获取第%d条时出现异常，稍后重试"%x)
            x = x - 1
            jav.append(l)
    print()
    return pages

def getIDs(pages):      #获取番号
    ID = []             #番号列表
    reg = r'<td class="text">([A-Z]{2,9}-[0-9]{2,5}.?)</td>'      #正则表达式
    x = 0
    for p in pages:
        x+=1
        filter = re.compile(reg)        #编译正则对象
        id = str(re.findall(filter,p)[0])
        print("第%d条："%x,id)
        ID.append(id)    #提取对象
    
    print("获取番号成功,获取到%d个番号"%x)  
    print()
    return ID

def getResults(ID):     #获取搜索页面
    r = requests.Response
    resultSet = []      #搜索页面列表
    x = 0
    for id in ID:
        try:
            x+=1
            print("正在查询第%d条番号"%x)
            r = requests.get("http://jijibt.net/jiji/" + id + ".html",timeout = 2)    
            r.encoding = r.apparent_encoding
            r.text.encode('utf-8','ignore')
            resultSet.append(r.text)
        except:
            print("获取第%d条时出现异常，稍后重试"%x)
            x = x - 1
            ID.append(id)
    print("获取搜索页面成功")
    print()
    return resultSet

def getMag(resultSet):         #获取磁力链接并写入
    link = []
    trueTitleL = []             #真实名称列表
    reg = r'a href="(magnet:?[^\"]+)" target="_blank"'#匹配结果链接
    ADreg = r'<h3><span>[\s\S]*?</span><a href="http://jijibt.net/ji/[a-zA-Z0-9\S]{25,30}.html" target="_blank"(>[\s\S]*?<)/a></h3>'#过滤广告用正则
    titleReg = r'(<[\s\S]*?>)'
    loop = 0
    for p in resultSet:
        loop+=1
        AD = re.compile(ADreg)
        title = re.findall(AD,p)       #获取结果名称列表
        tr = re.compile(titleReg)       #用于去除html标记
        for i in title:
            subTitle = re.sub(tr,"",i)     #sub函数用以替换所有匹配到的正则项
            trueTitleL.append(subTitle)
        num = len(title)                #获取结果条数
        filter = re.compile(reg)        #编译正则对象
        if len(re.findall(filter,p)) == 0:
            print('第%d条搜索结果为空'%loop)            #处理空结果异常
            continue
        flag = False
        for i in range(num):
            if ((".iso" not in str(trueTitleL[i])) and (".rar" not in str(trueTitleL[i]))\
               and ("QQ" not in str(trueTitleL[i])) and ("qq" not in str(trueTitleL[i])) and\
              ("代购" not in str(trueTitleL[i])) and ("直销" not in str(trueTitleL[i])) and\
             (".exe" not in str(trueTitleL[i])) and ("现货" not in str(trueTitleL[i])) and\
            (len(str(trueTitleL[i])) < 45)):   #过滤广告
                link.append(str(re.findall(filter,p)[i]))
                flag = True
                break
        if ((i == (num-1)) and (not flag)):         #如果条数为最后一条且未被添加，则无可用结果
            print('第%d条无可用结果'%loop)     

        '''        
        if len(re.findall(filter,p)) == 1:
            link.append(str(re.findall(filter,p)[0]))    #提取对象
        else:
            link.append(str(re.findall(filter,p)[1]))       #避免广告优先获取第二条
        '''

    print("获取结果链接成功")
    print()
    '''
    magpage = []        #磁力页面列表
    x = 0
    for l in link:
        try:
            x+=1
            print("获取第%d条结果"%x)
            r = requests.get(l,timeout = 2)     
            r.encoding = r.apparent_encoding
            r.text.encode('utf-8','ignore')
            magpage.append(r.text)
        except:
            print("获取第%d条时出现异常，稍后重试"%x)
            x = x - 1
            link.append(l)
    print()
    print("获取结果页面成功")

    magnet = []
    reg = r'<a href="(magnet:[\s\S]*?)" target="_blank" >magnet:[\s\S]*?</a><br/>'
    for p in magpage:
        filter = re.compile(reg)        #编译正则对象
        magnet.append(str(re.findall(filter,p)[0]))    #提取对象
    print("获取磁力链接成功")
    print()
    '''
    magnet = link
    f = open("F:\\1.txt",'w',encoding='utf-8')
    i = 0
    x = 1
    for mag in magnet:
        i+=1
        f.write(mag+'\n\n')
        if i%20 == 0:                   #每20条结果存为一个文档
            f.close()
            x+=1
            f = open("F:\\%d.txt"%x,'w')
    f.close()
    print("磁力链接写入成功！本次共爬取%d条"%i)

jav = getURLs()         #获取jav链接
pages = getPages(jav)   #获取jav页面
ID = getIDs(pages)      #获取番号
resultSet = getResults(ID)  #获取搜索结果页面
getMag(resultSet)              #获取磁力链接并写入

