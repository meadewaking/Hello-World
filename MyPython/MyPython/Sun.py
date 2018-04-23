from turtle import *
color("red","yellow")
speed("fastest")
begin_fill()        #填充颜色，无fillcolor函数则将颜色混合
while True:
    forward(200)
    left(140)
    if abs(pos()) < 1:
        break
end_fill()
done()