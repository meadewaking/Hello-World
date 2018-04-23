from Projectile import *
 
def getInputs():
    print("计算斜抛体的飞行距离（忽略空气阻力）\n")
    a = eval(input("输入抛射角度（d）："))
    v = eval(input("输入初速度（m/s）："))
    h = eval(input("输入初始高度（m/s）："))
    t = eval(input("输入模拟精度（s）："))
    return a,v,h,t
 
def main():
    angle,vel,h0,time = getInputs()
    shot = Projectile(angle,vel,h0)
    while shot.getY() >=0:
        shot.update(time)           
    print("\n飞行距离为{0:0.1f}米".format(shot.getX()))
   
if __name__ == "__main__":          #在本文件执行main方法时__name__为true，同时Python允许其他文件执行main方法，此时__name__为false
    main()