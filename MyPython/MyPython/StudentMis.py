#coding=utf-8  
def functionList():   #定义功能菜单  
    print("---------请输入序号选择您要得功能---------")  
    print("")  
    print("-"*14+"1.查看学生信息"+"-"*14)  
    print("-"*42)  
    print("-"*14+"2.增加学生信息"+"-"*14)  
    print("-"*42)  
    print("-"*14+"3.删除学生信息"+"-"*14)  
    print("-"*42)  
    print("-"*14+"4.修改学生信息"+"-"*14)  
    print("-"*42)  
    print("-"*14+"5.查找系统学生"+"-"*14)  
    print("-"*42)  
    print("-"*14+"6.返回到上一级"+"-"*14)  
    print("-"*42)  
    print("-"*14+"7.退出学生系统"+"-"*14)  
    print("")       
      
def functionList2(): #定义简单版功能菜单  
      
    print("---1:查看----2:增加-----3:删除----4:修改----")  
    print("-------5:查找-------6:返回------7:退出------")  
      
def nameListFunction():  #显示单个学生姓名信息  
    nameList =[]    
    for i in range(len(studentList)):  
        if studentList[i]["name"] not in nameList:  
            nameList.append(studentList[i]["name"])  
    return nameList  
  
def findNameLocation(studentname):  #通过名字找到学生位置  
    for j in range(len(studentList)):  
        if studentList[j]["name"]==studentname:  
            return j  
            
def listFunction():       #定义显示现有学生信息函数  
    for i in range(len(studentList)):  
        studentInfo = studentList[i]  
        print("姓名：%s--性别：%s--年龄：%s--学号：%s--备注：%s--"%(studentInfo["name"],studentInfo["sex"],studentInfo["age"],studentInfo["studentID"],studentInfo["extra"]))  
        print("")  
def addFunction():        #定义增加学生函数  
      
    while True:  
        numInput = input("-----修改已经存在的学生备注请输入1\n-----------增加一个新的学生请输入2：")  
        if numInput=="2":  
            while True:  
                nameNoExistAdd = input("请输入您要增加的名字：")         
                nameList=nameListFunction()  
                newStudent={}  
                newStudent["name"]=nameNoExistAdd  
                sexInput = input("----请输入%s的性别--f:man--m:women："%nameNoExistAdd)  
                newStudent["sex"]=sexInput  
                ageInput = input("-------请输入%s2位数字表示的年龄："%nameNoExistAdd)  
                newStudent["age"]=ageInput
                IDInput = input("----------请输入%s的8位学号："%nameNoExistAdd)  
                newStudent["studentID"]=IDInput   
                extraInput = input("----------请输入%s的备注："%nameNoExistAdd)  
                newStudent["extra"]=extraInput  
                studentList.append(newStudent)  
                print("--------------%s已经添加到学生管理系统"%nameNoExistAdd)  
                print("")  
                print("姓名：%s--性别：%s--年龄：%s--学号：%s--备注：%s--"%(newStudent["name"],newStudent["sex"],newStudent["age"],newStudent["studentID"],newStudent["extra"]))  
                break  
            break  
        elif numInput=="1":  
            while True:  
                nameExistAdd = input("------请输入您要修改备注的学生的名字：")  
                nameList=nameListFunction()  
                if nameExistAdd in nameList:  
                    extraExistAdd = input("-----------------请输入您要添加的备注：")  
                    j=findNameLocation(nameExistAdd)  
                    studentList[j]["extra"]=extraExistAdd  
                    print("---------------备注已经添加--------------")  
                    print("")  
                    print("姓名：%s--性别：%s--年龄：%s--学号：%s--备注：%s--"%(studentList[j]["name"],studentList[j]["sex"],studentList[j]["age"],studentList[j]["studentID"],studentList[j]["extra"]))  
                    print("")  
                    break  
                else:  
                    print("-----------------您输入的姓名不存在")  
            break  
  
        else:  
            print("----------------您输入的信息不正确")  
  
def delFunction():         #定义删除学生的函数     
    while True:       
        nameDel = input("---------------请输入您要删除的名字：")  
        studentNameList =nameListFunction()  
        if nameDel in studentNameList:  
            j=findNameLocation(nameDel)  
  
            del studentList[j]  
            print("-------------%s已经从学生管理系统中删除"%nameDel)  
            print("")  
            break  
        else:  
            print("------------------您要删除的名字不存在！")  
              
def modifiFunction():      #定义修改学生的函数  
    while True:  
        nameModifi = input("----------------请输入要修改的名字：")  
        studentNameList =nameListFunction()  
        if nameModifi in studentNameList:  
            print("------------请选择要修改的内容-----------")  
            print("--------------1：修改姓名---------------")  
            print("--------------2：修改性别---------------")  
            print("--------------3：修改年龄---------------")  
            print("--------------4：修改学号---------------")  
            print("--------------5：修改备注---------------")  
  
            while True:  
                choiceInput=input("请输入：")  
                if choiceInput == "1":  
                    newNameInput = input("----------请输入新的姓名：")  
                    j=findNameLocation(nameModifi)  
                    studentList[j]["name"]=newNameInput  
                    print("------------姓名已经更新------------")  
                    print("")  
                    break  
                elif choiceInput == "2":  
                    while True:  
                        newSexInput = input("----请输入新的性别--f:man--m:women---")  
                        if sexInputDebug(newSexInput)==True:  
                            j=findNameLocation(nameModifi)  
                            studentList[j]["sex"]=newSexInput  
                            print("-------------性别已经更新-------------")  
                            print("")  
                            break  
                        else:  
                            print("---------输入有误，请重新输入！---------")  
                    break  
                elif choiceInput == "3":  
                    while True:  
                        newAgeInput = input("----------请输入新的年龄：")  
                        if ageInputDebug(newAgeInput)==True:  
                            j=findNameLocation(nameModifi)  
                            studentList[j]["age"]=newAgeInput  
                            print("------------年龄已经更新------------")  
                            print("")  
                            break  
                        else :  
                            print("----------入有误，请重新输入！-------")  
                    break  
                elif choiceInput == "4":  
                    while True:  
                        newIDInput = input("----------请输入新的学号：")  
                        if IDInputDebug(newIDInput)==True:  
                            j=findNameLocation(nameModifi)  
                            studentList[j]["studentID"]=newIDInput  
                            print("------------学号已经更新------------")  
                            print("")  
                            break  
                        else:  
                            print("----------入有误，请重新输入！-------")  
                    break  
                elif choiceInput == "5":  
                    newExtraInput = input("----------请输入新的备注：")  
                    j=findNameLocation(nameModifi)  
                    studentList[j]["extra"]=newNameInput  
                    print("------------备注已经更新------------")  
                    print("")  
                    break  
                else :  
                    print("---------输入有误，请重新输入！-------")  
                    print("")  
            break  
        else :  
            print("-----------------您输入的名字不存在！")  
            print("")  
  
def searchFunction():      #定义搜索学生的函数  
    nameSearch = input("-------------请输入要查找的名字：")  
    print("")  
    nameList = nameListFunction()  
    if nameSearch in nameList:  
        print("-----------------%s在学生管理系统中-------------------"%nameSearch)  
        print("")  
        j=findNameLocation(nameSearch)  
        print("姓名：%s--性别：%s--年龄：%s--学号：%s--备注：%s--"%(studentList[j]["name"],studentList[j]["sex"],studentList[j]["age"],studentList[j]["studenID"],studentList[j]["extra"]))  
        print("")  
    else :  
        print("----------------%s不在学生管理系统中-----------------"%nameSearch)  
        print("")  
#默认学生信息系统内容  
studentList = [{"name":"Frank","sex":"f","age":33,"studentID":"312312","extra":""},{"name":"Jane","sex":"m","age":45,"studentID":"324235","extra":""}]  
  
#函数主体  
print("-"*11+"欢迎来到学生管理系统"+"-"*11)  
print("")  
print("")  
functionList()  
while True:                   #进入循环，根据序号选择操作  
    userInput =input("----------------请输入您要选择的功能序号：")  
    print("")  
      
    if userInput == "1":        #显示现有学生和返回  
        listFunction()  
        functionList2()          
        continue  
    elif userInput == "2":      #使用增加函数和返回  
        addFunction()  
        functionList2()  
        continue  
    elif userInput == "3":      #使用删除函数和返回  
        delFunction()  
        functionList2()  
        continue  
    elif userInput == "4":      #使用修改函数和返回  
        modifiFunction()  
        functionList2()  
        continue  
    elif userInput == "5":      #使用搜索函数和返回  
        #searchFunction()  
        findNameLocation("name")
        functionList2()  
        continue  
    elif userInput == "6":      #返回功能列表  
        functionList()  
        continue  
    elif userInput == "7":      #退出  
        break  
    else:  
        print("----------输入有误，请重新输入！----------")  
       