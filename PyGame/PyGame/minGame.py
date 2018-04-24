#游戏最小框架
import pygame, sys

pygame.init()      #初始化
screen = pygame.display.set_mode((600, 400))    #设置屏幕大小
pygame.display.set_caption("first game")    #添加窗口标题

while True:
    for event in pygame.event.get():     #游戏主事件一直循环
        if event.type == pygame.QUIT:    #判断是否退出
            sys.exit()

    pygame.display.update()         #刷新屏幕显示