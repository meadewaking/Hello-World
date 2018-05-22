#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
#define BOOK_COUNT 30			//定义最大保存图书数目

typedef struct str_Book
{
	char num[20];
	char name[30];
	char kind[20];
	char auther[20];
	char press[20];
}Book;
//Book book;

int bookcount = 0;				//定义全局变量
									//传参格式
float sum(float array[], int length)
{
	int i;
	float sum;

	for (sum = 0, i = 0; i < length; i++)
	{
		sum += array[i];
	}
	return sum;
}
int juge(char num1[], int length, Book s[])
{
	int i;
	for (i = 0; i < length; i++)
	{
		if (!strcmp(num1, s[i].num))
		{
			return 1;
			break;
		}
	}
	if (i == length)
	{
		return 0;
	}
}
int num2index(char num[], Book book[], int index)
{
	int i;
	for (i = 0; i < bookcount; i++)
	{
		if (!strcmp(num, book[i].num))
		{
			index = i + 1;
			return index;
		}
	}
}
void input(Book book[])
{
	int i, j;
	char ch3 = '0';

	bookcount = 0;
	for (j = 0; bookcount <BOOK_COUNT; j++)
	{
		i = 0;
		do
		{
			if (i>0)
			{
				printf("该编号已存在！请重新输入！\n");
			}
			printf("请输入第%d个图书的编号：", j + 1);
			scanf(" %s", book[j].num);
			i++;
		} while (j != 0 && juge(book[j].num, bookcount, book));
		printf("请输入第%d个图书的名称：", j + 1);
		scanf(" %s", book[j].name);
		printf("请输入第%d个图书的种类：", j + 1);
		scanf(" %s", book[j].kind);
		printf("请输入第%d个图书的作者：", j + 1);
		scanf(" %s", book[j].auther);
		printf("请输入第%d个图书的出版社：", j + 1);
		scanf(" %s", book[j].press);
		bookcount++;
		printf("是否继续输入？ Y/N：");
		scanf(" %c", &ch3);
		if (ch3 == 'N' || ch3 == 'n')
		{
			break;
		}
	}
}
void output(Book book[])
{
	int j;
	printf("编号\t");
	printf("名称\t");
	printf("种类\t");
	printf("作者\t");
	printf("出版社\n");
	for (j = 0; j < bookcount; j++)
	{
		printf("%s\t", book[j].num);
		printf("%s\t", book[j].name);
		printf("%s\t", book[j].kind);
		printf("%s\t", book[j].auther);
		printf("%s\t", book[j].press);
		printf("\n");
	}
}
void rank(Book book[])
{
	int i, j, k, max = 0;
	char ch;
	printf("1.以名称排序\n");
	printf("请选择:");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		for (k = 0; k < bookcount; k++)
		{
			for (i = 0; i < bookcount - 1; i++)
			{
				max = i;
				for (j = i + 1; j < bookcount; j++)
				{
					if (strcmp(book[i].name, book[j].name)>0)
					{
						max = j;
					}
				}

				if (max != i)
				{
					Book temp;
					temp = book[max];
					book[max] = book[i];
					book[i] = temp;
				}
			}
		}


		break;
	default:
		printf("请重新排序！\n");
		break;
	}

	printf("排序完成！\n\n");

}
void insert(Book book[])
{
	int index, i = 0;
	char tempnum[20];
	do
	{
		printf("请输入1至%d之间的位置!\n", bookcount + 1);
		printf("请输入你想要插入的位置：");
		scanf(" %d", &index);
	} while (index<1 || index>bookcount + 1);

	for (i = bookcount; i > index - 2; i--)
	{
		book[i + 1] = book[i];
	}
	bookcount++;
	i = 0;
	do
	{
		if (i>0)
		{
			printf("该编号已存在！请重新输入！\n");
		}
		printf("请输入第%d个图书的编号：", index);
		scanf(" %s", tempnum);
		i++;
	} while (juge(tempnum, bookcount, book));
	strcpy(book[index - 1].num, tempnum);
	printf("请输入第%d个图书的名称：", index);
	scanf(" %s", book[index - 1].name);
	printf("请输入第%d个图书的种类：", index);
	scanf(" %s", book[index - 1].kind);
	printf("请输入第%d个图书的作者：", index);
	scanf(" %s", book[index - 1].auther);
	printf("请输入第%d个图书的出版社：", index);
	scanf(" %s", book[index - 1].press);
}
void delete(Book book[])
{
	int index, i;
	printf("请输入你想要删除的位置：");
	scanf(" %d", &index);
	for (i = index - 1; i < bookcount; i++)
	{
		book[i] = book[i + 1];
	}
	bookcount--;
	printf("删除完成！\n\n");
}
void alter(Book book[])
{
	int index = 0;
	char num[20];
	printf("请输入你想要修改的图书的编号：");
	scanf(" %s", num);
	index = num2index(num, book, index);
	printf("请输入要修改的图书的编号：");
	scanf(" %s", book[index - 1].num);
	printf("请输入要修改的图书的名称：");
	scanf(" %s", book[index - 1].name);
	printf("请输入要修改的图书的种类：");
	scanf(" %s", book[index - 1].kind);
	printf("请输入要修改的图书的作者：");
	scanf(" %s", book[index - 1].auther);
	printf("请输入要修改的图书的出版社：");
	scanf(" %s", book[index - 1].press);
	printf("修改完成！\n\n");
}
void search(Book book[])
{
	int index, i, k = 0;
	char ch;
	char temp_num[20];
	char temp_name[20];
	printf("1.按位置查询\t2.按编号查询\t3.按名称查找\n");
	printf("请输入你要查找的方式：");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		printf("请输入你要查找的图书的位置：");
		scanf(" %d", &index);
		printf("编号\t");
		printf("名称\t");
		printf("种类\t");
		printf("作者\t");
		printf("出版社\n");
		printf("%s\t", book[index - 1].num);
		printf("%s\t", book[index - 1].name);
		printf("%s\t", book[index - 1].kind);
		printf("%s\t", book[index - 1].auther);
		printf("%s\t", book[index - 1].press);
		printf("\n");
		break;
	case'2':
		printf("请输入你要查找的图书的编号：");
		scanf(" %s", temp_num);
		for (i = 0; i < bookcount; i++)
		{
			if (!strcmp(temp_num, book[i].num))
			{
				printf("编号\t");
				printf("名称\t");
				printf("种类\t");
				printf("作者\t");
				printf("出版社\n");
				printf("%s\t", book[i].num);
				printf("%s\t", book[i].name);
				printf("%s\t", book[i].kind);
				printf("%s\t", book[i].auther);
				printf("%s\t", book[i].press);
				break;
			}
		}
		if (i == bookcount)
		{
			printf("你要找的图书不存在！\n");
		}
		break;
	case'3':
		printf("请输入你要查找的图书的名称：");
		scanf(" %s", temp_name);
		for (i = 0; i < bookcount; i++)
		{
			if (!strcmp(temp_name, book[i].name))
			{
				if (k == 0)
				{
					printf("编号\t");
					printf("名称\t");
					printf("种类\t");
					printf("作者\t");
					printf("出版社\n");
				}

				printf("%s\t", book[i].num);
				printf("%s\t", book[i].name);
				printf("%s\t", book[i].kind);
				printf("%s\t", book[i].auther);
				printf("%s\t", book[i].press);
				k++;
			}
		}
		if (k == 0)
		{
			printf("你要找的图书不存在！\n");
		}
		break;
	default:
		printf("请重新输入！\n");
		break;
	}
}
void save(Book book[])
{
	FILE*fp = fopen("D:\\BookMis.txt", "wb");
	int i;
	if (fp == NULL)
	{
		printf("无法保存！\n");
		return;
	}
	fprintf(fp, "%02d", bookcount);
	for (i = 0; i < bookcount; i++)
	{
		fwrite(&book[i], sizeof(Book), 1, fp);
	}
	fclose(fp);
	printf("保存成功！\n");
}
void load(Book book[])
{
	FILE*fp = fopen("D:\\BookMis.txt", "rb");
	int j;

	fscanf(fp, "%02d", &bookcount);
	fseek(fp, 2, 0);
	for (j = 0; j < bookcount; j++)
	{
		fread(&book[j], sizeof(Book), 1, fp);
	}
	fclose(fp);
	printf("加载完成！\n\n");
}
void main()
{
	char ch1;
	Book book[BOOK_COUNT];

	printf("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊图书管理系统＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊\n\n");
	do
	{
		printf("\t\t\t1.输入2.输出3.插入4.删除5.修改6.查找7.排序8.保存9.加载0.退出\n");
		printf("请输入0~9：");
		scanf(" %c", &ch1);

		switch (ch1)
		{
		case'1':
			input(book);		//引用函数，并调用参数
			break;
		case'2':
			output(book);
			break;
		case'3':
			insert(book);
			break;
		case'4':
			delete(book);
			break;
		case'5':
			alter(book);
			break;
		case'6':
			search(book);
			break;
		case'7':
			rank(book);
			break;
		case'8':
			save(book);
			break;
		case'9':
			load(book);
			break;
		case'0':
			break;
		default:
			printf("暂无该功能，请重新输入！\n");
			break;
		}
	} while (ch1 != '0');
}