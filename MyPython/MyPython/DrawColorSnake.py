import turtle
import time

def drawsnack(rad, angle, len, neckrad):    #（半径，角度，长度，脖子部分半径）
    colors = ["red", "yellow", "purple", "blue", "pink"]
    for i in range(len):
        '''
        if i != 0:
            if i == 1:
                turtle.pencolor("red")
            elif i == 2:
                turtle.pencolor("yellow")
            elif i == 3:
                turtle.pencolor("green")
            elif i == 4:
                turtle.pencolor("purple")
                '''
        turtle.color(colors[i % 5])
        turtle.circle(rad, angle)   #绘制圆心在左，半径为rad，角度为angle的圆弧
        turtle.circle(-rad, angle)  #绘制圆心在左，半径为rad，角度为angle的圆弧
    turtle.pencolor("pink")
    turtle.circle(rad, angle/2)     #绘制圆心在左，半径为rad，角度为angle/2的圆弧
    turtle.fd(rad)      #向前绘制rad长
    turtle.circle(neckrad, 180)     #绘制圆心在左，半径为neckrad，角度为180的圆弧
    turtle.fd(rad*2/3)      #向前绘制rad2/3长

def main():
    turtle.setup(1300, 800, 0, 0)   #绘图窗口初始化（长，宽，左上角位置（x，y））
    turtle.pensize(30)      #笔刷大小
    turtle.pencolor("blue")     #笔刷颜色
    turtle.seth(-40)        #笔刷起始运动方向（逆时针角度为正）
    drawsnack(40,80,5,15)

main()
time.sleep(3)