#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
#define SUBJECT_COUNT 5				//定义科目数
#define SDUDENT_COUNT 30			//定义最大保存学生数目
int studentcount = 0;				//定义全局变量
									//传参格式
void input(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int i, j;
	char ch3 = '0';

	for (j = 0; studentcount <SDUDENT_COUNT; j++)
	{
		printf("请输入第%d个学生的学号：", j + 1);
		scanf(" %s", code[j]);
		printf("请输入第%d个学生的姓名：", j + 1);
		scanf(" %s", name[j]);
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("请输入%d科成绩：", i + 1);
			scanf(" %f", &score[j][i]);
		}
		studentcount++;
		printf("是否继续输入？ Y/N：");
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
	printf("学号\t");
	printf("姓名\t");
	printf("数学\t");
	printf("语文\t");
	printf("英语\t");
	printf("物理\t");
	printf("化学\t");
	printf("总分\t");
	printf("平均分\n");
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
	printf("排序完成！\n\n");
}
void insert(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("请输入你想要插入的位置：");
	scanf(" %d", &index);
	for (i = studentcount; i > index - 2; i--)
	{
		strcpy(code[i + 1], code[i]);
		strcpy(name[i + 1], name[i]);
		memcpy(score[i + 1], score[i], sizeof(score[i + 1]));
	}
	studentcount++;
	printf("请输入第%d个学生的学号：", index);
	scanf(" %s", code[index - 1]);
	printf("请输入第%d个学生的姓名：", index);
	scanf(" %s", name[index - 1]);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("请输入%d科成绩：", i + 1);
		scanf(" %f", &score[index - 1][i]);
	}
}
void delete(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("请输入你想要删除的位置：");
	scanf(" %d", &index);
	for (i = index - 1; i < studentcount; i++)
	{
		strcpy(code[i], code[i + 1]);
		strcpy(name[i], name[i + 1]);
		memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
	}
	studentcount--;
	printf("删除完成！\n\n");
}
void alter(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	printf("请输入你想要修改的位置：");
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
	printf("请输入要修改的学生的学号：");
	scanf(" %s", code[index - 1]);
	printf("请输入要修改的学生的姓名：");
	scanf(" %s", name[index - 1]);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("请输入%d科成绩：", i + 1);
		scanf(" %f", &score[index - 1][i]);
	}
	printf("修改完成！\n\n");
}
void search(char code[][10], char name[][20], float score[][SUBJECT_COUNT])
{
	int index, i;
	float sum = 0.0;
	printf("请输入你要查找的学生的位数：");
	scanf(" %d", &index);
	printf("学号\t");
	printf("姓名\t");
	printf("数学\t");
	printf("语文\t");
	printf("英语\t");
	printf("物理\t");
	printf("化学\t");
	printf("总分\t");
	printf("平均分\n");
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
	printf("程序设计及应用课程项目：学生成绩管理系统\n");
	printf("王子铭版权所有\n\n");
	do
	{
		printf("1.输入2.输出3.插入4.删除5.修改6.查找7.排序8.保存9.加载0.退出\n");
		printf("请输入0~9：");
		scanf(" %c", &ch1);

		switch (ch1)
		{
		case'1':
			input(code, name, score);		//引用函数，并调用参数
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
			printf("暂无该功能，请重新输入！\n");
			break;
		}
	} while (ch1 != '0');
}