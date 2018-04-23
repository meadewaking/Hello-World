from graphics import *

def convert(input):
    celsius = eval(input.getText())    # 获取输入框中的摄氏度
    fahrenheit = 9.0/5.0 * celsius + 32     #转换为华氏度
    return fahrenheit 

def colorChange(win,input):
    cnum = eval(input.getText())    #与convert同时获取温度值
    weight = cnum / 100.0           #根据温度算出红色的权重
    newcolor =color_rgb(255*weight,66+150*(1-weight),255*(1-weight))        #根据权重计算颜色
    win.setBackground(newcolor)     #根据温度设置背景颜色

def main():
    win = GraphWin("温度转换", 400, 300)        #创建窗口，（标题，x，y）
    win.setCoords(0.0, 0.0, 3.0, 4.0)       #设置坐标系，定义左下点和右上点，为其他图形元素提供相对位置
    # 绘制输入接口
    Text(Point(1,3)," 请输入摄氏度：").draw(win)
    Text(Point(2,2.7)," (摄氏度范围0—100)").draw(win)
    Text(Point(1,1)," 华氏度：").draw(win)      #语句位置以point定义中心点
    input = Entry(Point(2,3), 5)    #初始输入框
    input.setText("0.0")        #输入框内默认值
    input.draw(win)     #绘制输入框
    output = Text(Point(2,1),"")       #设置结果text    
    output.draw(win)        #输出结果
    button = Text(Point(1.5,2.0),"转换")      #设置转换按钮
    button.draw(win)        #绘制按钮
    rect = Rectangle(Point(1,1.5), Point(2,2.5))        #设置按钮的矩形边框（左下点，右上点）
    rect.draw(win)      #绘制按钮边框
    win.getMouse()      #等待鼠标点击
    result = convert(input)    # 转换输入
    output.setText(result)    # 显示输出   
    colorChange(win,input)   # 改变颜色   
    button.setText("退出")    # 改变按钮字体   
    win.getMouse()  # 等待点击事件，退出程序
    win.close()     #关闭窗口
 
if __name__ == '__main__':
    main()