from turtle import *
import time

def turnLeft():
    penup()
    fd(5)
    left(90)
    fd(5)
    pendown()

def turnRight():  
    penup()
    fd(5)
    right(90)
    fd(5)
    pendown()

def blank():
    penup()
    fd(5)
    fd(5)
    pendown()

def mkHand(name, shape):
    #注册Turtle形状
    reset()
    begin_poly()               
    eval(shape)
    end_poly()
    handForm = get_poly()       #将绘制的指针形状赋给handform
    register_shape(name, handForm)      #将形状变量注册为name

def nine():
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)
    turnRight()
    fd(50)
    turnRight()
    fd(50)
    turnRight()
    fd(50)
    
def eight():
    fd(50)
    turnLeft()
    fd(50)
    blank()
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)
    p = clone()
    p.penup()
    p.fd(5)
    p.fd(5)
    p.pendown()
    p.fd(50)
    turnLeft()
    fd(50)

def seven():
    left(90)
    penup()
    fd(5)
    pendown()
    fd(50)
    blank()
    fd(50)
    turnLeft()
    fd(50)

def six():
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)
    p = clone()
    p.penup()
    p.fd(5)
    p.left(90)
    p.fd(5)
    p.pendown()
    p.fd(50)
    turnRight()
    fd(50)
    turnRight()
    fd(50)

def five():
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)
    turnRight()
    fd(50)
    turnRight()
    fd(50)

def four():
    left(90)
    penup()
    fd(5)
    pendown()
    fd(50)
    p = clone()
    p.penup()
    p.fd(5)
    p.left(90)
    p.fd(5)
    p.pendown()
    p.fd(50)
    p.penup()
    p.fd(5)
    p.right(90)
    p.fd(5)
    p.pendown()
    p.fd(50)
    blank()
    fd(50)

def three():
    fd(50)
    turnLeft()
    fd(50)
    p = clone()
    p.penup()
    p.fd(5)
    p.left(90)
    p.fd(5)
    p.pendown()
    p.fd(50)
    blank()
    fd(50)
    turnLeft()
    fd(50)

def two():
    setheading(180)
    fd(50)
    turnRight()
    fd(50)
    turnRight()
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)

def one():
    left(90)
    penup()
    fd(5)
    pendown()
    fd(50)
    blank()
    fd(50)

def zero():
    fd(50)
    turnLeft()
    fd(50)
    blank()
    fd(50)
    turnLeft()
    fd(50)
    turnLeft()
    fd(50)
    blank()
    fd(50)

def test():
    pensize(5)
    hideturtle()

    penup()
    goto(-400,0)
    pendown()
    nine()
    penup()
    goto(-320,0)
    pendown()
    setheading(0)
    eight()
    penup()
    goto(-190,0)
    pendown()
    setheading(0)
    seven()
    penup()
    goto(-160,0)
    pendown()
    setheading(0)
    six()
    penup()
    goto(-80,0)
    pendown()
    setheading(0)
    five()
    penup()
    goto(50,0)
    pendown()
    setheading(0)
    four()
    penup()
    goto(80,0)
    pendown()
    setheading(0)
    three()
    penup()
    goto(210,0)
    pendown()
    two()
    penup()
    goto(290,0)
    pendown()
    setheading(0)
    one()
    penup()
    goto(320,0)
    pendown()
    setheading(0)
    zero()
    time.sleep(5)

def transform(c):
    if c == '1':
        one()
    elif c == '2':
        two()
    elif c == '3':
        three()
    elif c == '4':
        four()
    elif c == '5':
        five()
    elif c == '6':
        six()
    elif c == '7':
        seven()
    elif c == '8':
        eight()
    elif c == '9':
        nine()
    elif c == '0':
        zero()

def mkHands():
    global zero ,one ,two ,three ,four ,five ,six ,seven ,eight ,nine
    mkHand("zero","zero()")
    zero = Turtle()
    zero.shape("zero")
    mkHand("one","one()")
    mkHand("two","two()")
    mkHand("three","three()")
    mkHand("four","four()")
    mkHand("five","five()")
    mkHand("six","six()")
    mkHand("seven","seven()")
    mkHand("eight","eight()")
    mkHand("nine","nine()")

'''
year = time.strftime('%Y',time.localtime(time.time()))
month = time.strftime('%m',time.localtime(time.time()))
day = time.strftime('%d',time.localtime(time.time()))
hour = time.strftime('%H',time.localtime(time.time()))
minute = time.strftime('%M',time.localtime(time.time()))
second = time.strftime('%S',time.localtime(time.time()))

pensize(5)
hideturtle()
p = clone()
while True:
    second = time.strftime('%S',time.localtime(time.time()))
    transform(second[1])
    time.sleep(1)
    penup()
    goto(0,0)
    setheading(0)
    pendown()
'''
tracer(False)
mkHand("two","two()")
reset()
two = Turtle()
two.shape("two")
two.shapesize(1,1,1)
two.speed(0)
tracer(True)
mainloop()