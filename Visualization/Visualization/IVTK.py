from tvtk.api import tvtk
 
def ivtk_scene(actors):     #创建ivtk窗口
    from tvtk.tools import ivtk     #引入ivtk工具
    win = ivtk.IVTKWithCrustAndBrowser()     #创建一个带Crust（Python Shell）的窗口 
    win.open()          #打开窗口
    win.scene.add_actor(actors)     #载入actor
    #修正窗口错误，
    dialog = win.control.centralWidget().widget(0).widget(0)
    from pyface.qt import QtCore
    dialog.setWindowFlags(QtCore.Qt.WindowFlags(0x00000000))
    dialog.show()
    return win
 
def event_loop():       #窗口循环
    from pyface.api import GUI
    gui = GUI()
    gui.start_event_loop()
 
s = tvtk.CubeSource(x_length=1.0, y_length=2.0, z_length=3.0)   #创建图形数据
m = tvtk.PolyDataMapper(input_connection=s.output_port)     #映射图形数据
a = tvtk.Actor(mapper=m)        #创建actor a承接映射器m
win = ivtk_scene(a)
win.scene.isometric_view()
event_loop()