from turtle import Turtle
import threading

def init(p):
    p.color("green")
    p.pensize(5)
    p.hideturtle()   #隐藏画笔
    p.speed("fastest")
    p.left(90)      # 调整画笔初始方向
    p.penup()       #不显示turtle移动时的痕迹
    p.goto(0,-200)      #调整画笔位置屏幕中心为0，0点
    p.pendown()     # 这三条语句是一个组合相当于先把笔收起来再移动到指定位置，再把笔放下开始画，否则turtle一移动就会自动的把线画出来

def branch(p,l,a,lst):
     p.forward(l)        #沿着当前的方向画l长
     q = p.clone()       #在分叉点处复制turtle
     p.left(a)           #原turtle向左
     q.right(a)          #副本turtle向右
     lst.append(p)       #将元素增加到列表的最后，以turtle本身遍历列表实现下一深度分支
     lst.append(q)

def tree(plist, l, a, f):       #   l为树杈长度，a为树杈与中线的角度，f为下一层深度的缩短的比例
    if l > 5:       #本函数的基例，树杈长度低于5个像素迭代停止
        lst = []    #turtle列表
        for p in plist:
           a = threading.Thread(target = branch, args = (p,l,a,lst))
           a.start()
           a.join()
           
        tree(lst, l*f, a, f)          
 
def main():
    p = Turtle()
    init(p)
    t = tree([p], 200, 65, 0.61803)

main()