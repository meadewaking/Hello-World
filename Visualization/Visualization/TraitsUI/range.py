from traits.api import HasTraits, Int, Range, Property, property_depends_on
from traitsui.api import View, Item, RangeEditor
 
class RangeDemo(HasTraits):
    a = Range(1, 10)        #traitsUI中的滑动条用range表示
    b = Range(1, 10)
    c = Property(Int)
    view = View(
        Item('a'),
        Item('b'),
        '_',        #分隔符
        Item('c',editor=RangeEditor(low = 1, high = 20, mode = 'slider')),      #创建1到20的滑动条
        Item('c'),
        width = 0.3
    )
 
    @property_depends_on('a,b', settable = True)        #监测ab的值,并设定值可以被编辑
    def _get_c(self):
        print("computing")
        return (self.a + self.b)
 
ran = RangeDemo()
#ran.edit_traits()      #edit_traits为非模态窗口执行一次即退出
ran.configure_traits()