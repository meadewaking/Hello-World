﻿def test():
    for i in range(5):
        yield i**2      #yield为生成器关键字，生成器会在被调用时冻结当前进程，并返回当前值。生成器的遍历可以由返回全部值的集合实现
                        #但生成器可以实时返回所需要的值在返回数据巨大时可以提高效率
                        #使用生成器时可以当返回值为列表使用

for i in test():
    print(i)