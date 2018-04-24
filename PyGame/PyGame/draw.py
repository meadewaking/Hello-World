import pygame, sys
from math import pi
 
pygame.init()
screen = pygame.display.set_mode((600,400))
pygame.display.set_caption("Pygame图形绘制")
GOLD = 255, 251, 0
RED = pygame.Color('red')
WHITE = 255, 255, 255
GREEN = pygame.Color('green')

#pygame以rect为单位进行绘制，该类保存了区域位置与长宽等信息（绘制体的外接矩形）
e1rect = pygame.draw.ellipse(screen, GREEN, (50,50,500,300), 3)     #绘制椭圆（屏幕，颜色，区域（x,y,长，宽），边宽（0为填充））
c1rect = pygame.draw.circle(screen, GOLD, (200,180), 30, 5)     #绘制圆（圆心，半径）
c2rect = pygame.draw.circle(screen, GOLD, (400,180), 30)        
r1rect = pygame.draw.rect(screen, RED, (170, 130, 60, 10), 3)   #绘制矩形
r2rect = pygame.draw.rect(screen, RED, (370, 130, 60, 10))
plist = [(295,170), (285,250), (260,280), (340,280), (315,250), (305,170)]      #点列表
l1rect = pygame.draw.lines(screen, GOLD, True, plist, 2)        #绘制连线（是否闭合）
a1rect = pygame.draw.arc(screen, RED, (200,220,200,100), 1.4*pi, 1.9*pi, 3)     #绘制椭圆曲线（起始弧度，截止弧度）
 
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
    pygame.display.update()