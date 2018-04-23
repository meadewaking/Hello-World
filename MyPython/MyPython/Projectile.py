from math import sin, cos, radians
 
class Projectile:           #声明类
    def __init__(self, angle, velocity, height):
        #声明属性，包含角度初速，和高度
        self.xpos = 0.0         #x坐标
        self.ypos = height      #y坐标
        theta = radians(angle)      #弧度化
        self.xvel = velocity * cos(theta)       #初始x方向速度
        self.yvel = velocity * sin(theta)       #初始y方向速度
 
    def update(self, time):
        #位置更新
        self.xpos = self.xpos + time * self.xvel        #x位置更新x = x0 + vt 
        yvell = self.yvel - 9.8 * time              #y速度更新v = v0 - at
        self.ypos = self.ypos + time * (self.yvel + yvell) / 2.0        #更新y位置 x = x + t(v0+v)/2 
        self.yvel = yvell           #更新y速度
 
    def getY(self):                 #get方法
        return self.ypos
 
    def getX(self):
        return self.xpos