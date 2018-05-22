#include <stdio.h>         //编译预处理命令  文件包含命令
#include <string.h>        //编译预处理命令  使用字符数组
#include <stdlib.h>        //编译预处理命令  包含C语言标准库函数
#pragma warning(disable:4996)
#define BOOK_COUNT 30
#define NUMBER_COUNT 3
//结构体
typedef struct str_Book 
{
	char * number;
	char * name;
	char * kind;
	char * auther;
	char * press;
} Book;
//自定义函数
int bookCount = 0;

Book * inputBook(int);                        //定义指针
void inputBooks(Book * []);                   //定义输入函数
void outputBook(Book *);                      //定义输出函数
void outputBooks(Book * []);                  //定义输出函数

void insertBooks(Book * []);                  //定义插入函数
void borrowBooks(Book * []);                  //定义借出函数

int fintBookByNumber(Book * [], char []);     //定义按照编号查找函数
int fintBookByName(Book * [], char []);       //定义按照编号查找函数
void modifyBooks(Book * []);                  //定义查找函数
void findBooks(Book * []);      
void findBook1s(Book * []);
void findBook2s(Book * []);

void sortBooks(Book * []);                    //定义排序函数
void saveBooks(Book * []);                    //定义保存函数
void loadBooks(Book * []);                    //定义加载函数

char * inputNumber(Book *[]);  
float sumFloat(float [], int);                //定义浮点数
char inputChar(char, char);                   //定义字符
int inputInteger(int, int);                   //定义变量
float inputFloat(float, float);               //定义浮点数
char * inputString();                         //定义字符

float sumFloat(float array[], int length)     //函数调用
{
	int i;     
    float sum; 

	for(sum = 0, i = 0; i < length; i++)
		sum += array[i];
	return sum;
}

char inputChar(char min, char max)            //函数调用
{
	char ch;
	char * temp;

	do {
		temp = inputString();
		if (strlen(temp) > 1){
			printf("请输入一个字符：");
			continue;
		}

		sscanf(temp, " %c", &ch);
		free(temp);
		if (ch < min || ch > max)
			printf("请输入%c-%c之间的字符: ",min, max);
	} while (ch < min || ch > max);
	return ch;
}



int inputInteger(int min, int max)           //函数调用
{
	int i;
	char * temp;
	do{
		temp = inputString();
		sscanf(temp, " %d", &i);
		free(temp);
		if (i < min || i> max)
			printf("请输入%d-%d之间的值: ",min, max);
	} while (i < min || i> max);
	return i;
}

float inputFloat(float min, float max)     //函数调用
{
	float f;
	char * temp;
	do {
		temp = inputString();
		sscanf(temp, " %f", &f);
		free(temp);
		if(f < min || f > max)
			printf("请输入%.2f-%.2f之间的值: ",min, max);
	} while (f < min || f > max);
	return f;
}

char * inputString()
{
	char buffer[256];
	char * temp;

	//gets(buffer);
	scanf(" %s",buffer);
	temp = (char *)malloc(strlen(buffer) + 1);
	strcpy(temp, buffer);
	return temp;
}

char * loadString(FILE*fp)
	{
		char buffer[256];
		char * temp;
		
		fscanf(fp," %s",buffer);
		temp =(char *)malloc(strlen(buffer)+1);
		strcpy(temp,buffer);
		return temp;
	}

char * inputNumber(Book * books[])           
{
	char * ch;                        //定义字符型
	int i = 0;                        //声明变量i
	int j = 0;                        //声明变量j

	do {

		j = 0;

		ch = inputString();          //调用函数

		for (i = 0; i < bookCount; i++)
		{
			if (strcmp(books[i]->number, ch) == 0)//用Strcmp通过指针查找编号，如果重复
			{
				printf("编号重复！请重新输入：");//则输出编号重复！请重新输入
				j = 1;
			}
		}
	} while (j == 1);

	return ch;
	free(ch);                                //释放内存
}

void main()
{
	Book * book[BOOK_COUNT];
	char choice;
  
	printf("                                        程序设计及应用课程项目：图书管理系统\n");
	printf("                     	—————————————版权归ICID所有————————————\n");

	do {


		printf("\t\t\t\t\t ╭═════════■□■□═══╮\n");  
        printf("\t\t\t\t\t│         图书信息管理系统         │\n");  
        printf("\t\t\t\t\t╰═══■□■□══════════╯\n");  
        printf("   ┌───────────────—─────────────—────────────────────────—┐\n");  
        printf("   │     1. 录入图书信息      2. 输出图书信息     3. 增加归还图书      4. 删除借出图书     5. 修改图书信息        │\n");  
        printf("   │                                                                                                              │\n");  
        printf("   │     6. 查找图书信息      7. 图书信息排序     8. 图书信息保存      9. 图书信息加载     0. 退出 系统           │\n"); 
        printf("   └───────────────—─────────────—────────────────────────—┘\n");  
        printf("\t\t请您选择(0-9):");  
		choice = inputChar('0', '9');
		switch (choice)
		{
		case '1':
			inputBooks(book);   //输入
			break;
		case '2': 
			outputBooks(book);  //输出
			break;
		case '3': 
			insertBooks(book);  //插入
			break;
		case '4':
		    borrowBooks(book);  //借出
			break;
		case '5':
			modifyBooks(book);  //删除
			break;
		case '6':
			findBooks(book);    //查找
			break;
		case '7':
			sortBooks(book);    //排序
		case '8':
			saveBooks(book);    //保存
			break;
		case '9':
			loadBooks(book);    //加载
			break;
		case '0':
			break;
		default:
			printf("没有该功能\n");
			break;
		}
	} while (choice != '0');

	printf("谢谢使用!\n");
}

//申请内存空间，完成输入，并返回该内存空间的指针
//李念君（输入 输出）
//录入图书信息
Book * inputBook(int j)							//输入一个书的信息
{
	Book * pBook = (Book *)malloc(sizeof(Book));     //定义结构体Book指针
	printf("请输入%d本图书的编号：", j + 1);
	pBook -> number = inputNumber(pBook);                 //通过结构体指针访问结构体成员number
	printf("请输入%d本图书的名称：", j + 1);
	pBook ->name = inputString();                    //通过结构体指针访问结构体成员name	
	printf("请输入%d本图书的类别：", j + 1);
	pBook ->kind = inputString();                    //通过结构体指针访问结构体成员kind
	printf("请输入%d本图书的作者：", j + 1);
	pBook ->auther = inputString();                  //通过结构体指针访问结构体成员auther	
	printf("请输入%d本图书的出版社：", j + 1);
	pBook ->press = inputString();                   //通过结构体指针访问结构体成员press
	return pBook;	
}
//申请内存空间，完成输入并返回该该内存的指针


void inputBooks(Book * books[])                      //定义输入函数
{
	int j;                                           //定义整型变量j
	char choise;                                     //定义字符型变量j

	for (j =bookCount ; bookCount < BOOK_COUNT; j++) //当书的数量小于30时，执行循环
	{
	books[j] = inputBook(j);						 //调用inputBook函数，输入一本书的信息
	bookCount++;									 //全局变量加1，每循环一次书的数量增加一本

	printf("是否继续？(Y|N):");                      //提示用户是否继续
	choise=inputChar('Y','y');  							 //输入选择
	if(choise != 'Y' && choise != 'y')
	break;
	}
}
//查看图书信息
void outputBook(Book * pBook)                        //输出一本书函信息
{
printf("%s\t", pBook->number);                      //通过结构体指针访问结构体成员number
printf("%s\t", pBook->name);                        //通过结构体指针访问结构体成员number
printf("%s\t", pBook->kind);                        //通过结构体指针访问结构体成员number
printf("%s\t", pBook->auther);                      //通过结构体指针访问结构体成员number
printf("%s\t", pBook->press);                       //通过结构体指针访问结构体成员number
printf("\n");
}

void outputBooks(Book * books[])                   //定义输出函数
{
int j;                                             //定义整型变量j

printf("图书编号\t名称\t类别\t作者\t出版社\n");    //输出表头

for (j = 0; j < bookCount; j++)					   //当j小于bookCount时，循环输出
{
outputBook(books[j]);							   //输出书的信息
}
printf("\n");									   //换行
}
//高聪（插入）
//添加图书信息
void insertBooks(Book * book[])    
{
	int index;                              //定义一个整型数
	int i;                                  //定义i

	printf("请输入要插入的位置：");
	index = inputInteger(1, bookCount + 1); //函数调用，输入验证

	for (i = bookCount+1; i > index - 1; i--)
		book[i] = book[i - 1];              //把后一个值赋值给前一个，该位置前移。

	book[index - 1] = inputBook(index - 1); //将输入的信息赋值给想要插入的位置
	bookCount++;                            //全局变量+1，每循环一次多一本。
 
	printf("插入成功！");                   //输出插入成功。
	printf("\n");
}
//董妍（删除）
//删除图书信息
void borrowBooks(Book * book[])               //定义一个修改函数，声明了一个Student型数组student 
{
	int index;                                //定义整形变量index
	int i;                                    //定义整形变量i

	printf("请输入已经借出的图书编号：");     //请输入已经借出的图书编号：
	index = inputInteger(1,bookCount);        //调用inputInteger函数

	for(i = index - 1; i < bookCount - 1; i++)//正常数组从0开始计数
	book[i] = book[i + 1];                    //删除之后后面的信息向前移

	bookCount--;                              //全局变量减1，每循环一次bookcount-1(书被借出，数量减少)
	printf("该书已经借出!\n");                //该书已经借出
}
//王建（修改）
//修改图书信息
void modifyBooks(Book * book[])               //定义一个修改函数，声明了一个Student型数组student
{
	int index;
	char * number;

	printf("请输入需要修改的编号：");         //输入提示：请输入学号
	number = inputString();                   //输入整型年份变量number的值要用格式声明“%d”
	
	index = fintBookByNumber(book,number);    //把编号信息转换成位置信息
	
	if(index >=bookCount)                     //
	{
		printf("无该图书信息，不能修改！\n");
		return;
	}

	outputBook(book[index]);                   //调用输出函数
	book[index] = inputBook(index);            //调用输入函数，把原来位置的信息重新输入
}
//王子铭（查找）
//查找图书信息
	int fintBookByNumber(Book * book[],char number[])
	{
		int index;
		for (index = 0;index<bookCount;index++)
			if(strcmp(book[index]->number,number)==0)
				return index;
			return -1;
	}

	int fintBookByName(Book * book[],char name[])
	{
		int index;
		for (index = 0;index < bookCount;index++)
			if(strcmp(book[index]->name,name)==0)
				return index;
			return -1;
	}

	void findBook1s(Book * book[])                         //学号查找
	{
		int index;
		char number[20];
		printf("请输入图书编号：");
			scanf(" %s",&number);//输入编号
			
			index = fintBookByNumber(book,number);

			if(index==-1)
			{
				printf("无该图书信息！\n");
				return;
			}
			outputBook(book[index]);//调用
	}
	void findBook2s(Book * book[]) 
	{
		int index;
		char name[20];
		printf("请输入书名：");
			scanf(" %s",&name);

			index = fintBookByName(book,name);

			if(index==-1)
			{
				printf("无该图书信息！\n");
				return;
			}
			outputBook(book[index]);
	}
    void findBooks(Book * book[])
	{
		char choice = '0';
			printf("1.按编号查找！ 2.按书名查找！ \n");
			printf("请选择（1-2）：");
			scanf(" %c",&choice);
			switch(choice)  
			{
			case'1':
				findBook1s(book);
				break;
			case'2':
				findBook2s(book);
				break;
			default:
				printf("暂无该功能！\n");
			break;
			}
	}
//邵奎鑫（排序）
//图书信息排序
void sortBooks(Book * book[])
{
	int max;
	int i,j;

	char choice = '0';
	printf("1.编号排序！ 2.书名排序！ \n");//子菜单
	printf("请选择（1-2）：");
	scanf(" %c",&choice);
	switch(choice)  
	{
	case'1':
		 for (i=0;i<bookCount-1;i++)                           //循环
		{
		   max=i;                                              //相等
		   for(j=i+1; j<bookCount; j++)                        //循环
				if(strcmp(book[max]->number,book[j]->number)>0)//if语句判断，比较大小
					max = j;
			if(max!=i)
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];                       //交换
				book[i] = temp;      
			}
		}
    	printf("排序完成！\n");	                           //输出 
			break;
	case'2':
		for (i=0;i<bookCount-1;i++)                        //循环
		{
		   max=i;           
		   for(j=i+1; j<bookCount; j++)                    //循环
				if(strcmp(book[max]->name,book[j]->name)>0)//字符串比较大小
					max = j;
			if(max!=i)                                     //如果不等有
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];
				book[i] = temp;      
			}
		}
    	printf("排序完成！\n");	                           
		//提示
			break;
	default:
		printf("暂无该功能！\n");
			break;
	}
    
}
//姚禹竹（保存 加载）
//保存图书信息
void saveBooks(Book * book[]) 
{
	FILE * fp = fopen("c:\\temp\\student.txt", "w"); //打开文件夹
	int j;                                           //定义变量i,j
	printf("正在保存，请您耐心等候！\n");            //提示正在保存
	printf("保存成功！\n");                          //提示保存成功

	if (fp == NULL)                                  //如果为空
	{
	printf("无法保存！\n\a");                        //提示无法保存
	return;
	}

	for (j = 0; j < bookCount; j++)                  //循环
	{
		fprintf(fp,"%s\t", book[j]->number);         //取出book所指向的结构体中包含的数据项number
		fprintf(fp,"%s\t", book[j]->name);           //取出book所指向的结构体中包含的数据项name
		fprintf(fp,"%s\t", book[j]->kind);           //取出book所指向的结构体中包含的数据项kind
		fprintf(fp,"%s\t", book[j]->auther);         //取出book所指向的结构体中包含的数据项auther
		fprintf(fp,"%s\t", book[j]->press);          //取出book所指向的结构体中包含的数据项press
		fprintf(fp, "\n");                           //输出
	}
	fclose(fp);
}
//加载图书信息
void loadBooks(Book * book[])
{
	int j;                                           //声明变量i
	FILE * fp = fopen("c:\\temp\\student.txt","r");  //声明结构体指针

	if (fp == NULL)                                  //如果pf等于NULL
	{
	printf("文件读写错误!\n\a");                     //输出文件读些错误
	return;
	}
	bookCount = 0;
	for (j = 0; j < BOOK_COUNT && !feof(fp); j++)    //循环
	{
	book[bookCount] = (Book *)malloc(sizeof(Book));  //指针内存   在动态内存中输入字符窜
	book[bookCount]->number = loadString(fp);        //在文件中找到结构体中放入编号
	book[bookCount]->name = loadString(fp);          //在文件中找到结构体中放入书名
	book[bookCount]->kind = loadString(fp);          //在文件中找到结构体中放入种类
	book[bookCount]->auther = loadString(fp);        //在文件中找到结构体中放入作者
	book[bookCount]->press = loadString(fp);         //在文件中找到结构体中放入出版社

	fscanf(fp, "\n");
	bookCount++;                                     //循环加载所有信息
	if(feof(fp))                                     //终止
		break;
	}
	printf("成功加载%d条图书信息。\n",bookCount);    //提示加载成功

}









