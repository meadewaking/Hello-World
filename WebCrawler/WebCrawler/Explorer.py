import webbrowser
from selenium import webdriver

browser = webdriver.Chrome('D:\Program Files (x86)\Python34\selenium\webdriver\chromedriver.exe')
print(browser.current_url)
