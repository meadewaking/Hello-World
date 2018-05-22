#include<stdio.h>
#include<string.h>
#include<malloc.h>
#include <string.h>
#include <winsock2.h>
#pragma comment(lib, "Ws2_32.lib")
#pragma warning(disable:4996)
#define SUBJECT_COUNT 5				//�����Ŀ��
#define STUDENT_COUNT 30			//������󱣴�ѧ����Ŀ

typedef struct str_Student
{
	char * num;
	char * name;
	char * sex;
	float score[5];		//����ָ��ṹ�����,������̬�ڴ�
}Student;
typedef struct str_Fstudent
{
	char num[20];
	char name[30];
	char sex[20];
	float score[5];		//������ʱ�ַ����ṹ�����,��ʱ�н�ָ��ָ�������
}Fstudent;
typedef struct str_Account
{
	char account[50];
	char password[50];
}Account;
//Student student;
int studentcount = 0;				//����ȫ�ֱ���
									//���θ�ʽ
float sum(float array[], int length)
{
	int i;
	float sum;

	for (sum = 0, i = 0; i < length; i++)
	{
		sum += array[i];				//��ͺ���,��Ҫ��������,���Ӻ�������
	}
	return sum;
}
char * inputstring()					//�����ַ�������
{
	char buffer[256];
	char * temp;
	gets(buffer);						//��gets�������Զ����ո�
	temp = (char *)malloc(strlen(buffer) + 1);		//Ϊ��ʱָ����������ַ,+1Ԥ����ֹ��
	strcpy(temp, buffer);
	return temp;
}
char * inputstring2()
{
	char buffer[256];
	char * temp;
	getchar();					//��һ���ַ�,��ֹgets��ȡ�س�
	gets(buffer);
	temp = (char *)malloc(strlen(buffer) + 1);
	strcpy(temp, buffer);
	return temp;
}
char * temp()			//�ڶ�ȡʱ��student�ṹ���ʼ��
{
	char * temp;
	char ttemp[30] = { 0 };
	temp = (char *)malloc(sizeof(ttemp));
	strcpy(temp, ttemp);
	return temp;
}
char inputchar()
{
	char ch;
	char * tempch;
	char buffer[256];
	char * temp;
	do
	{
		scanf(" %s", buffer);						//��gets�������Զ����ո�
		temp = (char *)malloc(strlen(buffer) + 1);		//Ϊ��ʱָ����������ַ,+1Ԥ����ֹ��
		strcpy(temp, buffer);
		tempch = temp;
		if (strlen(tempch)>1)				//��ֹ�û�����һ�������ַ�,strlen����:��ȡ�ַ���ʵ��
		{
			printf("������һ���ַ���\n");
			continue;
		}
		sscanf(tempch, "%c", &ch);		//�ַ���д��
		free(tempch);
		return  ch;
	} while (1);
}
int num2index(char num[], Student * student[])
{
	int i, index;
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(num, student[i]->num))		//��ѧ����ѧ����Ϣת��Ϊѧ��λ�ò�����
		{
			index = i + 1;
			return index;
		}
	}
	return -1;
}
int juge(char * tnum, Student * student[])
{
	int i;
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(tnum, student[i]->num))		//�ж�ѧ���Ƿ��ظ�
		{
			return 1;			//�ظ�����intֵ1
		}
	}
	return 0;		//���ظ��򷵻�intֵ1
}
void rankbyname(Student * student[])		//����������
{
	int i, j, k, max = 0;
	for (k = 0; k < studentcount; k++)
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

				if (strcmp(student[i]->name, student[j]->name)>0)		//�Խ���������������,�ҳ�ÿһ���������asciiֵ
				{
					max = j;
				}
			}

			if (max != i)
			{
				Student * temp;
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
}
void rankbyscore(Student * student[])
{
	int i, j, max = 0;
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
			if (sum(student[max]->score, SUBJECT_COUNT) < sum(student[j]->score, SUBJECT_COUNT))
			{
				max = j;									//ѡ������
			}
		}

		if (max != i)
		{
			Student * temp;
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
void searchbyindex(Student * student[])		//��λ�ò���
{
	int index, i;
	float sum = 0.0;
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
	printf("%s\t", student[index - 1]->num);
	printf("%s\t", student[index - 1]->name);
	printf("%s\t", student[index - 1]->sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("%.2f\t", student[index - 1]->score[i]);
		sum += student[index - 1]->score[i];
	}
	printf("%.2f\t", sum);
	printf("%.2f\n", sum / SUBJECT_COUNT);
	printf("\n\n");
}
void searchbynum(Student * student[])		//��ѧ�Ų���
{
	int i, j;
	float sum = 0.0;
	char temp_num[20];
	printf("��������Ҫ���ҵ�ѧ����ѧ�ţ�");
	scanf(" %s", temp_num);
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(temp_num, student[i]->num))
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
			printf("%s\t", student[i]->num);
			printf("%s\t", student[i]->name);
			printf("%s\t", student[i]->sex);
			for (j = 0; j < SUBJECT_COUNT; j++)
			{
				printf("%.2f\t", student[i]->score[j]);
				sum += student[i]->score[j];
			}
			printf("%.2f\t", sum);
			printf("%.2f\n", sum / SUBJECT_COUNT);
			printf("\n\n");
			break;
		}
	}
	if (i == studentcount)
	{
		printf("��Ҫ�ҵ�ѧ�������ڣ�\n");
	}
}
void searchbyname(Student * student[])		//����������
{
	int i, j, k = 0;
	float sum = 0.0;
	char temp_name[20];
	printf("��������Ҫ���ҵ�ѧ����������");
	scanf(" %s", temp_name);
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(temp_name, student[i]->name))
		{
			if (k == 0)
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

			printf("%s\t", student[i]->num);
			printf("%s\t", student[i]->name);
			printf("%s\t", student[i]->sex);
			for (j = 0; j < SUBJECT_COUNT; j++)
			{
				printf("%.2f\t", student[i]->score[j]);
				sum += student[i]->score[j];
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
}
void suggest()
{

	WORD wVersionRequested;
	WSADATA wsaData;
	int err;
	int i = 0, n = 0;

	wVersionRequested = MAKEWORD(2, 2);

	err = WSAStartup(wVersionRequested, &wsaData);
	if (err != 0) {

		return;
	}


	if (LOBYTE(wsaData.wVersion) != 2 ||
		HIBYTE(wsaData.wVersion) != 2)
	{

		WSACleanup();
		return;
	}
	SOCKET socketClient = socket(AF_INET, SOCK_STREAM, 0);
	SOCKADDR_IN addrSrv;
	addrSrv.sin_family = AF_INET;
	addrSrv.sin_port = htons(5000);
	addrSrv.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//�������˵�IP��ַ

	connect(socketClient, (SOCKADDR *)&addrSrv, sizeof(SOCKADDR));

	char recvBuf[256];
	char sendBuf[256];
	for (n = 0; n < 2; n++)
	{
		recv(socketClient, recvBuf, 256, 0);
		printf("%s\n", recvBuf);
		if (i == 0)
		{
			break;
		}
	}
	//printf("��������������");
	scanf("%s", &sendBuf);
	printf("\n��л���ʹ�ã�\n");
	send(socketClient, sendBuf, strlen(sendBuf) + 1, 0);
	closesocket(socketClient);
	WSACleanup();
	i++;
}
Student * inputsingle(int j, Student * student[])		//�����ڴ�ռ䣬������룬�����ظ��ڴ�ռ��ָ��
{
	int i;
	i = 0;
	int k = 0;
	char * tnum;			//��ʱָ�����ڴ���,�ṹ��ֱ�Ӵ�������δ֪ԭ�����
	Student * tstudent = (Student *)malloc(sizeof(Student));		//Ϊ�ṹ�������ڴ�ռ�
	do
	{
		if (k != 0)
		{
			printf("ѧ���Ѵ��ڣ�����������\n");
		}
		printf("�������%d��ѧ����ѧ�ţ�", j + 1);
		if (k == 0)							//��ֹgets������ȡ�س�
		{
			tnum = inputstring2();
		}
		else
		{
			tnum = inputstring();
		}
		k++;
	} while (studentcount != 0 && juge(tnum, student));		//����&&�����·,�����������,�������Ч��
	tstudent->num = tnum;
	printf("�������%d��ѧ����������", j + 1);
	tstudent->name = inputstring();
	printf("�������%d��ѧ�����Ա�", j + 1);
	tstudent->sex = inputstring();
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &tstudent->score[i]);
	}
	return tstudent;			//���ؽṹ��ָ��

}
void input(Student * student[])		//�����ڴ�ռ䣬������룬�����ظ��ڴ�ռ��ָ��
{
	int j;
	char ch3 = '0';
	studentcount = 0;
	for (j = 0; studentcount <STUDENT_COUNT; j++)		//�û�������������޶�����������Ľṹ�����
	{
		student[j] = inputsingle(j, student);
		studentcount++;
		printf("�Ƿ�������룿 Y/N��");
		scanf(" %c", &ch3);
		if (ch3 == 'N' || ch3 == 'n')
		{
			break;
		}
	}
}
void output(Student * student[])
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
	for (j = 0; j < studentcount; j++)		//������ʾ
	{
		printf("%s\t", student[j]->num);
		printf("%s\t", student[j]->name);
		printf("%s\t", student[j]->sex);
		sum = 0;
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("%.2f\t", student[j]->score[i]);
			sum += student[j]->score[i];		//�����ܷ�
			sumcol[i] += student[j]->score[i];		//��ͳ��
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
void rank(Student * student[])
{
	char ch;
	printf("1.����������\t2.���ܷ�����\n");
	printf("��ѡ��:");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		rankbyname(student);
		break;
	case'2':
		rankbyscore(student);
		break;
	default:
		printf("����������\n");
		break;
	}

	printf("������ɣ�\n\n");

}
void insert(Student * student[])
{
	int index, i = 0;
	do
	{
		printf("������1��%d֮���λ��!\n", studentcount + 1);
		printf("����������Ҫ�����λ�ã�");
		scanf(" %d", &index);
	} while (index<1 || index>studentcount + 1);

	for (i = studentcount; i > index - 1; i--)
	{
		student[i] = student[i - 1];
		/*
		strcpy(code[i + 1], code[i]);
		strcpy(name[i + 1], name[i]);
		memcpy(score[i + 1], score[i],sizeof(score[i+1]));
		*/
	}
	student[index - 1] = inputsingle(index - 1, student);		//������λ�ÿճ�����inputsingle����
	studentcount++;
	/*
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
	strcpy(student[index - 1]->num, tempnum);
	printf("�������%d��ѧ����������", index);
	scanf(" %s", student[index - 1]->name);
	printf("�������%d��ѧ�����Ա�", index);
	scanf(" %s", student[index - 1]->sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
	printf("������%d�Ƴɼ���", i + 1);
	scanf(" %f", &student[index - 1]->score[i]);
	}
	*/
}
void delete(Student * student[])
{
	int index, i;
	printf("����������Ҫɾ����λ�ã�");
	scanf(" %d", &index);
	for (i = index - 1; i < studentcount; i++)
	{
		student[i] = student[i + 1];		//������ɾ����λ�ø��ǵ�
											/*
											strcpy(code[i], code[i + 1]);
											strcpy(name[i], name[i + 1]);
											memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
											*/
	}
	studentcount--;
	printf("ɾ����ɣ�\n\n");
}
void alter(Student * student[])
{
	int index = 0, i;
	char num[20];
	printf("����������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
	scanf(" %s", num);
	index = num2index(num, student);
	if (index == -1)
	{
		printf("��ѧ�Ų�����!\n\n");
		return;
	}
	printf("������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
	scanf(" %s", student[index - 1]->num);
	printf("������Ҫ�޸ĵ�ѧ����������");
	scanf(" %s", student[index - 1]->name);
	printf("������Ҫ�޸ĵ�ѧ�����Ա�");
	scanf(" %s", student[index - 1]->sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &student[index - 1]->score[i]);
	}
	printf("�޸���ɣ�\n\n");
}
void search(Student * student[])
{
	char ch;
	printf("1.��λ�ò�ѯ\t2.��ѧ�Ų�ѯ\t3.����������\n");
	printf("��������Ҫ���ҵķ�ʽ��");
	scanf(" %c", &ch);
	switch (ch)
	{
	case'1':
		searchbyindex(student);
		break;
	case'2':
		searchbynum(student);
		break;
	case'3':
		searchbyname(student);
		break;
	default:
		printf("���������룡\n");
		break;
	}
}
void save(Student * student[])
{
	Fstudent fstudent;		//������ʱ�ַ����ṹ��
	FILE*fp = fopen("D:\\StudentMis2.0.txt", "wb");
	int i, j;
	if (fp == NULL)
	{
		printf("�޷����棡\n");
		return;
	}
	fprintf(fp, "%02d", studentcount);
	for (i = 0; i < studentcount; i++)
	{
		strcpy(fstudent.num, student[i]->num);
		strcpy(fstudent.name, student[i]->name);
		strcpy(fstudent.sex, student[i]->sex);		//��ָ��ֵ������ʱ�ṹ��
		for (j = 0; j < SUBJECT_COUNT; j++)
		{
			fstudent.score[j] = student[i]->score[j];		//����ʱ�ṹ��ɿ鰴��������ʽ����
		}
		fwrite(&fstudent, sizeof(Fstudent), 1, fp);
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
void load(Student * student[])
{
	Fstudent fstudent;
	FILE*fp = fopen("D:\\StudentMis2.0.txt", "rb");
	int i, j;

	fscanf(fp, "%02d", &studentcount);
	fseek(fp, 2, 0);
	for (i = 0; i < studentcount; i++)
	{
		fread(&fstudent, sizeof(Fstudent), 1, fp);
		student[i] = (Student *)malloc(sizeof(Student));
		student[i]->num = temp();		//��temp������ʼstudent�ṹ��
		student[i]->name = temp();
		student[i]->sex = temp();
		strcpy(student[i]->num, fstudent.num);
		strcpy(student[i]->name, fstudent.name);
		strcpy(student[i]->sex, fstudent.sex);	//����ʱ�ַ�����ֵ����ָ��
		for (j = 0; j < SUBJECT_COUNT; j++)
		{
			student[i]->score[j] = fstudent.score[j];
		}

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
void mainTeacher()
{
	Sleep(1000);
	system("cls");
	char ch1;
	Student * student[STUDENT_COUNT];
	printf("������������������������������������������Ƽ�Ӧ�ÿγ���Ŀ��ѧ���ɼ�����ϵͳ������������������������������������������\n\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t����������Ȩ����\n\n");
	do
	{
		printf("\t\t\t1.����2.���3.����4.ɾ��5.�޸�6.����7.����8.����9.����0.�˳�\n");
		printf("������0~9��");
		ch1 = inputchar();
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
			suggest();
			break;
		default:
			printf("���޸ù��ܣ����������룡\n");
			break;
		}
	} while (ch1 != '0');
}
void mainStudent()
{
	Sleep(1000);
	system("cls");
	char ch1;
	Student * student[STUDENT_COUNT];
	printf("������������������������������������������Ƽ�Ӧ�ÿγ���Ŀ��ѧ���ɼ�����ϵͳ������������������������������������������\n\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t����������Ȩ����\n\n");
	do
	{
		printf("\t\t\t1.���\t\t2.����\t\t3.����\t\t4.����\t\t0.�˳�\n");
		printf("������0~4��");
		ch1 = inputchar();
		switch (ch1)
		{
		case'1':
			output(student);
			break;
		case'2':
			search(student);
			break;
		case'3':
			rank(student);
			break;
		case'4':
			load(student);
			break;
		case'0':
			suggest();
			break;
		default:
			printf("���޸ù��ܣ����������룡\n");
			break;
		}
	} while (ch1 != '0');
}
void main()
{
	char account[50], password[50];			//������ʱ�˻���������
	Account Taccount[3];					//���ý�ʦ��ѧ���ṹ��
	Account Saccount[3];
	FILE*fp = fopen("D:\\Taccount for SM.txt", "rb");
	int i, t = 0, s = 0, k = 0, ta, sa;
	for ( i = 0; i < STUDENT_COUNT; i++)		//��ȡ��ʦ���˺ź�����
	{
		fscanf(fp, "%s", Taccount[i].account);
		fscanf(fp, "%s", Taccount[i].password);
		if (feof)
		{
			break;
		}
	}
	fclose(fp);
	fopen("D:\\Saccount for SM.txt", "rb");
	for (i = 0; i < STUDENT_COUNT; i++)			//��ȡѧ�����˺ź�����
	{
		fscanf(fp, "%s", Saccount[i].account);
		fscanf(fp, "%s", Saccount[i].password);
		if (feof)
		{
			break;
		}
	}
	fclose(fp);
	do
	{
		if (k != 0)			//���������˺�û���ҵ���֤���˺Ų�����
		{
			printf("���˺Ų����ڣ����������룡\n");
		}
		printf("�������˻���");
		scanf(" %s", account);
		for ( i = 0; i < STUDENT_COUNT; i++)
		{
			if (!strcmp(account,Taccount[i].account))	
			{
				t = 1;			//�жϴ��˺Ŵ��ڣ���Ϊ��ʦ�˺�
				ta = i;			//����Ϊ��i���˺�
				break;
			}
		}
		for (i = 0; i < STUDENT_COUNT; i++)
		{
			if (!strcmp(account, Saccount[i].account))
			{
				s = 1;			//�жϴ��˺Ŵ��ڣ���Ϊѧ���˺�
				sa = i;			//����Ϊ��i���˺�
				break;
			}
		}
		k++;			
	} while (t==0&&s==0);		//�ж��˺Ų����ڵ������ѭ������
	k = 0;				//k��Ϊ������֤
	do
	{
		if (k != 0)
		{
			printf("�����벻��ȷ�����������룡\n");
		}
		printf("���������룺");
		scanf(" %s", password);
		if (t==1)		
		{
			if (!strcmp(password, Taccount[ta].password))	//�ȶ�������������ҵ��ĸ����˺ŵ�����
			{
				printf("\n�������ǽ�ʦ��\n");			
				mainTeacher();
				break;
			}
			else
			{
				k++;
			}
		}
		if (s == 1)
		{
			if (!strcmp(password, Saccount[sa].password))
			{
				printf("\n��������ѧ����\n");
				mainStudent();
				break;
			}
			else
			{
				k++;
			}
		}
	} while (1);

}