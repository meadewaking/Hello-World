#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
#define SUBJECT_COUNT 5				//�����Ŀ��
#define STUDENT_COUNT 30			//������󱣴�ѧ����Ŀ

typedef struct str_Student
{
	char num[20];
	char name[30];
	char sex[20];
	float score[5];
}Student;
//Student student;

int studentcount = 0;				//����ȫ�ֱ���
//���θ�ʽ
float sum(float array[],int length)
{
	int i;
	float sum;

	for (sum=0, i = 0; i < length; i++)
	{
		sum += array[i];
	}
	return sum;
}
int juge(char num1[],int length, Student s[])
{
	int i;
	for (i = 0; i < length ; i++)
	{
		if (!strcmp(num1, s[i].num))
		{
			return 1;
			break;
		}
	}
	if(i==length)
	{
		return 0;
	}
}
int num2index(char num[],Student student[],int index)
{
	int i;
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(num, student[i].num))
		{
			index = i + 1;
			return index;
		}
	}
}
void input(Student student[])
{
	int i, j;
	char ch3 = '0';

	studentcount = 0;
	for (j = 0; studentcount <STUDENT_COUNT; j++)
	{
		i = 0;
		do
		{
			if (i>0)
			{
				printf("��ѧ���Ѵ��ڣ����������룡\n");
			}
			printf("�������%d��ѧ����ѧ�ţ�",j + 1);
			scanf(" %s", student[j].num);
			i++;
		} while (j != 0 && juge(student[j].num, studentcount, student));
		printf("�������%d��ѧ����������",j + 1);
		scanf(" %s", student[j].name);
		printf("�������%d��ѧ�����Ա�", j + 1);
		scanf(" %s", student[j].sex);
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("������%d�Ƴɼ���", i + 1);
			scanf(" %f", &student[j].score[i]);
		}
		studentcount++;
		printf("�Ƿ�������룿 Y/N��");
		scanf(" %c",&ch3);
		if (ch3 == 'N' || ch3 == 'n')
		{
			break;
		}
	}
}
void output(Student student[])
{	
	float sum;
	int i, j;
	float sumcol[SUBJECT_COUNT] = { 0 };
	printf("ѧ��\t");
	printf("����\t");
	printf("�Ա�\t");
	printf("��ѧ\t");
	printf("����\t");
	printf("Ӣ��\t");
	printf("����\t");
	printf("��ѧ\t");
	printf("�ܷ�\t");
	printf("ƽ����\n");
	for (j = 0; j < studentcount; j++)
	{
		printf("%s\t", student[j].num);
		printf("%s\t", student[j].name);
		printf("%s\t", student[j].sex);
		sum = 0;
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("%.2f\t", student[j].score[i]);
			sum += student[j].score[i];
			sumcol[i] += student[j].score[i];
		}
		printf("%.2f\t", sum);
		printf("%.2f\n", sum / SUBJECT_COUNT);
	}
	printf("\t\t\t");
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("%.2f\t", sumcol[i]);
	}
	printf("\n\n");
}
void rank(Student student[])
{
	int i, j, k, max = 0;
	char ch;
	printf("1.����������\t2.���ܷ�����\n");
	printf("��ѡ��:");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		for ( k = 0; k < studentcount; k++)
		{
			for (i = 0; i < studentcount - 1; i++)
			{
				max = i;
				for (j = i + 1; j < studentcount; j++)
				{
					/*
					float summax = 0;
					float sumJ = 0;

					for ( k = 0; k < SUBJECT_COUNT; k++)
					{
					summax += student[max].score[k];
					}
					for ( k = 0; k < SUBJECT_COUNT; k++)
					{
					sumJ += student[j].score[k];
					}
					*/
		
					if (strcmp(student[i].name,student[j].name)>0)
					{
						max = j;
					}
				}

				if (max != i)
				{
					Student temp;
					temp = student[max];
					student[max] = student[i];
					student[i] = temp;

					/*
					char tempname[20];
					char tempcode[20];
					float tempscore[5];

					strcpy(tempname, name[max]);
					strcpy(name[max], name[i]);
					strcpy(name[i], tempname);

					strcpy(tempcode, code[max]);
					strcpy(code[max], code[i]);
					strcpy(code[i], tempcode);

					memcpy(tempscore, score[max], sizeof(tempscore));
					memcpy(score[max], score[i], sizeof(tempscore));
					memcpy(score[i], tempscore, sizeof(tempscore));
					*/
				}
			}
		}


			break;
	case'2':
		for (i = 0; i < studentcount - 1; i++)
		{
			max = i;
			for (j = i + 1; j < studentcount; j++)
			{
				/*
					float summax = 0;
					float sumJ = 0;

					for ( k = 0; k < SUBJECT_COUNT; k++)
					{
						summax += student[max].score[k];
					}
					for ( k = 0; k < SUBJECT_COUNT; k++)
					{
						sumJ += student[j].score[k];
					}
					*/
				if (sum(student[max].score, SUBJECT_COUNT) < sum(student[j].score, SUBJECT_COUNT))
				{
					max = j;
				}
			}

			if (max != i)
			{
				Student temp;
				temp = student[max];
				student[max] = student[i];
				student[i] = temp;

				/*
					char tempname[20];
					char tempcode[20];
					float tempscore[5];

					strcpy(tempname, name[max]);
					strcpy(name[max], name[i]);
					strcpy(name[i], tempname);

					strcpy(tempcode, code[max]);
					strcpy(code[max], code[i]);
					strcpy(code[i], tempcode);

					memcpy(tempscore, score[max], sizeof(tempscore));
					memcpy(score[max], score[i], sizeof(tempscore));
					memcpy(score[i], tempscore, sizeof(tempscore));
					*/
			}
			
		}
			break;
	default:
		printf("����������\n");
		break;
		}

		printf("������ɣ�\n\n");
	
}
void insert(Student student[])
{
	int index, i = 0;
	char tempnum[20];
	do
	{
		printf("������1��%d֮���λ��!\n",studentcount+1);
		printf("����������Ҫ�����λ�ã�");
		scanf(" %d", &index);
	} while (index<1 || index>studentcount + 1);

	for (i = studentcount; i > index - 2; i--)
	{
		student[i + 1] = student[i];
		/*
		strcpy(code[i + 1], code[i]);
		strcpy(name[i + 1], name[i]);
		memcpy(score[i + 1], score[i],sizeof(score[i+1]));
		*/
	}
	studentcount++;
	i = 0;
	do
	{
		if (i>0)
		{
			printf("��ѧ���Ѵ��ڣ����������룡\n");
		}
		printf("�������%d��ѧ����ѧ�ţ�", index);
		scanf(" %s", tempnum);
		i++;
	} while (juge(tempnum, studentcount, student));
	strcpy(student[index - 1].num, tempnum);
	printf("�������%d��ѧ����������", index);
	scanf(" %s", student[index - 1].name);
	printf("�������%d��ѧ�����Ա�", index);
	scanf(" %s", student[index - 1].sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &student[index - 1].score[i]);
	}
}
void delete(Student student[])
{
	int index, i;
	printf("����������Ҫɾ����λ�ã�");
	scanf(" %d", &index );
	for (i = index-1; i < studentcount; i++)
	{
		student[i] = student[i + 1];
		/*
		strcpy(code[i], code[i + 1]);
		strcpy(name[i], name[i + 1]);
		memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
		*/
	}
	studentcount--;
	printf("ɾ����ɣ�\n\n");
}
void alter(Student student[])
{
	int index = 0, i;
	char num[20];
	printf("����������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
	scanf(" %s", num);
	index=num2index(num, student, index);
	printf("������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
	scanf(" %s", student[index - 1].num);
	printf("������Ҫ�޸ĵ�ѧ����������");
	scanf(" %s", student[index - 1].name);
	printf("������Ҫ�޸ĵ�ѧ�����Ա�");
	scanf(" %s", student[index - 1].sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &student[index - 1].score[i]);
	}
	printf("�޸���ɣ�\n\n");
}
void search(Student student[])
{
	int index, i, j, k = 0;
	float sum = 0.0;
	char ch;
	char temp_num[20];
	char temp_name[20];
	printf("1.��λ�ò�ѯ\t2.��ѧ�Ų�ѯ\t3.����������\n");
	printf("��������Ҫ���ҵķ�ʽ��");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		printf("��������Ҫ���ҵ�ѧ����λ����");
		scanf(" %d", &index);
		printf("ѧ��\t");
		printf("����\t");
		printf("��ѧ\t");
		printf("����\t");
		printf("Ӣ��\t");
		printf("����\t");
		printf("��ѧ\t");
		printf("�ܷ�\t");
		printf("ƽ����\n");
		printf("%s\t", student[index - 1].num);
		printf("%s\t", student[index - 1].name);
		printf("%s\t", student[index - 1].sex);
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("%.2f\t", student[index - 1].score[i]);
			sum += student[index - 1].score[i];
		}
		printf("%.2f\t", sum);
		printf("%.2f\n", sum / SUBJECT_COUNT);
		printf("\n\n");
		break;
	case'2':
		printf("��������Ҫ���ҵ�ѧ����ѧ�ţ�");
		scanf(" %s", temp_num);
		for ( i = 0; i < studentcount; i++)
		{
			if (!strcmp(temp_num, student[i].num))
			{
				printf("ѧ��\t");
				printf("����\t");
				printf("��ѧ\t");
				printf("����\t");
				printf("Ӣ��\t");
				printf("����\t");
				printf("��ѧ\t");
				printf("�ܷ�\t");
				printf("ƽ����\n");
				printf("%s\t", student[i].num);
				printf("%s\t", student[i].name);
				printf("%s\t", student[i].sex);
				for (j = 0; j < SUBJECT_COUNT; j++)
				{
					printf("%.2f\t", student[i].score[j]);
					sum += student[i].score[j];
				}
				printf("%.2f\t", sum);
				printf("%.2f\n", sum / SUBJECT_COUNT);
				printf("\n\n");
				break;
			}
		}
		if (i==studentcount)
		{
			printf("��Ҫ�ҵ�ѧ�������ڣ�\n");
		}
		break;
	case'3':
		printf("��������Ҫ���ҵ�ѧ����������");
		scanf(" %s", temp_name);
		for (i = 0; i < studentcount; i++)
		{
			if (!strcmp(temp_name, student[i].name))
			{
				if (k==0)
				{
					printf("ѧ��\t");
					printf("����\t");
					printf("��ѧ\t");
					printf("����\t");
					printf("Ӣ��\t");
					printf("����\t");
					printf("��ѧ\t");
					printf("�ܷ�\t");
					printf("ƽ����\n");
				}

				printf("%s\t", student[i].num);
				printf("%s\t", student[i].name);
				printf("%s\t", student[i].sex);
				for (j = 0; j < SUBJECT_COUNT; j++)
				{
					printf("%.2f\t", student[i].score[j]);
					sum += student[i].score[j];
				}
				printf("%.2f\t", sum);
				printf("%.2f\n", sum / SUBJECT_COUNT);
				k++;
			}
		}
		if (k == 0)
		{
			printf("��Ҫ�ҵ�ѧ�������ڣ�\n");
		}
		break;
	default:
		printf("���������룡\n");
		break;
	}
}
void save(Student student[])
{	
	FILE*fp = fopen("D:\\StudentMis.txt", "wb");
	int i;
	if (fp==NULL)
	{
		printf("�޷����棡\n");
		return;
	}
	fprintf(fp, "%02d", studentcount);
	for ( i = 0; i < studentcount; i++)
	{
		fwrite(&student[i], sizeof(Student), 1, fp);
	}
	/*
	//float sum;
	int i, j;
	//float sumcol[SUBJECT_COUNT] = { 0 };
	/*
	fprintf(fp, "ѧ��\t");
	fprintf(fp, "����\t");
	fprintf(fp, "�Ա�\t");
	fprintf(fp, "��ѧ\t");
	fprintf(fp, "����\t");
	fprintf(fp, "Ӣ��\t");
	fprintf(fp, "����\t");
	fprintf(fp, "��ѧ\t");
	fprintf(fp, "�ܷ�\t");
	fprintf(fp, "ƽ����\n");
	*/
	/*
	for (j = 0; j < studentcount; j++)
	{
		fprintf(fp, "%s\t", student[j].num);
		fprintf(fp, "%s\t", student[j].name);
		fprintf(fp, "%s\t", student[j].sex);
		//sum = 0;
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			fprintf(fp,"%.2f\t", student[j].score[i]);
			//sum += student[j].score[i];
			//sumcol[i] += student[j].score[i];
		}
		//fprintf(fp, "%.2f\t", sum);
		//fprintf(fp, "%.2f\n", sum / SUBJECT_COUNT);
		fprintf(fp, "\n");
	}
	/*
	fprintf(fp, "\t\t");
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		fprintf(fp, "%.2f\t", sumcol[i]);
	}
	*/
	/*
	fprintf(fp, "\n\n");
	fclose(fp);
	*/
	fclose(fp);
	printf("����ɹ���\n");
}
void load(Student student[])
{
	FILE*fp = fopen("D:\\StudentMis.txt", "rb");
	int j;

	fscanf(fp, "%02d", &studentcount);
	fseek(fp, 2, 0);
	for ( j = 0; j < studentcount; j++)
	{
		fread(&student[j], sizeof(Student), 1, fp);
	}
	/*
		printf("�Ƿ�������룿 Y/N��");
		fscanf(fp, " %c", &ch3);
		if (ch3 == 'N' || ch3 == 'n')
		{
			break;
		}
		*/
	fclose(fp);
	printf("������ɣ�\n\n");
}
void main()
{
	char ch1;
	Student student[STUDENT_COUNT];

	printf("������������������������������������������Ƽ�Ӧ�ÿγ���Ŀ��ѧ���ɼ�����ϵͳ������������������������������������������\n\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t����������Ȩ����\n\n");
	do
	{
		printf("\t\t\t1.����2.���3.����4.ɾ��5.�޸�6.����7.����8.����9.����0.�˳�\n");
		printf("������0~9��");
		scanf(" %c", &ch1);

		switch (ch1)
		{
		case'1':
			input(student);		//���ú����������ò���
			break;
		case'2':
			output(student);
			break;
		case'3':
			insert(student);
			break;
		case'4':
			delete(student);
			break;
		case'5':
			alter(student);
			break;
		case'6':
			search(student);
			break;
		case'7':
			rank(student);
			break;
		case'8':
			save(student);
			break;
		case'9':
			load(student);
			break;
		case'0':
			break;
		default:
			printf("���޸ù��ܣ����������룡\n");
			break;
		}
	} while (ch1!='0');
}