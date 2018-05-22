#include <stdio.h>         //����Ԥ��������  �ļ���������
#include <string.h>        //����Ԥ��������  ʹ���ַ�����
#include <stdlib.h>        //����Ԥ��������  ����C���Ա�׼�⺯��
#pragma warning(disable:4996)
#define BOOK_COUNT 30
#define NUMBER_COUNT 3
//�ṹ��
typedef struct str_Book 
{
	char * number;
	char * name;
	char * kind;
	char * auther;
	char * press;
} Book;
//�Զ��庯��
int bookCount = 0;

Book * inputBook(int);                        //����ָ��
void inputBooks(Book * []);                   //�������뺯��
void outputBook(Book *);                      //�����������
void outputBooks(Book * []);                  //�����������

void insertBooks(Book * []);                  //������뺯��
void borrowBooks(Book * []);                  //����������

int fintBookByNumber(Book * [], char []);     //���尴�ձ�Ų��Һ���
int fintBookByName(Book * [], char []);       //���尴�ձ�Ų��Һ���
void modifyBooks(Book * []);                  //������Һ���
void findBooks(Book * []);      
void findBook1s(Book * []);
void findBook2s(Book * []);

void sortBooks(Book * []);                    //����������
void saveBooks(Book * []);                    //���屣�溯��
void loadBooks(Book * []);                    //������غ���

char * inputNumber(Book *[]);  
float sumFloat(float [], int);                //���帡����
char inputChar(char, char);                   //�����ַ�
int inputInteger(int, int);                   //�������
float inputFloat(float, float);               //���帡����
char * inputString();                         //�����ַ�

float sumFloat(float array[], int length)     //��������
{
	int i;     
    float sum; 

	for(sum = 0, i = 0; i < length; i++)
		sum += array[i];
	return sum;
}

char inputChar(char min, char max)            //��������
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



int inputInteger(int min, int max)           //��������
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

float inputFloat(float min, float max)     //��������
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

char * inputNumber(Book * books[])           
{
	char * ch;                        //�����ַ���
	int i = 0;                        //��������i
	int j = 0;                        //��������j

	do {

		j = 0;

		ch = inputString();          //���ú���

		for (i = 0; i < bookCount; i++)
		{
			if (strcmp(books[i]->number, ch) == 0)//��Strcmpͨ��ָ����ұ�ţ�����ظ�
			{
				printf("����ظ������������룺");//���������ظ�������������
				j = 1;
			}
		}
	} while (j == 1);

	return ch;
	free(ch);                                //�ͷ��ڴ�
}

void main()
{
	Book * book[BOOK_COUNT];
	char choice;
  
	printf("                                        ������Ƽ�Ӧ�ÿγ���Ŀ��ͼ�����ϵͳ\n");
	printf("                     	����������������������������Ȩ��ICID���С�����������������������\n");

	do {


		printf("\t\t\t\t\t �q�T�T�T�T�T�T�T�T�T���������T�T�T�r\n");  
        printf("\t\t\t\t\t��         ͼ����Ϣ����ϵͳ         ��\n");  
        printf("\t\t\t\t\t�t�T�T�T���������T�T�T�T�T�T�T�T�T�T�s\n");  
        printf("   ������������������������������������������������������������������������������������������������������������������\n");  
        printf("   ��     1. ¼��ͼ����Ϣ      2. ���ͼ����Ϣ     3. ���ӹ黹ͼ��      4. ɾ�����ͼ��     5. �޸�ͼ����Ϣ        ��\n");  
        printf("   ��                                                                                                              ��\n");  
        printf("   ��     6. ����ͼ����Ϣ      7. ͼ����Ϣ����     8. ͼ����Ϣ����      9. ͼ����Ϣ����     0. �˳� ϵͳ           ��\n"); 
        printf("   ������������������������������������������������������������������������������������������������������������������\n");  
        printf("\t\t����ѡ��(0-9):");  
		choice = inputChar('0', '9');
		switch (choice)
		{
		case '1':
			inputBooks(book);   //����
			break;
		case '2': 
			outputBooks(book);  //���
			break;
		case '3': 
			insertBooks(book);  //����
			break;
		case '4':
		    borrowBooks(book);  //���
			break;
		case '5':
			modifyBooks(book);  //ɾ��
			break;
		case '6':
			findBooks(book);    //����
			break;
		case '7':
			sortBooks(book);    //����
		case '8':
			saveBooks(book);    //����
			break;
		case '9':
			loadBooks(book);    //����
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
//����������� �����
//¼��ͼ����Ϣ
Book * inputBook(int j)							//����һ�������Ϣ
{
	Book * pBook = (Book *)malloc(sizeof(Book));     //����ṹ��Bookָ��
	printf("������%d��ͼ��ı�ţ�", j + 1);
	pBook -> number = inputNumber(pBook);                 //ͨ���ṹ��ָ����ʽṹ���Աnumber
	printf("������%d��ͼ������ƣ�", j + 1);
	pBook ->name = inputString();                    //ͨ���ṹ��ָ����ʽṹ���Աname	
	printf("������%d��ͼ������", j + 1);
	pBook ->kind = inputString();                    //ͨ���ṹ��ָ����ʽṹ���Աkind
	printf("������%d��ͼ������ߣ�", j + 1);
	pBook ->auther = inputString();                  //ͨ���ṹ��ָ����ʽṹ���Աauther	
	printf("������%d��ͼ��ĳ����磺", j + 1);
	pBook ->press = inputString();                   //ͨ���ṹ��ָ����ʽṹ���Աpress
	return pBook;	
}
//�����ڴ�ռ䣬������벢���ظø��ڴ��ָ��


void inputBooks(Book * books[])                      //�������뺯��
{
	int j;                                           //�������ͱ���j
	char choise;                                     //�����ַ��ͱ���j

	for (j =bookCount ; bookCount < BOOK_COUNT; j++) //���������С��30ʱ��ִ��ѭ��
	{
	books[j] = inputBook(j);						 //����inputBook����������һ�������Ϣ
	bookCount++;									 //ȫ�ֱ�����1��ÿѭ��һ�������������һ��

	printf("�Ƿ������(Y|N):");                      //��ʾ�û��Ƿ����
	choise=inputChar('Y','y');  							 //����ѡ��
	if(choise != 'Y' && choise != 'y')
	break;
	}
}
//�鿴ͼ����Ϣ
void outputBook(Book * pBook)                        //���һ���麯��Ϣ
{
printf("%s\t", pBook->number);                      //ͨ���ṹ��ָ����ʽṹ���Աnumber
printf("%s\t", pBook->name);                        //ͨ���ṹ��ָ����ʽṹ���Աnumber
printf("%s\t", pBook->kind);                        //ͨ���ṹ��ָ����ʽṹ���Աnumber
printf("%s\t", pBook->auther);                      //ͨ���ṹ��ָ����ʽṹ���Աnumber
printf("%s\t", pBook->press);                       //ͨ���ṹ��ָ����ʽṹ���Աnumber
printf("\n");
}

void outputBooks(Book * books[])                   //�����������
{
int j;                                             //�������ͱ���j

printf("ͼ����\t����\t���\t����\t������\n");    //�����ͷ

for (j = 0; j < bookCount; j++)					   //��jС��bookCountʱ��ѭ�����
{
outputBook(books[j]);							   //��������Ϣ
}
printf("\n");									   //����
}
//�ߴϣ����룩
//���ͼ����Ϣ
void insertBooks(Book * book[])    
{
	int index;                              //����һ��������
	int i;                                  //����i

	printf("������Ҫ�����λ�ã�");
	index = inputInteger(1, bookCount + 1); //�������ã�������֤

	for (i = bookCount+1; i > index - 1; i--)
		book[i] = book[i - 1];              //�Ѻ�һ��ֵ��ֵ��ǰһ������λ��ǰ�ơ�

	book[index - 1] = inputBook(index - 1); //���������Ϣ��ֵ����Ҫ�����λ��
	bookCount++;                            //ȫ�ֱ���+1��ÿѭ��һ�ζ�һ����
 
	printf("����ɹ���");                   //�������ɹ���
	printf("\n");
}
//������ɾ����
//ɾ��ͼ����Ϣ
void borrowBooks(Book * book[])               //����һ���޸ĺ�����������һ��Student������student 
{
	int index;                                //�������α���index
	int i;                                    //�������α���i

	printf("�������Ѿ������ͼ���ţ�");     //�������Ѿ������ͼ���ţ�
	index = inputInteger(1,bookCount);        //����inputInteger����

	for(i = index - 1; i < bookCount - 1; i++)//���������0��ʼ����
	book[i] = book[i + 1];                    //ɾ��֮��������Ϣ��ǰ��

	bookCount--;                              //ȫ�ֱ�����1��ÿѭ��һ��bookcount-1(�鱻�������������)
	printf("�����Ѿ����!\n");                //�����Ѿ����
}
//�������޸ģ�
//�޸�ͼ����Ϣ
void modifyBooks(Book * book[])               //����һ���޸ĺ�����������һ��Student������student
{
	int index;
	char * number;

	printf("��������Ҫ�޸ĵı�ţ�");         //������ʾ��������ѧ��
	number = inputString();                   //����������ݱ���number��ֵҪ�ø�ʽ������%d��
	
	index = fintBookByNumber(book,number);    //�ѱ����Ϣת����λ����Ϣ
	
	if(index >=bookCount)                     //
	{
		printf("�޸�ͼ����Ϣ�������޸ģ�\n");
		return;
	}

	outputBook(book[index]);                   //�����������
	book[index] = inputBook(index);            //�������뺯������ԭ��λ�õ���Ϣ��������
}
//�����������ң�
//����ͼ����Ϣ
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

	void findBook1s(Book * book[])                         //ѧ�Ų���
	{
		int index;
		char number[20];
		printf("������ͼ���ţ�");
			scanf(" %s",&number);//������
			
			index = fintBookByNumber(book,number);

			if(index==-1)
			{
				printf("�޸�ͼ����Ϣ��\n");
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

			if(index==-1)
			{
				printf("�޸�ͼ����Ϣ��\n");
				return;
			}
			outputBook(book[index]);
	}
    void findBooks(Book * book[])
	{
		char choice = '0';
			printf("1.����Ų��ң� 2.���������ң� \n");
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
//�ۿ��Σ�����
//ͼ����Ϣ����
void sortBooks(Book * book[])
{
	int max;
	int i,j;

	char choice = '0';
	printf("1.������� 2.�������� \n");//�Ӳ˵�
	printf("��ѡ��1-2����");
	scanf(" %c",&choice);
	switch(choice)  
	{
	case'1':
		 for (i=0;i<bookCount-1;i++)                           //ѭ��
		{
		   max=i;                                              //���
		   for(j=i+1; j<bookCount; j++)                        //ѭ��
				if(strcmp(book[max]->number,book[j]->number)>0)//if����жϣ��Ƚϴ�С
					max = j;
			if(max!=i)
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];                       //����
				book[i] = temp;      
			}
		}
    	printf("������ɣ�\n");	                           //��� 
			break;
	case'2':
		for (i=0;i<bookCount-1;i++)                        //ѭ��
		{
		   max=i;           
		   for(j=i+1; j<bookCount; j++)                    //ѭ��
				if(strcmp(book[max]->name,book[j]->name)>0)//�ַ����Ƚϴ�С
					max = j;
			if(max!=i)                                     //���������
			{
				Book * temp;
				temp = book[max];
				book[max] = book[i];
				book[i] = temp;      
			}
		}
    	printf("������ɣ�\n");	                           
		//��ʾ
			break;
	default:
		printf("���޸ù��ܣ�\n");
			break;
	}
    
}
//Ҧ���񣨱��� ���أ�
//����ͼ����Ϣ
void saveBooks(Book * book[]) 
{
	FILE * fp = fopen("c:\\temp\\student.txt", "w"); //���ļ���
	int j;                                           //�������i,j
	printf("���ڱ��棬�������ĵȺ�\n");            //��ʾ���ڱ���
	printf("����ɹ���\n");                          //��ʾ����ɹ�

	if (fp == NULL)                                  //���Ϊ��
	{
	printf("�޷����棡\n\a");                        //��ʾ�޷�����
	return;
	}

	for (j = 0; j < bookCount; j++)                  //ѭ��
	{
		fprintf(fp,"%s\t", book[j]->number);         //ȡ��book��ָ��Ľṹ���а�����������number
		fprintf(fp,"%s\t", book[j]->name);           //ȡ��book��ָ��Ľṹ���а�����������name
		fprintf(fp,"%s\t", book[j]->kind);           //ȡ��book��ָ��Ľṹ���а�����������kind
		fprintf(fp,"%s\t", book[j]->auther);         //ȡ��book��ָ��Ľṹ���а�����������auther
		fprintf(fp,"%s\t", book[j]->press);          //ȡ��book��ָ��Ľṹ���а�����������press
		fprintf(fp, "\n");                           //���
	}
	fclose(fp);
}
//����ͼ����Ϣ
void loadBooks(Book * book[])
{
	int j;                                           //��������i
	FILE * fp = fopen("c:\\temp\\student.txt","r");  //�����ṹ��ָ��

	if (fp == NULL)                                  //���pf����NULL
	{
	printf("�ļ���д����!\n\a");                     //����ļ���Щ����
	return;
	}
	bookCount = 0;
	for (j = 0; j < BOOK_COUNT && !feof(fp); j++)    //ѭ��
	{
	book[bookCount] = (Book *)malloc(sizeof(Book));  //ָ���ڴ�   �ڶ�̬�ڴ��������ַ���
	book[bookCount]->number = loadString(fp);        //���ļ����ҵ��ṹ���з�����
	book[bookCount]->name = loadString(fp);          //���ļ����ҵ��ṹ���з�������
	book[bookCount]->kind = loadString(fp);          //���ļ����ҵ��ṹ���з�������
	book[bookCount]->auther = loadString(fp);        //���ļ����ҵ��ṹ���з�������
	book[bookCount]->press = loadString(fp);         //���ļ����ҵ��ṹ���з��������

	fscanf(fp, "\n");
	bookCount++;                                     //ѭ������������Ϣ
	if(feof(fp))                                     //��ֹ
		break;
	}
	printf("�ɹ�����%d��ͼ����Ϣ��\n",bookCount);    //��ʾ���سɹ�

}









