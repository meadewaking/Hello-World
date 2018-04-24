﻿from tvtk.api import tvtk
from tvtkfunc import ivtk_scene,event_loop
 
s = tvtk.STLReader(file_name = "python.stl")    #使用STLReader读取stl模型文件
m = tvtk.PolyDataMapper(input_connection = s.output_port)
a = tvtk.Actor(mapper = m)
 
win = ivtk_scene(a)
win.scene.isometric_view()
event_loop()