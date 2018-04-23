import turtle
import time
turtle.speed("fastest")
turtle.pensize(2)
for x in range(400):
    turtle.forward(2*x)
    turtle.left(90)
time.sleep(5)