def processLine(line, wordCounts):      #词频的行统计
    #用空格替换标点符号
    line = replacePunctuations(line)
    #从每一行获取每个词
    words = line.split() 
    for word in words:
        if word in wordCounts:
            wordCounts[word] += 1       #对每行的词频进行统计,将单词作为键，频数作为值
        else:
            wordCounts[word] = 1
 
#空格替换标点的函数
def replacePunctuations(line):
    for ch in line:
        if ch in "~@#$%^&*()_-+=<>?/,.:;{}[]|\'\"“”":
            line = line.replace(ch, " ")
    return line

infile = open("d:\\Mis\\cet.txt", "r")
 
#建立用于计算词频的空字典
wordCounts = {}
for line in infile:
    processLine(line.lower(), wordCounts)       #调用行统计并将所有大写字母转为小写
     
#从字典中获取数据对
pairs = list(wordCounts.items())        #将字典转化为键值对列表

#列表中的数据对交换位置,数据对排序
#items.sort()                    #排序（sort方法只对子项的首值排序，所以交换位置）
infile.close()
print(pairs)
print("")
pairs.sort(key = lambda x:x[1],reverse = True)      #lambda表达式在Python中已未命名函数形式出现，sort方法中可以取排序值key和直接定义reverse方法，默认由小到大
print(pairs)

