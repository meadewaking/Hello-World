#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
#define BOOK_COUNT 30			//������󱣴�ͼ����Ŀ

typedef struct str_Book
{
	char num[20];
	char name[30];
	char kind[20];
	char auther[20];
	char press[20];
}Book;
//Book book;

int bookcount = 0;				//����ȫ�ֱ���
									//���θ�ʽ
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
				printf("�ñ���Ѵ��ڣ����������룡\n");
			}
			printf("�������%d��ͼ��ı�ţ�", j + 1);
			scanf(" %s", book[j].num);
			i++;
		} while (j != 0 && juge(book[j].num, bookcount, book));
		printf("�������%d��ͼ������ƣ�", j + 1);
		scanf(" %s", book[j].name);
		printf("�������%d��ͼ������ࣺ", j + 1);
		scanf(" %s", book[j].kind);
		printf("�������%d��ͼ������ߣ�", j + 1);
		scanf(" %s", book[j].auther);
		printf("�������%d��ͼ��ĳ����磺", j + 1);
		scanf(" %s", book[j].press);
		bookcount++;
		printf("�Ƿ�������룿 Y/N��");
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
	printf("���\t");
	printf("����\t");
	printf("����\t");
	printf("����\t");
	printf("������\n");
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
	printf("1.����������\n");
	printf("��ѡ��:");
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
		printf("����������\n");
		break;
	}

	printf("������ɣ�\n\n");

}
void insert(Book book[])
{
	int index, i = 0;
	char tempnum[20];
	do
	{
		printf("������1��%d֮���λ��!\n", bookcount + 1);
		printf("����������Ҫ�����λ�ã�");
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
			printf("�ñ���Ѵ��ڣ����������룡\n");
		}
		printf("�������%d��ͼ��ı�ţ�", index);
		scanf(" %s", tempnum);
		i++;
	} while (juge(tempnum, bookcount, book));
	strcpy(book[index - 1].num, tempnum);
	printf("�������%d��ͼ������ƣ�", index);
	scanf(" %s", book[index - 1].name);
	printf("�������%d��ͼ������ࣺ", index);
	scanf(" %s", book[index - 1].kind);
	printf("�������%d��ͼ������ߣ�", index);
	scanf(" %s", book[index - 1].auther);
	printf("�������%d��ͼ��ĳ����磺", index);
	scanf(" %s", book[index - 1].press);
}
void delete(Book book[])
{
	int index, i;
	printf("����������Ҫɾ����λ�ã�");
	scanf(" %d", &index);
	for (i = index - 1; i < bookcount; i++)
	{
		book[i] = book[i + 1];
	}
	bookcount--;
	printf("ɾ����ɣ�\n\n");
}
void alter(Book book[])
{
	int index = 0;
	char num[20];
	printf("����������Ҫ�޸ĵ�ͼ��ı�ţ�");
	scanf(" %s", num);
	index = num2index(num, book, index);
	printf("������Ҫ�޸ĵ�ͼ��ı�ţ�");
	scanf(" %s", book[index - 1].num);
	printf("������Ҫ�޸ĵ�ͼ������ƣ�");
	scanf(" %s", book[index - 1].name);
	printf("������Ҫ�޸ĵ�ͼ������ࣺ");
	scanf(" %s", book[index - 1].kind);
	printf("������Ҫ�޸ĵ�ͼ������ߣ�");
	scanf(" %s", book[index - 1].auther);
	printf("������Ҫ�޸ĵ�ͼ��ĳ����磺");
	scanf(" %s", book[index - 1].press);
	printf("�޸���ɣ�\n\n");
}
void search(Book book[])
{
	int index, i, k = 0;
	char ch;
	char temp_num[20];
	char temp_name[20];
	printf("1.��λ�ò�ѯ\t2.����Ų�ѯ\t3.�����Ʋ���\n");
	printf("��������Ҫ���ҵķ�ʽ��");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		printf("��������Ҫ���ҵ�ͼ���λ�ã�");
		scanf(" %d", &index);
		printf("���\t");
		printf("����\t");
		printf("����\t");
		printf("����\t");
		printf("������\n");
		printf("%s\t", book[index - 1].num);
		printf("%s\t", book[index - 1].name);
		printf("%s\t", book[index - 1].kind);
		printf("%s\t", book[index - 1].auther);
		printf("%s\t", book[index - 1].press);
		printf("\n");
		break;
	case'2':
		printf("��������Ҫ���ҵ�ͼ��ı�ţ�");
		scanf(" %s", temp_num);
		for (i = 0; i < bookcount; i++)
		{
			if (!strcmp(temp_num, book[i].num))
			{
				printf("���\t");
				printf("����\t");
				printf("����\t");
				printf("����\t");
				printf("������\n");
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
			printf("��Ҫ�ҵ�ͼ�鲻���ڣ�\n");
		}
		break;
	case'3':
		printf("��������Ҫ���ҵ�ͼ������ƣ�");
		scanf(" %s", temp_name);
		for (i = 0; i < bookcount; i++)
		{
			if (!strcmp(temp_name, book[i].name))
			{
				if (k == 0)
				{
					printf("���\t");
					printf("����\t");
					printf("����\t");
					printf("����\t");
					printf("������\n");
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
			printf("��Ҫ�ҵ�ͼ�鲻���ڣ�\n");
		}
		break;
	default:
		printf("���������룡\n");
		break;
	}
}
void save(Book book[])
{
	FILE*fp = fopen("D:\\BookMis.txt", "wb");
	int i;
	if (fp == NULL)
	{
		printf("�޷����棡\n");
		return;
	}
	fprintf(fp, "%02d", bookcount);
	for (i = 0; i < bookcount; i++)
	{
		fwrite(&book[i], sizeof(Book), 1, fp);
	}
	fclose(fp);
	printf("����ɹ���\n");
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
	printf("������ɣ�\n\n");
}
void main()
{
	char ch1;
	Book book[BOOK_COUNT];

	printf("����������������������������������������������������ͼ�����ϵͳ��������������������������������������������������������\n\n");
	do
	{
		printf("\t\t\t1.����2.���3.����4.ɾ��5.�޸�6.����7.����8.����9.����0.�˳�\n");
		printf("������0~9��");
		scanf(" %c", &ch1);

		switch (ch1)
		{
		case'1':
			input(book);		//���ú����������ò���
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
			printf("���޸ù��ܣ����������룡\n");
			break;
		}
	} while (ch1 != '0');
}