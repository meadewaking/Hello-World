from turtle import *
from datetime import *
 
def Skip(step):         #绘制表盘时跳过间隔
    penup()
    forward(step)
    pendown()
 
def mkHand(name, length):
    #注册Turtle形状，建立表针Turtle
    reset()
    Skip(-length*0.1)       #另取旋转中心点模拟真实时钟指针交叉
    begin_poly()                #绘制指针
    forward(length*1.1)
    end_poly()
    handForm = get_poly()       #将绘制的指针形状赋给handform
    register_shape(name, handForm)      #将形状变量注册为name
 
def Init():         #初始化指针与显示日期
    global secHand, minHand, hurHand, printer       #声明全局变量
    mode("logo")        # 重置Turtle指向北
    #建立三个表针Turtle并初始化
    mkHand("secHand", 125)      #注册秒针
    mkHand("minHand",  110)     #注册分针
    mkHand("hurHand", 90)       #注册时针
    secHand = Turtle()      
    secHand.shape("secHand")    
    minHand = Turtle()
    minHand.shape("minHand")    #声明并获取表针形状
    hurHand = Turtle()
    hurHand.shape("hurHand")    
    for hand in secHand, minHand, hurHand:
        hand.shapesize(1, 1, 3)     #设置形状（宽度，长度，轮廓倍数）
        hand.speed(0)
    #建立输出文字Turtle
    printer = Turtle()
    printer.hideturtle()
    printer.penup()
     
def SetupClock(radius):     #radius为半径
    #建立表的外框
    reset()             #复位初始化时变动和设置过的turtle
    pensize(7)
    for i in range(60):
        Skip(radius)        #从原点出发
        if i % 5 == 0:      #每隔5s绘制一条短线
            forward(20)
            Skip(-radius-20)    #退回原点
        else:
            dot(5)          #绘制半径5px的点
            Skip(-radius)       #退回到原点
        right(6)        #转到1s对应的角度
         
def Week(t):    #星期格式转换，weekday（）返回int型变量
    week = ["星期一", "星期二", "星期三",
            "星期四", "星期五", "星期六", "星期日"]
    return week[t.weekday()]
 
def Date(t):
    y = t.year
    m = t.month
    d = t.day
    return "%s %d %d" % (y, m, d)
 
def Tick():
    #绘制表针的动态显示
    t = datetime.today()        #取today（）格式时间
    second = t.second + t.microsecond*0.000001  #取时间到下一精度以保证转动连续
    minute = t.minute + second/60.0
    hour = t.hour + minute/60.0
    secHand.setheading(6*second)        #根据时间输出指针角度
    minHand.setheading(6*minute)
    hurHand.setheading(30*hour)
     
    tracer(False)  
    printer.forward(65)
    printer.write(Week(t), align="center",font=("Courier", 14, "bold"))     #打印文本（文本，对齐，字体（字体，字号，加粗））
    printer.back(130)
    printer.write(Date(t), align="center",font=("Courier", 14, "bold"))
    printer.home()          #文本turtle回归原点
    tracer(True)
 
    ontimer(Tick, 100)      #100ms后继续调用tick，此处可以去掉mainloop用递归实现，
    #但递归层数限制程序必然会崩溃，第一次调用ontimer不会影响结束函数（所以需要配合mainloop使用），第二次开始线程会在此等待
 
def main():
    tracer(False)       #关闭绘制过程
    Init()
    SetupClock(160)     #绘制半径为160px的表盘
    tracer(True)
    Tick()
    mainloop()      #mainloop不等效于循环，它保持主线程不结束变成挂起状态等待下一次出发操作

main()