import os
import re

def Test1(rootDir): 
    list_dirs = os.walk(rootDir)
    l = 0   #总行数
    bns = 0 #空白行数
    ex = 0  #注释行数
    for root, dirs, files in list_dirs:    
        for f in files: 
            if ('.py' in f) and ('.pyc' not in f) and ('.pyp' not in f):
                #print(os.path.join(f)) 
                fls = open(root + '\\' + f,'r',encoding='utf-8').readlines()        #以列表形式读取文件
                for line in fls:
                    if ('\'\'\'' in line) or ('#' in line):
                        ex = ex + 1
                fbn = open(root + '\\' + f,'r',encoding='utf-8').read()         #无格式读取文件
                bn = re.findall(r'\n[\s| ]*\n',str(fbn))        #统计空白行
                bns = len(bn) + bns
                l = len(fls) + l
    print("代码行总数：",l)
    print("空白行数：",bns)
    print("注释行数：",ex)
    print("实际代码行数",l-bns)

print("python代码统计\n\n")
Test1('F:\\s4\\Python')