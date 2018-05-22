#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define BOOK_COUNT 30
#define NUMBER_COUNT 3
#pragma warning(disable:4996)
typedef struct str_Book 
{
	char * number;
	char * name;
	char * kind;
	char * auther;
	char * press;
} Book;

int bookCount = 0;

Book * inputBook(int);
void inputBooks(Book * []);
void outputBook(Book *);
void outputBooks(Book * []);

void insertBooks(Book * []);
void borrowBooks(Book * []);

int fintBookByNumber(Book * [], char []);
int fintBookByName(Book * [], char []);
void modifyBooks(Book * []);
void findBooks(Book * []);
void findBook1s(Book * []);
void findBook2s(Book * []);

void sortBooks(Book * []);
void saveBooks(Book * []);
void loadBooks(Book * []);

float sumFloat(float [], int);
char inputChar(char, char);
int inputInteger(int, int);
float inputFloat(float, float);
char * inputString();

float sumFloat(float array[], int length)
{
	int i;     
    float sum; 

	for(sum = 0, i = 0; i < length; i++)
		sum += array[i];
	return sum;
}

char inputChar(char min, char max)
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

int inputInteger(int min, int max)
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

float inputFloat(float min, float max)
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


void main()
{
	Book * book[BOOK_COUNT];
	char choice;

	printf("                                   程序设计及应用课程项目：图书管理系统\n");
	printf("                	—————————————版权归ICID所有————————————\n");

	do {
		printf("1.录入图书信息 2.查看图书信息 3.添加（归还）图书信息 4.删除图书信息 5.修改图书信息 6.查找图书信息 7.图书信息排序 8.图书信息保存 9.加载 0.退出\n");
		printf("请选择：");
		choice = inputChar('0', '9');
		switch (choice)
		{
		case '1':
			inputBooks(book);
			break;
		case '2': 
			outputBooks(book);
			break;
		case '3': 
			insertBooks(book);
			break;
		case '4':
		    borrowBooks(book);
			break;
		case '5':
			modifyBooks(book);
			break;
		case '6':
			findBooks(book);
			break;
		case '7':
			sortBooks(book);
			break;
		case '8':
			//saveBooks(book);
			break;
		case '9':
			//loadBooks(book);
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
//录入图书信息
Book * inputBook(int j)  
{

    //int i;
	Book * pBook = (Book *)malloc(sizeof(Book));
	printf("请输入%d本图书的编号：", j + 1);
    pBook -> number = inputString();//通过结构体指针访问结构体成员	
	printf("请输入%d本图书的名称：", j + 1);
	pBook ->name = inputString();
	printf("请输入%d本图书的类别：", j + 1);
	pBook ->kind = inputString();
	printf("请输入%d本图书的作者：", j + 1);
	pBook ->auther = inputString();
	printf("请输入%d本图书的出版社：", j + 1);
	pBook ->press = inputString();
	return pBook;
}//申请内存空间，完成输入并返回该该内存的指针


void inputBooks(Book * books[])  
{

	
		int j;
	char choise;

	for (j =bookCount ; bookCount < BOOK_COUNT; j++)                 //student加s,p
	{
		books[j] = inputBook(j);
		bookCount++;
		
		printf("是否继续？(Y|N):");
		scanf(" %c", &choise);
		if(choise != 'Y' && choise != 'y')
			break;
	}
}
//查看图书信息
void outputBook(Book * pBook) //只读操作
{
	printf("%s\t", pBook->number);
	printf("%s\t", pBook->name);
	printf("%s\t", pBook->kind);
	printf("%s\t", pBook->auther);
	printf("%s\t", pBook->press);
    printf("\n");
}

void outputBooks(Book * books[]) 
{
	float sumCol[NUMBER_COUNT] = {0};
	int j;

	printf("图书编号\t名称\t类别\t作者\t出版社\n");

	for (j = 0; j < bookCount; j++)
	{
		outputBook(books[j]);
	}
		printf("\n");
}
//添加图书信息
void insertBooks(Book * book[]) 
{


	int index;
	int i;

	printf("请输入要插入的位置：");
	index = inputInteger(1, bookCount + 1);

	for (i = bookCount; i > index - 1; i--)
		book[i] = book[i - 1];

	book[index - 1] = inputBook(index - 1);
	bookCount++;

	printf("插入成功！");
	printf("\n");
}
//删除图书信息
void borrowBooks(Book * book[])  
{	
	int i;
	int index;

	printf("请输入已经借出的图书编号：");
    index = inputInteger(1,bookCount);

	for(i = index - 1; i < bookCount - 1; i++)
		book[i] = book[i + 1];

	bookCount--;
	printf("该书已经借出!\n");
}
//修改图书信息
void modifyBooks(Book * book[])//定义一个修改函数，声明了一个Student型数组student
{
	int index;
	char * number;

	printf("请输入需要修改的编号：");  //输入提示：请输入学号
	number = inputString();  //输入整型年份变量number的值要用格式声明“%d”
	
	index = fintBookByNumber(book,number);
	
	if(index >=bookCount)
	{
		printf("无该图书信息，不能修改！\n");
		return;
	}

	outputBook(book[index]);
	book[index] = inputBook(index);
}

	int fintBookByNumber(Book * book[],char number[])
	{
		int index;
		for (index = 0;index<bookCount;index++)
			if(strcmp(book[index]->number,number)==0)
				return index;
			return index;
	}

	int fintBookByName(Book * book[],char name[])
	{
		int index;
		for (index = 0;index < bookCount;index++)
			if(strcmp(book[index]->name,name)==0)
				return index;
			return index;
	}

	void findBook1s(Book * book[])                         //学号查找
	{
		int index;
		char number[20];
		printf("请输入学号：");
			scanf(" %s",&number);//输入学号
			
			index = fintBookByNumber(book,number);

			if(index>=bookCount)
			{
				printf("无该学生信息！\n");
				return;
			}
			outputBook(book[index]);//调用
	}
	void findBook2s(Book * book[]) 
	{
		int index;
		char name[20];
		printf("请输入姓名：");
			scanf(" %s",&name);

			index = fintBookByName(book,name);

			if(index>=bookCount)
			{
				printf("无该学生信息！\n");
				return;
			}
			outputBook(book[index]);
	}
    void findBooks(Book * book[])
	{
		char choice = '0';
			printf("1.按学号查找! 2.按姓名查找！ \n");
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
//图书信息排序
void sortBooks(Book * book[])
{
	int max;
	int i,j;

	char choice = '0';
	printf("1.编号排序！ 2.作者姓名排序！ \n");
	printf("请选择（1-2）：");
	scanf(" %c",&choice);
	switch(choice)  
	{
	case'1':
		 for (i=0;i<bookCount-1;i++)
		{
		   max=i;           
		   for(j=i+1; j<bookCount; j++)
				if(strcmp(book[max]->number,book[j]->number)>0)
					max = j;
			if(max!=i)
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];
				book[i] = temp;      
			}
		}
    	printf("排序完成！\n");	
			break;
	case'2':
		for (i=0;i<bookCount-1;i++)
		{
		   max=i;           
		   for(j=i+1; j<bookCount; j++)
				if(strcmp(book[max]->name,book[j]->name)>0)
					max = j;
			if(max!=i)
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];
				book[i] = temp;      
			}
		}
    	printf("排序完成！\n");	
			break;
	default:
		printf("暂无该功能！\n");
			break;
	}
    
}
//保存图书信息
/*void saveBooks(Book * Book[])  
{

}	
//加载图书信息
void loadBooks(Book * Book[])
{

}

	printf("谢谢使用!\n");
}*/

//申请内存空间，完成输入，并返回该内存空间的指针
//录入图书信息

//(Student * pstudent,int j)*/







