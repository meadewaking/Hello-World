import turtle
import time
turtle.speed("fast")
turtle.pensize(2)
turtle.bgcolor("black")
colors = ["red", "yellow", "purple", "blue"]
turtle.tracer(False)        #隐藏绘画过程
for x in range(400):
     turtle.forward(2*x)
     turtle.color(colors[x % 4])
     turtle.left(91)
turtle.tracer(True)
time.sleep(3)