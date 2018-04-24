import matplotlib.pyplot as plt
import numpy as np
import math

theta = math.radians(45)
k = 0.05
m = 0.046
v0 = 10
g = 9.8
t = np.arange(0.0, 0.12, 0.01)
x = 0.0
y = 0.0
xl = []
yl = []

vx = v0 * math.cos(theta)
vy = v0 * math.sin(theta)

for i in t.tolist():
    vx = vx - ((k * (v0 ** 2)) * math.cos(theta) * i)/m
    vy = vy - g * i - ((k * (v0 ** 2)) * math.sin(theta) * i)/m
    theta = math.atan(vy/vx)
    v0 = vx/math.cos(theta)
    x = x + vx * i
    y = y + vy * i
    xl.append(x)
    yl.append(y)

print(xl,yl)
plt.plot(xl,yl)
plt.show()
