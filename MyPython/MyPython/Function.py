'''                 函数中的形参操作不影响实参
def swap(a,b):
    c = a
    a = b
    b = c

a = 10
b = 5
swap(a,b)
print("a = ",a,"b = ",b)
'''
'''                 元祖和列表的形参操作会影响到实参，和Java类似Python中也有内存回收机制
def swaplist(list):
    for i in range(len(list)):
        list[i] = list[i] * 2
    print(list)

list = [1,2,3]
swaplist(list)
print(list)
'''
'''                 类似于java Python有内存动态申请机制
list = []
for i in range(3):
    string = input("输入信息")
    list.append(string)
for i in range(len(list)):
    print(list[i],end = ' ')        #利用end = ''可以修改默认结尾（默认结尾为\n）
'''
list = [{"name":1,"age":1},{"name":2,"age":2}]
'''                     append添加时必须为局部变量，否则会引起变量覆盖，Python中特有的数据结构字典是无序存储的，存储键值对并在调用时通过键直接映射值
def add():
    element = {}
    element["name"] = input()
    element["age"] = input()
    new = element
    list.append(new)

add()
add()
add()
for i in range(len(list)):
    print(list[i]) 
'''