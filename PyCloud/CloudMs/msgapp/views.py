from django.shortcuts import render
from datetime import datetime

# Create your views here.
def msgproc(request):
    datalist = []
    if request.method == "POST":
        userA = request.POST.get("userA",None)      #获取发送方
        userB = request.POST.get("userB",None)      #获取接收方
        msg = request.POST.get("msg",None)          #获取消息
        time = datetime.now()                       #设定发送时间
        with open('msgdata.txt', 'a+') as f:        #打开外部文件保存数据
            f.write("{}--{}--{}--{}--\n".format(userB, userA,\
                            msg, time.strftime("%Y-%m-%d %H:%M:%S")))
    if request.method == "GET":
        userC = request.GET.get("userC", None)      #获取接收方
        if userC != None:
            with open("msgdata.txt", "r") as f:
                cnt = 0
                for line in f:
                    linedata = line.split('--')
                    if linedata[0] == userC:
                        cnt = cnt + 1
                        d = {"userA":linedata[1], "msg":linedata[2]\
                             , "time":linedata[3]}          #以字典形式保存一条数据
                        datalist.append(d)
                    if cnt >= 10:               #只显示最近10条信息
                        break
    return render(request, "msgBoard.html", {"data":datalist})