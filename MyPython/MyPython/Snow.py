from turtle import *
from random import *   

def ground():       #绘制雪地
    hideturtle()
    speed(100)
    for i in range(400):        #绘制400条随机短线
        pensize(randint(5,10))      #随机生成线段的宽度
        x = randint(-400,350)       
        y = randint(-280,-1)        #随机生成短线位置
        r = -y/280
        g = -y/280
        b = -y/280              #根据坐标生成渐进色
        pencolor((r, g, b))     
        penup()
        goto(x,y)
        pendown()
        forward(randint(40,100))        #随机生成线段长短
 
def snow():     #绘制雪花
    hideturtle()
    pensize(2)
    speed(100)
    for i in range(100):
        r = random()
        g = random()
        b = random()
        pencolor(r, g, b)       #随机生成雪花的颜色
        penup()
        setx(randint(-350,350)) 
        sety(randint(1,270))        #随机变换坐标原点
        pendown()
        dens = randint(8,12)        #随机生成雪花的角数
        snowsize = randint(10,14)   #随机生成雪花大小
        for j in range(dens):   #画出雪花
            forward(snowsize)
            backward(snowsize)
            right(360/dens)
         
 
def main():
    setup(800, 600, 0, 0)
    tracer(False)
    bgcolor("black")    
    snow()
    ground()
    tracer(True)
    mainloop()
     
main()