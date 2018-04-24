from traits.api import HasTraits, Str, Password
from traitsui.api import Item, Group, View
 
class TextEditor(HasTraits):
    # 定义文本编辑器的变量
    string_trait = Str("sample string")     #可以在此设定预设定值
    password = Password
    # 定义布局
    text_str_group = Group(
        Item('string_trait', style = 'simple', label= 'Simple'),
        Item('_'),
        Item('string_trait', style = 'custom', label= 'Custom'),        #相当于textera
        Item('_'),
        Item('password', style = 'simple', label= 'password')
        )
    # 定义视图
    traits_view = View(
        text_str_group,
        title = 'TextEditor',
        buttons = ['OK']
        )
     
text = TextEditor()
text.configure_traits()