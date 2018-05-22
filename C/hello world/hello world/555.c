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
			printf("������һ���ַ���");
			continue;
		}

		sscanf(temp, " %c", &ch);
		free(temp);
		if (ch < min || ch > max)
			printf("������%c-%c֮����ַ�: ",min, max);
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
			printf("������%d-%d֮���ֵ: ",min, max);
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
			printf("������%.2f-%.2f֮���ֵ: ",min, max);
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

	printf("                                   ������Ƽ�Ӧ�ÿγ���Ŀ��ͼ�����ϵͳ\n");
	printf("                	����������������������������Ȩ��ICID���С�����������������������\n");

	do {
		printf("1.¼��ͼ����Ϣ 2.�鿴ͼ����Ϣ 3.��ӣ��黹��ͼ����Ϣ 4.ɾ��ͼ����Ϣ 5.�޸�ͼ����Ϣ 6.����ͼ����Ϣ 7.ͼ����Ϣ���� 8.ͼ����Ϣ���� 9.���� 0.�˳�\n");
		printf("��ѡ��");
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
			printf("û�иù���\n");
			break;
		}
	} while (choice != '0');

	printf("ллʹ��!\n");
}

//�����ڴ�ռ䣬������룬�����ظ��ڴ�ռ��ָ��
//¼��ͼ����Ϣ
Book * inputBook(int j)  
{

    //int i;
	Book * pBook = (Book *)malloc(sizeof(Book));
	printf("������%d��ͼ��ı�ţ�", j + 1);
    pBook -> number = inputString();//ͨ���ṹ��ָ����ʽṹ���Ա	
	printf("������%d��ͼ������ƣ�", j + 1);
	pBook ->name = inputString();
	printf("������%d��ͼ������", j + 1);
	pBook ->kind = inputString();
	printf("������%d��ͼ������ߣ�", j + 1);
	pBook ->auther = inputString();
	printf("������%d��ͼ��ĳ����磺", j + 1);
	pBook ->press = inputString();
	return pBook;
}//�����ڴ�ռ䣬������벢���ظø��ڴ��ָ��


void inputBooks(Book * books[])  
{

	
		int j;
	char choise;

	for (j =bookCount ; bookCount < BOOK_COUNT; j++)                 //student��s,p
	{
		books[j] = inputBook(j);
		bookCount++;
		
		printf("�Ƿ������(Y|N):");
		scanf(" %c", &choise);
		if(choise != 'Y' && choise != 'y')
			break;
	}
}
//�鿴ͼ����Ϣ
void outputBook(Book * pBook) //ֻ������
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

	printf("ͼ����\t����\t���\t����\t������\n");

	for (j = 0; j < bookCount; j++)
	{
		outputBook(books[j]);
	}
		printf("\n");
}
//���ͼ����Ϣ
void insertBooks(Book * book[]) 
{


	int index;
	int i;

	printf("������Ҫ�����λ�ã�");
	index = inputInteger(1, bookCount + 1);

	for (i = bookCount; i > index - 1; i--)
		book[i] = book[i - 1];

	book[index - 1] = inputBook(index - 1);
	bookCount++;

	printf("����ɹ���");
	printf("\n");
}
//ɾ��ͼ����Ϣ
void borrowBooks(Book * book[])  
{	
	int i;
	int index;

	printf("�������Ѿ������ͼ���ţ�");
    index = inputInteger(1,bookCount);

	for(i = index - 1; i < bookCount - 1; i++)
		book[i] = book[i + 1];

	bookCount--;
	printf("�����Ѿ����!\n");
}
//�޸�ͼ����Ϣ
void modifyBooks(Book * book[])//����һ���޸ĺ�����������һ��Student������student
{
	int index;
	char * number;

	printf("��������Ҫ�޸ĵı�ţ�");  //������ʾ��������ѧ��
	number = inputString();  //����������ݱ���number��ֵҪ�ø�ʽ������%d��
	
	index = fintBookByNumber(book,number);
	
	if(index >=bookCount)
	{
		printf("�޸�ͼ����Ϣ�������޸ģ�\n");
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

	void findBook1s(Book * book[])                         //ѧ�Ų���
	{
		int index;
		char number[20];
		printf("������ѧ�ţ�");
			scanf(" %s",&number);//����ѧ��
			
			index = fintBookByNumber(book,number);

			if(index>=bookCount)
			{
				printf("�޸�ѧ����Ϣ��\n");
				return;
			}
			outputBook(book[index]);//����
	}
	void findBook2s(Book * book[]) 
	{
		int index;
		char name[20];
		printf("������������");
			scanf(" %s",&name);

			index = fintBookByName(book,name);

			if(index>=bookCount)
			{
				printf("�޸�ѧ����Ϣ��\n");
				return;
			}
			outputBook(book[index]);
	}
    void findBooks(Book * book[])
	{
		char choice = '0';
			printf("1.��ѧ�Ų���! 2.���������ң� \n");
			printf("��ѡ��1-2����");
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
				printf("���޸ù��ܣ�\n");
			break;
			}
	}
//ͼ����Ϣ����
void sortBooks(Book * book[])
{
	int max;
	int i,j;

	char choice = '0';
	printf("1.������� 2.������������ \n");
	printf("��ѡ��1-2����");
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
    	printf("������ɣ�\n");	
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
    	printf("������ɣ�\n");	
			break;
	default:
		printf("���޸ù��ܣ�\n");
			break;
	}
    
}
//����ͼ����Ϣ
/*void saveBooks(Book * Book[])  
{

}	
//����ͼ����Ϣ
void loadBooks(Book * Book[])
{

}

	printf("ллʹ��!\n");
}*/

//�����ڴ�ռ䣬������룬�����ظ��ڴ�ռ��ָ��
//¼��ͼ����Ϣ

//(Student * pstudent,int j)*/







