from django.shortcuts import render

# Create your views here.

def helloTime(request):
    return render(request,"time.html")      #render为打包函数可以返回请求页面