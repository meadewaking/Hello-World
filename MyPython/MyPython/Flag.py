from turtle import *

t = Turtle()

def move(x,y,d):
    x = x*20-300
    y = y*(-20)+200
    t.penup()
    t.goto(x,y)
    t.pendown()
    t.seth(d)

def flag():
    t.color("red")
    t.begin_fill()
    t.forward(600)
    t.right(90)
    t.forward(400)
    t.right(90)
    t.forward(600)
    t.right(90)
    t.forward(400)
    t.end_fill()

def star(length):
    t.color("yellow")
    t.begin_fill()
    for i in range(5):
        t.forward(length)
        t.right(144)
    t.end_fill()
    
t.speed(4)
t.penup()
t.setx(-300)
t.sety(200)
t.pendown()
flag()
move(2,4,0)
star(120)
move(9.3,1.3,-30)
star(40)
move(11,4.3,30)
star(40)
move(11,6.8,0)
star(40)
move(9.3,8.4,-27)
star(40)
t.hideturtle()
mainloop()