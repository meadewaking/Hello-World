ft1 = open("D:\\Mis\\TelBook.txt","rb")       #打开电话簿
ft2 = open("D:\\Mis\\EmailBook.txt","rb")         #打开邮箱文件

ft1.readline()                              #跳过头行
ft2.readline()

lines1 = ft1.readlines()                #读取文本为行列表
lines2 = ft2.readlines()

Tname = []                  #声明空列表存储读取的各部分信息
tale = []
Ename = []
email = []

for line in lines1:         #整理电话簿
    elements = line.split()         #以空格分割元素
    Tname.append(str(elements[0].decode('gbk')))        #前面使用二进制读入办法防止汉字乱码，此处转编码，并将分割元素转成字符串并添加到列表中
    tale.append(str(elements[1].decode('gbk')))

for line in lines2:         #整理邮箱文件
    elements = line.split()         
    Ename.append(str(elements[0].decode('gbk')))        
    email.append(str(elements[1].decode('gbk')))

lines = []          #用以保存的列表
lines.append("姓名\t电话\t\t邮箱\n")
for i in range(len(Tname)):             #按电话簿整理邮箱
    s = ""
    if Tname[i] in Ename:           #找到邮箱文件中的电话簿姓名
        j = Ename.index(Tname[i])   #找到返回找到的位置
        s = "\t".join([Tname[i], tale[i], email[j]])    #以“\t”隔开并合并吻合的数据
        s += "\n"   
    else:
        s = "\t".join([Tname[i], tale[i], str("   -----   ")])
        s += "\n"
    lines.append(s)

for i in range(len(Ename)):     #处理邮箱文件在电话簿中不存在的姓名
    s = ""
    if Ename[i] not in Tname:
        s = "\t".join([Ename[i], str("   -----   "), email[i]])
        s += "\n"
    lines.append(s)

ft3 = open("D:\\Mis\\combination.txt","w")
ft3.writelines(lines)

ft1.close()
ft2.close()
ft3.close()

print("combine complete！")