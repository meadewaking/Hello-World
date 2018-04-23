f = open("D:\\select.txt","w")
num = "253121341"

for g in range(1,11):
    string = "SELECT * FROM dbo.Group" + str(g * 100 + 1) + " where QQNum=" + num +" \n"
    for i in range(g * 100 + 2,g * 100 + 101):
        string += "\t"+"UNION   all"+"\n"+"SELECT * FROM dbo.Group"+str(i)+" where QQNum=" + num +" \n"
    f.write("\n\n====GROUP " + str(g+1) +"======\n\n")
    f.write(string)
f.close()
