from django.shortcuts import render
from datetime import datetime

# Create your views here.
def msgproc(request):
    datalist = []
    if request.method == "POST":
        username = request.POST.get("username",None)      
        password = request.POST.get("password",None)      
        time = datetime.now()                       
        d = {"username":username, "password":password\
             , "time":time}          
        datalist.append(d)
    return render(request, "index.html", {"data":datalist})