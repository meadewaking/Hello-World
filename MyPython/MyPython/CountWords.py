import turtle
 
##全局变量##
#词频排列显示个数
count = 10
#单词频率数组-作为y轴数据
data = []
#单词数组-作为x轴数据
words = []
#y轴显示放大倍数-可以根据词频数量进行调节
yScale = 3
#x轴显示放大倍数-可以根据count数量进行调节
xScale = 40
 
################# Turtle Start  ####################  

#从点(x1,y1)到(x2,y2)绘制线段
def drawLine(t, x1, y1, x2, y2):
    t.penup()
    t.goto (x1, y1)
    t.pendown()
    t.goto (x2, y2)
 
# 在坐标(x,y)处写文字
def drawText(t, x, y, text):
    t.penup()
    t.goto (x, y)
    t.pendown()
    t.write(text)
 
def drawGraph(t):
    #绘制x/y轴线
    drawLine (t, 0, 0, 480, 0)
    drawLine (t, 0, 300, 0, 0)
 
    #x轴: 坐标及描述
    for x in range(count):
        x=x+1 #向右移一位,为了不画在原点上使得柱与y轴重合
        drawText(t, x*xScale-4, -20, (words[x-1]))      #在x轴以x*xScale为间距输出高频词
        drawText(t, x*xScale-4, data[x-1]*yScale+10, data[x-1])     #在柱的上方输出频次
    drawBar(t)
 
#绘制一个柱体
def drawRectangle(t, x, y):
    x = x*xScale
    y = y*yScale        #放大倍数显示
    drawLine(t, x-5, 0, x-5, y)
    drawLine(t, x-5, y, x+5, y)
    drawLine(t, x+5, y, x+5, 0)
    #drawLine(t, x+5, 0, x-5, 0)
     
#绘制多个柱体
def drawBar(t):
    for i in range(count):
        drawRectangle(t, i+1, data[i])    
################# Turtle End  ####################
 
         
#对文本的每一行计算词频的函数
def processLine(line, wordCounts):      #词频的行统计
    #用空格替换标点符号
    line = replacePunctuations(line)
    #从每一行获取每个词
    words = line.split()                #对中文分词可以安装jieba库引用其中的lcut方法实现
    for word in words:
        if word in wordCounts:
            wordCounts[word] += 1       #对每行的词频进行统计,将单词作为键，频数作为值
        else:
            wordCounts[word] = 1
 
#空格替换标点的函数
def replacePunctuations(line):
    for ch in line:
        if ch in "~@#$%^&*()_-+=<>?/,.:;{}[]|\'\"“”":
            line = line.replace(ch, " ")
    return line
 
def main():
    #用户输入一个文件名
    filename = input("请输入文件名及路径：").strip()
    infile = open(filename, "r")
     
    #建立用于计算词频的空字典
    wordCounts = {}
    for line in infile:
        processLine(line.lower(), wordCounts)       #调用行统计并将所有大写字母转为小写
         
    #从字典中获取数据对
    pairs = list(wordCounts.items())        #将字典转化为键值对列表
 
    #列表中的数据对交换位置,数据对排序
    items = [[x,y]for (y,x)in pairs]        #将所有键值对交换位置并变成子列表
    items.sort()                    #排序（sort方法只对子项的首值排序，所以交换位置）
    items.reverse()             #倒置列表
 
    #输出count个数词频结果
    for i in range(count):
        print(items[i][1]+"\t"+str(items[i][0]))        #输出最多的单词和它的频次
        data.append(items[i][0])        #将频次存到y轴
        words.append(items[i][1])       #将单词存到x轴
         
    infile.close()
     
    #根据词频结果绘制柱状图
    turtle.title('词频结果柱状图')
    turtle.setup(1200, 900)
    t = turtle.Turtle()
    t.hideturtle()
    t.width(3)
    drawGraph(t)
         
main()