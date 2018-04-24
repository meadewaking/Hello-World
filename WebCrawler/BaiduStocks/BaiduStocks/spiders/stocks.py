# -*- coding: utf-8 -*-
import scrapy
import re
#spider模块，负责提交请求

class StocksSpider(scrapy.Spider):
    name = 'stocks'
    start_urls = ['http://quote.eastmoney.com/stocklist.html']			#初始的爬取链接

    def parse(self, response):
    	for href in response.css('a::attr(href)').extract():		#用css selecter 定位目标等效于beautiful soup返回值为列表
    		try:
    			stock = re.findall(r"[s][hz]\d{6}",href)[0]			#正则获取股票名称
    			url = 'https://gupiao.baidu.com/stock/' + stock + '.html'		#整理股票信息链接
    			yield scrapy.Request(url, callback = self.parse_stock)			#request请求目标并返回response至parse_stock方法
    		except :
    			continue

    def parse_stock(self,response):
    	infoDict = {}				#股票信息字典
    	stockInfo = response.css('.stock-bets')			
    	name = stockInfo.css('.bets-name').extract()[0]				#获取股票名称列表
    	keyList = stockInfo.css('dt').extract()					#获取信息元素名称列表
    	valueList = stockInfo.css('dd').extract()				#获取信息元素值列表
    	for i in range(len(keyList)):
    		key = re.findall(r'>.*</dt',keyList[i])[0][1:-5]		#获取信息元素名称
    		try:
    			val = re.findall(r'\d+\.?.*</dd>', valueList[i])[0][0:-5]			#获取信息元素值
    		except:
    			val = '--'
    		infoDict[key] = val 			#保存入字典

    	infoDict.update(							#update方法向字典中添加子字典的值
    		{'股票名称':re.findall('\s.*\(',name)[0].split()[0] + re.findall('\>.*\<', name)[0][1:-1]}
    		)
    	yield infoDict
    		

