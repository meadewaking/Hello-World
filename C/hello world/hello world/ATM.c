#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
#define SUBJECT_COUNT 5				//�����Ŀ��
#define SDUDENT_COUNT 30			//������󱣴�ѧ����Ŀ
int studentcount = 0;				//����ȫ�ֱ���
									//���θ�ʽ
void input(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int i, j;
	char ch3 = '0';

	for (j = 0; studentcount <SDUDENT_COUNT; j++)
	{
		printf("�������%d��ѧ����ѧ�ţ�", j + 1);
		scanf(" %s", code[j]);
		printf("�������%d��ѧ����������", j + 1);
		scanf(" %s", name[j]);
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("������%d�Ƴɼ���", i + 1);
			scanf(" %f", &score[j][i]);
		}
		studentcount++;
		printf("�Ƿ�������룿 Y/N��");
		scanf(" %c", &ch3);
		if (ch3 == 'N' || ch3 == 'n')
		{
			break;
		}
	}
}
void output(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	float sum;
	int i, j;
	float sumcol[SUBJECT_COUNT] = { 0 };
	printf("ѧ��\t");
	printf("����\t");
	printf("��ѧ\t");
	printf("����\t");
	printf("Ӣ��\t");
	printf("����\t");
	printf("��ѧ\t");
	printf("�ܷ�\t");
	printf("ƽ����\n");
	for (j = 0; j < studentcount; j++)
	{
		printf("%s\t", code[j]);
		printf("%s\t", name[j]);
		sum = 0;
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("%.2f\t", score[j][i]);
			sum += score[j][i];
			sumcol[i] += score[j][i];
		}
		printf("%.2f\t", sum);
		printf("%.2f\n", sum / SUBJECT_COUNT);
	}
	printf("\t\t");
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("%.2f\t", sumcol[i]);
	}
	printf("\n\n");
}
void rank(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int i, j, k, max = 0;
	for (i = 0; i < studentcount - 1; i++)
	{
		max = i;
		for (j = i + 1; j < studentcount; j++)
		{
			float summax = 0;
			float sumJ = 0;
			for (k = 0; k < SUBJECT_COUNT; k++)
			{
				summax += score[max][k];
			}
			for (k = 0; k < SUBJECT_COUNT; k++)
			{
				sumJ += score[j][k];
			}
			if (summax<sumJ)
			{
				max = j;
			}
		}

		if (max != i)
		{
			char tempname[20];
			float tempscore[5];

			strcpy(tempname, code[max]);
			strcpy(code[max], name[i]);
			strcpy(name[i], tempname);

			memcpy(tempscore, score[max], sizeof(tempscore));
			memcpy(score[max], score[i], sizeof(tempscore));
			memcpy(score[i], tempscore, sizeof(tempscore));
		}
	}
	printf("������ɣ�\n\n");
}
void insert(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("����������Ҫ�����λ�ã�");
	scanf(" %d", &index);
	for (i = studentcount; i > index - 2; i--)
	{
		strcpy(code[i + 1], code[i]);
		strcpy(name[i + 1], name[i]);
		memcpy(score[i + 1], score[i], sizeof(score[i + 1]));
	}
	studentcount++;
	printf("�������%d��ѧ����ѧ�ţ�", index);
	scanf(" %s", code[index - 1]);
	printf("�������%d��ѧ����������", index);
	scanf(" %s", name[index - 1]);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &score[index - 1][i]);
	}
}
void delete(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("����������Ҫɾ����λ�ã�");
	scanf(" %d", &index);
	for (i = index - 1; i < studentcount; i++)
	{
		strcpy(code[i], code[i + 1]);
		strcpy(name[i], name[i + 1]);
		memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
	}
	studentcount--;
	printf("ɾ����ɣ�\n\n");
}
void alter(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("����������Ҫ�޸ĵ�λ�ã�");
	scanf(" %d", &index);
	for (i = index - 1; i < studentcount; i++)
	{
		strcpy(code[i], code[i + 1]);
		strcpy(name[i], name[i + 1]);
		memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
	}
	studentcount--;
	for (i = studentcount; i > index - 2; i--)
	{
		strcpy(code[i + 1], code[i]);
		strcpy(name[i + 1], name[i]);
		memcpy(score[i + 1], score[i], sizeof(score[i + 1]));
	}
	studentcount++;
	printf("������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
	scanf(" %s", code[index - 1]);
	printf("������Ҫ�޸ĵ�ѧ����������");
	scanf(" %s", name[index - 1]);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("������%d�Ƴɼ���", i + 1);
		scanf(" %f", &score[index - 1][i]);
	}
	printf("�޸���ɣ�\n\n");
}
void search(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
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
	printf("%s\t", code[index - 1]);
	printf("%s\t", name[index - 1]);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("%.2f\t", score[index - 1][i]);
		sum += score[index - 1][i];
	}
	printf("%.2f\t", sum);
	printf("%.2f\n", sum / SUBJECT_COUNT);
	printf("\n\n");
}
void main()
{
	char ch1;
	float score[SDUDENT_COUNT][SUBJECT_COUNT] = { 0 };
	char name[SDUDENT_COUNT][20] = { 0 };
	char code[SDUDENT_COUNT][10] = { 0 };
	printf("������Ƽ�Ӧ�ÿγ���Ŀ��ѧ���ɼ�����ϵͳ\n");
	printf("��������Ȩ����\n\n");
	do
	{
		printf("1.����2.���3.����4.ɾ��5.�޸�6.����7.����8.����9.����0.�˳�\n");
		printf("������0~9��");
		scanf(" %c", &ch1);

		switch (ch1)
		{
		case'1':
			input(code, name, score);		//���ú����������ò���
			break;
		case'2':
			output(code, name, score);
			break;
		case'3':
			insert(code, name, score);
			break;
		case'4':
			delete(code, name, score);
			break;
		case'5':
			alter(code, name, score);
			break;
		case'6':
			search(code, name, score);
			break;
		case'7':
			rank(code, name, score);
			break;
		case'8':
			break;
		case'9':
			break;
		case'0':
			break;
		default:
			printf("���޸ù��ܣ����������룡\n");
			break;
		}
	} while (ch1 != '0');
}