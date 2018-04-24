import pygame ,sys

pygame.init()

size = width, height = 600, 400     #原始预设窗口大小
speed = [1, 1]
black = 0, 0, 0
vinfo = pygame.display.Info()   #info方法可以在setmode之前调用获取系统窗体大小（即一般为屏幕尺寸），之后获取显示屏幕尺寸
#size = width, height = vinfo.current_w, vinfo.current_h     #获取屏幕尺寸
icon = pygame.image.load("flower.png")      #加载图标

pygame.display.set_icon(icon)       #为界面设置图标
#screen = pygame.display.set_mode(size,pygame.NOFRAME)       #无边框显示
screen = pygame.display.set_mode(size,pygame.RESIZABLE)     #窗口大小可调
#screen = pygame.display.set_mode(size,pygame.FULLSCREEN)    #全屏显示
pygame.display.set_caption("Wall Ball")     #设置标题
ball = pygame.image.load("wb.gif")      #加载图片
ballrect = ball.get_rect()      #物体的surface对象（物体的外接矩形）
fps = 300           #帧率
fclock = pygame.time.Clock()    #计时器
still = False
bgcolor = pygame.Color("black")     #初始背景颜色为黑色

def RGBchannel(a):
    return 0 if a < 0 else (255 if a > 255 else int(a))

while True:
    for event in pygame.event.get():        #输入事件监听
        if event.type == pygame.QUIT:
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                speed[0] = speed[0] if speed[0] == 0 else (abs(speed[0]) - 1)*int(speed[0]/abs(speed[0]))   #移动速度减一，永不为负
            elif event.key == pygame.K_RIGHT:
                speed[0] = speed[0] + 1 if speed[0] > 0 else speed[0] - 1       #增加一像素移动速度
            elif event.key == pygame.K_UP:
                speed[1] = speed[1] + 1 if speed[1] > 0 else speed[1] - 1
            elif event.key == pygame.K_DOWN:
                speed[1] = speed[1] if speed[1] == 0 else (abs(speed[1]) - 1)*int(speed[1]/abs(speed[1]))
            elif event.key == pygame.K_ESCAPE:
                sys.exit()
        elif event.type == pygame.VIDEORESIZE:      #检测屏幕尺寸是否发生变化
            size = width, height = event.size[0], event.size[1]     #获取当前屏幕尺寸
            screen = pygame.display.set_mode(size, pygame.RESIZABLE)        #重设屏幕尺寸
        elif event.type == pygame.MOUSEBUTTONDOWN:      #点击鼠标事件，返回button（左键返回1，右键返回3。其他键因设备不同）
            if event.button == 1:       #检测被按下的是不是左键
                still = True
        elif event.type == pygame.MOUSEBUTTONUP:        #释放鼠标事件，同样返回button和pos
            still == False
            if event.button == 1:
                ballrect = ballrect.move(event.pos[0] - ballrect.left, event.pos[1] - ballrect.top)
        elif event.type == pygame.MOUSEMOTION:      #绘制ball跟随鼠标移动
            if event.buttons[0] == 1:
                ballrect = ballrect.move(event.pos[0] - ballrect.left, event.pos[1] - ballrect.top)

    #触发事件处理
    bgcolor.r = RGBchannel(ballrect.left*255/width)     #r通道根据surface左侧坐标与屏宽的比值确定
    bgcolor.g = RGBchannel(ballrect.top*255/height)     #g通道根据surface顶部坐标与屏高的比值确定
    bgcolor.b = RGBchannel(min(speed[0],speed[1])*255/max(speed[0],speed[1]))       #b通道根据surface速度的比值确定
    if pygame.display.get_active() and not still:       #如果窗口正在绘制，并且鼠标左键被释放，回复之前的绘制 #active监测窗口是否在系统中被绘制
        ballrect = ballrect.move(speed)                 #当窗口激活时继续活动，即最小化使时暂停绘制
    ballrect = ballrect.move(speed[0], speed[1])       #移动surface对象
    if ballrect.left < 0 or ballrect.right > width:      #判断是否发生碰撞，pygame使用笛卡尔坐标系，以左上角为原点
        speed[0] = -speed[0]
        if ballrect.right > width and ballrect.right + speed[0] > ballrect.right:       #解决ball超出边界后速度不断取反导致卡在边界的问题
            speed[0] = - speed[0]               #当还处在边框之外，控制速度朝向为逃离边框
    if ballrect.top < 0 or ballrect.bottom > height:
        speed[1] = -speed[1]
        if ballrect.bottom > height and ballrect.bottom + speed[1] > ballrect.bottom:
            speed[1] = - speed[1]

    #绘制规则
    screen.fill(bgcolor)          #重新调色（surface移动过的痕迹将留白）
    screen.blit(ball,ballrect)      #blit函数将一物体绘制到另一物体位置
    pygame.display.update()     #update只绘制改变部分，flip绘制整个窗口
    fclock.tick(fps)        #tick函数延迟指定fps
