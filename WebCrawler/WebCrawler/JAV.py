import requests
import re

def getKV():
    kv = {'user-agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36','Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8','Accept-Encoding':'gzip, deflate, sdch, br','Accept-Language':'zh-CN,zh;q=0.8','Upgrade-Insecure-Requests':'1','Host':'btso.pw'
    }
    return kv

def getCOOKIES():
    cookie = "AD_enterTime=1514466069; AD_jav_b_SM_T_728x90=0; AD_javu_b_SM_T_728x90=0; AD_wav_b_SM_T_728x90=0; AD_wwwp_b_SM_T_728x90=0; AD_adst_b_SM_T_728x90=1; AD_javu_b_SM_B_728x90=1; opti-userid=69dbb14b-28ef-4802-8bec-2c61cca4ecc8; GED_PLAYLIST_ACTIVITY=W3sidSI6Ik03VEQiLCJ0c2wiOjE1MTQ0NjYxNzYsIm52IjowLCJ1cHQiOjE1MTQ0NjYxMTQsImx0IjoxNTE0NDY2MTE0fV0.; opti-position=20; AD_exoc_b_SM_T_728x90=1; AD_clic_b_POPUNDER=2; AD_adca_b_SM_T_728x90=1; AD_wav_b_SM_B_728x90=2; AD_popa_b_POPUNDER=1; _ga=GA1.2.1027113242.1514466069; _gid=GA1.2.793912636.1514466069; a=9z09dt197kanzojbxahhj4s9hx6ff8b1; token=1514466661,1514466679,ec8bed25c1c284cb0e5a918e4e828d74"
    cookies={}
    for line in cookie.split(';'): 
        name,value=line.strip().split('=',1)    #伪装cookies
        cookies[name]=value 

    return cookies

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
            r = requests.get(l,headers = kv)      #伪装成浏览器
            r.encoding = r.apparent_encoding
            r.text.encode('utf-8','ignore')
            if len(r.text) == 0:
                print("第%d条链接页面获取失败"%x)
            print("第%d条页面获取成功"%x)
            pages.append(r.text)
        except:
            print("获取%d条时出现异常"%x)
    print()
    return pages

def getIDs(pages):      #获取番号
    ID = []             #番号列表
    reg = r'<td class="text">([A-Z]{2,6}-[0-9]{2,5}.?)</td>'      #正则表达式
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
    kv = getKV()
    cookies = getCOOKIES()

    x = 0
    for id in ID:
        r = requests.get("https://btso.pw/search/"+id,headers = kv,cookies=cookies)     #bt站反爬虫较强，需伪装cookie和全部的request信息
        r.encoding = r.apparent_encoding
        r.text.encode('utf-8','ignore')
        resultSet.append(r.text)
        x+=1
        print("正在查询第%d条番号"%x)
    print("获取搜索页面成功")
    print()
    return resultSet

def getMag(resultSet):         #获取磁力链接并写入
    link = []
    reg = r'href="(https://btso.pw/magnet/detail/hash/[A-Z0-9]{38,42})"'#匹配结果链接
    ADreg = r'<div class="col-xs-12 col-sm-8 col-lg-9 file">([\s\S]*?)</div>'#过滤广告用正则
    loop = 0
    for p in resultSet:
        loop+=1
        AD = re.compile(ADreg)
        title = re.findall(AD,p)       #获取结果名称
        num = len(title)                #获取结果条数
        filter = re.compile(reg)        #编译正则对象
        if len(re.findall(filter,p)) == 0:
            print('第%d条搜索结果为空'%loop)            #处理空结果异常
            continue
        flag = False
        for i in range(num):
            if ("直销" not in str(title[i])) and (len(str(title[i])) < 55):   #过滤广告
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

    magpage = []        #磁力页面列表
    kv = getKV()
    cookies = getCOOKIES()
    x = 0
    for l in link:
        r = requests.get(l,headers = kv,cookies=cookies)     #bt站反爬虫较强，需伪装cookie和全部的request信息
        r.encoding = r.apparent_encoding
        r.text.encode('utf-8','ignore')
        magpage.append(r.text)
        x+=1
        print("获取第%d条结果"%x)
    print()
    print("获取结果页面成功")

    magnet = []
    reg = r'<textarea class="magnet-link" readonly>(magnet:?[^\"]+)</textarea>'
    for p in magpage:
        filter = re.compile(reg)        #编译正则对象
        magnet.append(str(re.findall(filter,p)[0]))    #提取对象
    print("获取磁力链接成功")
    print()

    f = open("F:\\1.txt",'w')
    i = 0
    x = 1
    for mag in magnet:
        i+=1
        f.write(mag+'\n\n')
        if i%10 == 0:                   #每10条结果存为一个文档
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

