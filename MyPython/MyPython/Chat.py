from tkinter import *
import time
 
def main():
 
  def sendMsg():        #发送消息
    strMsg = '我:' + time.strftime("%Y-%m-%d %H:%M:%S",time.localtime()) + '\n '     #添加发送方和发送时间
    txtMsgList.insert(END, strMsg, 'greencolor')    #将消息信息插入到文本显示区末尾一行并调用颜色标签
    txtMsgList.insert(END, txtMsg.get('0.0', END))  #将发送信息插入到末尾一行
    txtMsg.delete('0.0', END)       #清空输入区
     
  def cancelMsg():#取消消息
    txtMsg.delete('0.0', END)     #清空输入区（0.0代表文本开始位置）
 
  def sendMsgEvent(event): #发送消息事件
    if event.keysym == "Up":        #判断键盘输入并调用发送消息函数
      sendMsg()
 
  #创建窗口 
  t = Tk()
  t.title('与自己聊天中')
       
  #创建frame容器
  frmLT = Frame(width=500, height=320, bg='white')      #创建显示文本容器
  frmLC = Frame(width=500, height=150, bg='white')      #创建输入文本容器
  frmLB = Frame(width=500, height=30)       #创建按钮容器
  frmRT = Frame(width=200, height=500)      #创建信息显示容器
   
  #创建控件
  txtMsgList = Text(frmLT)          #创建文本显示区（text（）为文本控件构造函数）
  txtMsgList.tag_config('greencolor', foreground='#008C00')     #创建tag（绿色文本）
  txtMsg = Text(frmLC);         #创建文本输入区
  txtMsg.bind("<KeyPress-Up>", sendMsgEvent)        #绑定方向键上为发送键
  btnSend = Button(frmLB, text='发 送', width = 8, command=sendMsg)       #创建发送按钮（容器对象，显示文本，宽度，调用的函数）
  btnCancel = Button(frmLB, text='取消', width = 8, command=cancelMsg)    #创建取消按钮
  #imgInfo = PhotoImage(file = "D:\\Python.gif")    #初始图片信息
  #lblImage = Label(frmRT, image = imgInfo)     #在指定容器中显示图片
  #lblImage.image = imgInfo
 
  #窗口布局
  frmLT.grid(row=0, column=0, columnspan=2, padx=1, pady=3)     #划分窗口columnspan = 2代表区域横跨两列
  frmLC.grid(row=1, column=0, columnspan=2, padx=1, pady=3)     
  frmLB.grid(row=2, column=0, columnspan=2)
  frmRT.grid(row=0, column=2, rowspan=3, padx=2, pady=3)
  #固定大小
  frmLT.grid_propagate(0)       #使设置的frame尺寸生效
  frmLC.grid_propagate(0)
  frmLB.grid_propagate(0)
  frmRT.grid_propagate(0)
   
  btnSend.grid(row=2, column=0)     #放置发送按钮
  btnCancel.grid(row=2, column=1)   #放置取消按钮
  #lblImage.grid()
  txtMsgList.grid()        #控件放入网格中
  txtMsg.grid()
 
  #主事件循环
  t.mainloop()
 
if __name__ == '__main__':
    main()