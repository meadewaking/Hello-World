from selenium import webdriver
from selenium.webdriver.firefox.options import Options      #火狐驱动选项
from bs4 import BeautifulSoup
import re

url = 'http://seo.chinaz.com/?q=www.teachercn.com'
options = Options()
options.add_argument('-headless')           #静默模式启动
driver = webdriver.Firefox(firefox_options=options)
driver.set_page_load_timeout(10)         #超时设置（5秒）
try:
    driver.get(url)
except:
    pass

def get():
    info = []       #全部信息列表
    #page = driver.page_source       #获取返回的网页源码（包括js生成数值）
    worldRank = driver.find_element_by_xpath("//*[@id='worldtop']/a/font").text   #通过xpath获取元素的值
    info.append({"整站世界排名":worldRank})
    avgIP = driver.find_element_by_xpath("//*[@id='ipavg']/a/font").text 
    info.append({"整站日均IP":avgIP})
    avgPV = driver.find_element_by_xpath("//*[@id='pvavg']/a/font").text 
    info.append({"整站日均PV":avgPV})
    Bweight = getWeight("//*[@id='seoinfo']/div/ul/li[2]/div[2]/div[1]/p/a/img")
    info.append({"百度权重":Bweight})
    Sweight = getWeight("//*[@id='rank360']/img")
    info.append({"360权重":Sweight})
    Gweight = getWeight("//*[@id='pr']/img")
    info.append({"Google权重":Gweight})
    backLink = driver.find_element_by_xpath("//*[@id='outlink1']/a/font").text 
    info.append({"反链数":backLink})
    flow = driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/ul/li[3]/div[1]/a").text 
    info.append({"流量预计":flow})
    indexN = driver.find_element_by_xpath("//*[@id='seo_BaiduSiteIndex']/a/font").text 
    info.append({"索引量":indexN})
    BincludeN = driver.find_element_by_xpath("//*[@id='detailcontent']/li[2]/div[2]").text 
    info.append({"百度收录":BincludeN})
    GincludeN = driver.find_element_by_xpath("//*[@id='detailcontent']/li[2]/div[4]").text 
    info.append({"Google收录":GincludeN})
    driver.close()      #关闭浏览器
    print(info)

def getWeight(xpath):
    weight = driver.find_element_by_xpath(xpath).get_attribute("src")
    reg = r'(\d).gif'
    ft = re.compile(reg)
    Tweight = re.findall(ft,weight)
    if len(Tweight) == 0:
        Tweight = "暂无"
    else:
        Tweight = Tweight[0]
    return Tweight

get()