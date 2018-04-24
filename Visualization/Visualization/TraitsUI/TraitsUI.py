from traits.api import HasTraits,Str,Int
from traitsui.api import View,Item,Group

class ModelManager(HasTraits):          #继承hastraits类
    model_name = Str
    category = Str
    model_file = Str
    model_number = Int      #变量为int型当输入值为非int型时输入栏会标红提示
    vertices = Int
    view = View(            #定义视图
        Item('model_name',label = u"模型名称"),         #定义具体项item（'变量',label="视图显示名称"（中文使用Unicode编码前要加“u”））
        Item('category',label = u"模型类型"),
        Item('model_file',label = u"模型文件"),
        Item('model_number',label = u"模型数量"),
        title = u"模型资料",width = 220,resizable = True        #视图标题，视图宽度，是否可以拉伸
        )

view1 = View(
    Group(      #group项默认显示为窗口的一个栏，默认横向排列。可以使用group嵌套将多个group显示在一页中
        Item('model_name',label = u"模型名称"),
        Item('category',label = u"模型类型"),
        Item('model_file',label = u"模型文件"),
        label = u"模型信息",show_border = True
        ),
    Group(
        Item('model_number',label = u"模型数量"),
        Item('vertices',label = u"顶点数量"),
        label = u"统计信息",show_border = True
        )
    )

model = ModelManager()
model.configure_traits(view = view1)        #调用configure_traits显示窗口,可以通过参数设置类外自定义视图