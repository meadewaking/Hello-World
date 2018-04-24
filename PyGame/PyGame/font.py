import pygame,sys
import pygame.freetype

pygame.init()
screen = pygame.display.set_mode((600,400))
pygame.display.set_caption("文字绘制")
GOLD = 255,251,0
f1 = pygame.freetype.Font("C:\Windows\Fonts\msyh.ttc",36)    #定义字体（字体路径，大小）
f1rect = f1.render_to(screen, (200,160), "世界和平", fgcolor=GOLD, size=50)     #绘制文字（屏幕，位置，内容，颜色，大小（将覆盖初始值））

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()

    pygame.display.update()