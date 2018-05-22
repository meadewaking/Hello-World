#include<stdio.h>
#include<string.h>
#include<malloc.h>
#include <string.h>
#include <winsock2.h>
#pragma comment(lib, "Ws2_32.lib")
#pragma warning(disable:4996)
#define SUBJECT_COUNT 5				//定义科目数
#define STUDENT_COUNT 30			//定义最大保存学生数目

typedef struct str_Student
{
	char * num;
	char * name;
	char * sex;
	float score[5];		//定义指针结构体变量,建立动态内存
}Student;
typedef struct str_Fstudent
{
	char num[20];
	char name[30];
	char sex[20];
	float score[5];		//定义临时字符串结构体变量,临时承接指针指向的内容
}Fstudent;
typedef struct str_Account
{
	char account[50];
	char password[50];
}Account;
//Student student;
int studentcount = 0;				//定义全局变量
									//传参格式
float sum(float array[], int length)
{
	int i;
	float sum;

	for (sum = 0, i = 0; i < length; i++)
	{
		sum += array[i];				//求和函数,主要用于排序,增加函数重用
	}
	return sum;
}
char * inputstring()					//输入字符串函数
{
	char buffer[256];
	char * temp;
	gets(buffer);						//用gets函数可以读到空格
	temp = (char *)malloc(strlen(buffer) + 1);		//为临时指针变量申请地址,+1预留终止符
	strcpy(temp, buffer);
	return temp;
}
char * inputstring2()
{
	char buffer[256];
	char * temp;
	getchar();					//读一个字符,防止gets读取回车
	gets(buffer);
	temp = (char *)malloc(strlen(buffer) + 1);
	strcpy(temp, buffer);
	return temp;
}
char * temp()			//在读取时将student结构体初始化
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
		scanf(" %s", buffer);						//用gets函数可以读到空格
		temp = (char *)malloc(strlen(buffer) + 1);		//为临时指针变量申请地址,+1预留终止符
		strcpy(temp, buffer);
		tempch = temp;
		if (strlen(tempch)>1)				//防止用户输入一个以上字符,strlen函数:读取字符串实长
		{
			printf("请输入一个字符！\n");
			continue;
		}
		sscanf(tempch, "%c", &ch);		//字符串写入
		free(tempch);
		return  ch;
	} while (1);
}
int num2index(char num[], Student * student[])
{
	int i, index;
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(num, student[i]->num))		//将学生的学号信息转化为学生位置并返回
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
		if (!strcmp(tnum, student[i]->num))		//判断学号是否重复
		{
			return 1;			//重复返回int值1
		}
	}
	return 0;		//不重复则返回int值1
}
void rankbyname(Student * student[])		//按姓名排序
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

				if (strcmp(student[i]->name, student[j]->name)>0)		//以交换法对姓名排序,找出每一次最大姓名ascii值
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
				max = j;									//选择法排序
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
void searchbyindex(Student * student[])		//按位置查找
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
void searchbynum(Student * student[])		//按学号查找
{
	int i, j;
	float sum = 0.0;
	char temp_num[20];
	printf("请输入你要查找的学生的学号：");
	scanf(" %s", temp_num);
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(temp_num, student[i]->num))
		{
			printf("学号\t");
			printf("姓名\t");
			printf("数学\t");
			printf("语文\t");
			printf("英语\t");
			printf("物理\t");
			printf("化学\t");
			printf("总分\t");
			printf("平均分\n");
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
		printf("你要找的学生不存在！\n");
	}
}
void searchbyname(Student * student[])		//按姓名查找
{
	int i, j, k = 0;
	float sum = 0.0;
	char temp_name[20];
	printf("请输入你要查找的学生的姓名：");
	scanf(" %s", temp_name);
	for (i = 0; i < studentcount; i++)
	{
		if (!strcmp(temp_name, student[i]->name))
		{
			if (k == 0)
			{
				printf("学号\t");
				printf("姓名\t");
				printf("数学\t");
				printf("语文\t");
				printf("英语\t");
				printf("物理\t");
				printf("化学\t");
				printf("总分\t");
				printf("平均分\n");
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
		printf("你要找的学生不存在！\n");
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
	addrSrv.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//服务器端的IP地址

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
	//printf("请输入你的意见：");
	scanf("%s", &sendBuf);
	printf("\n感谢你的使用！\n");
	send(socketClient, sendBuf, strlen(sendBuf) + 1, 0);
	closesocket(socketClient);
	WSACleanup();
	i++;
}
Student * inputsingle(int j, Student * student[])		//申请内存空间，完成输入，并返回该内存空间的指针
{
	int i;
	i = 0;
	int k = 0;
	char * tnum;			//临时指针用于传参,结构体直接传参引发未知原因错误
	Student * tstudent = (Student *)malloc(sizeof(Student));		//为结构体申请内存空间
	do
	{
		if (k != 0)
		{
			printf("学号已存在！请重新输入\n");
		}
		printf("请输入第%d个学生的学号：", j + 1);
		if (k == 0)							//防止gets函数读取回车
		{
			tnum = inputstring2();
		}
		else
		{
			tnum = inputstring();
		}
		k++;
	} while (studentcount != 0 && juge(tnum, student));		//运用&&运算断路,精简代码行数,提高运行效率
	tstudent->num = tnum;
	printf("请输入第%d个学生的姓名：", j + 1);
	tstudent->name = inputstring();
	printf("请输入第%d个学生的性别：", j + 1);
	tstudent->sex = inputstring();
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("请输入%d科成绩：", i + 1);
		scanf(" %f", &tstudent->score[i]);
	}
	return tstudent;			//返回结构体指针

}
void input(Student * student[])		//申请内存空间，完成输入，并返回该内存空间的指针
{
	int j;
	char ch3 = '0';
	studentcount = 0;
	for (j = 0; studentcount <STUDENT_COUNT; j++)		//用户可以输入最大限额内任意数额的结构体个数
	{
		student[j] = inputsingle(j, student);
		studentcount++;
		printf("是否继续输入？ Y/N：");
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
	printf("学号\t");
	printf("姓名\t");
	printf("性别\t");
	printf("数学\t");
	printf("语文\t");
	printf("英语\t");
	printf("物理\t");
	printf("化学\t");
	printf("总分\t");
	printf("平均分\n");
	for (j = 0; j < studentcount; j++)		//遍历显示
	{
		printf("%s\t", student[j]->num);
		printf("%s\t", student[j]->name);
		printf("%s\t", student[j]->sex);
		sum = 0;
		for (i = 0; i < SUBJECT_COUNT; i++)
		{
			printf("%.2f\t", student[j]->score[i]);
			sum += student[j]->score[i];		//计算总分
			sumcol[i] += student[j]->score[i];		//列统计
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
	printf("1.以姓名排序\t2.以总分排序\n");
	printf("请选择:");
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
		printf("请重新排序！\n");
		break;
	}

	printf("排序完成！\n\n");

}
void insert(Student * student[])
{
	int index, i = 0;
	do
	{
		printf("请输入1至%d之间的位置!\n", studentcount + 1);
		printf("请输入你想要插入的位置：");
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
	student[index - 1] = inputsingle(index - 1, student);		//将插入位置空出重用inputsingle函数
	studentcount++;
	/*
	i = 0;
	do
	{
	if (i>0)
	{
	printf("该学号已存在！请重新输入！\n");
	}
	printf("请输入第%d个学生的学号：", index);
	scanf(" %s", tempnum);
	i++;
	} while (juge(tempnum, studentcount, student));
	strcpy(student[index - 1]->num, tempnum);
	printf("请输入第%d个学生的姓名：", index);
	scanf(" %s", student[index - 1]->name);
	printf("请输入第%d个学生的性别：", index);
	scanf(" %s", student[index - 1]->sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
	printf("请输入%d科成绩：", i + 1);
	scanf(" %f", &student[index - 1]->score[i]);
	}
	*/
}
void delete(Student * student[])
{
	int index, i;
	printf("请输入你想要删除的位置：");
	scanf(" %d", &index);
	for (i = index - 1; i < studentcount; i++)
	{
		student[i] = student[i + 1];		//将所需删除的位置覆盖掉
											/*
											strcpy(code[i], code[i + 1]);
											strcpy(name[i], name[i + 1]);
											memcpy(score[i], score[i + 1], sizeof(score[i + 1]));
											*/
	}
	studentcount--;
	printf("删除完成！\n\n");
}
void alter(Student * student[])
{
	int index = 0, i;
	char num[20];
	printf("请输入你想要修改的学生的学号：");
	scanf(" %s", num);
	index = num2index(num, student);
	if (index == -1)
	{
		printf("该学号不存在!\n\n");
		return;
	}
	printf("请输入要修改的学生的学号：");
	scanf(" %s", student[index - 1]->num);
	printf("请输入要修改的学生的姓名：");
	scanf(" %s", student[index - 1]->name);
	printf("请输入要修改的学生的性别：");
	scanf(" %s", student[index - 1]->sex);
	for (i = 0; i < SUBJECT_COUNT; i++)
	{
		printf("请输入%d科成绩：", i + 1);
		scanf(" %f", &student[index - 1]->score[i]);
	}
	printf("修改完成！\n\n");
}
void search(Student * student[])
{
	char ch;
	printf("1.按位置查询\t2.按学号查询\t3.按姓名查找\n");
	printf("请输入你要查找的方式：");
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
		printf("请重新输入！\n");
		break;
	}
}
void save(Student * student[])
{
	Fstudent fstudent;		//定义临时字符串结构体
	FILE*fp = fopen("D:\\StudentMis2.0.txt", "wb");
	int i, j;
	if (fp == NULL)
	{
		printf("无法保存！\n");
		return;
	}
	fprintf(fp, "%02d", studentcount);
	for (i = 0; i < studentcount; i++)
	{
		strcpy(fstudent.num, student[i]->num);
		strcpy(fstudent.name, student[i]->name);
		strcpy(fstudent.sex, student[i]->sex);		//将指针值赋给临时结构体
		for (j = 0; j < SUBJECT_COUNT; j++)
		{
			fstudent.score[j] = student[i]->score[j];		//将临时结构体成块按二进制形式保存
		}
		fwrite(&fstudent, sizeof(Fstudent), 1, fp);
	}
	/*
	//float sum;
	int i, j;
	//float sumcol[SUBJECT_COUNT] = { 0 };
	/*
	fprintf(fp, "学号\t");
	fprintf(fp, "姓名\t");
	fprintf(fp, "性别\t");
	fprintf(fp, "数学\t");
	fprintf(fp, "语文\t");
	fprintf(fp, "英语\t");
	fprintf(fp, "物理\t");
	fprintf(fp, "化学\t");
	fprintf(fp, "总分\t");
	fprintf(fp, "平均分\n");
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
	printf("保存成功！\n");
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
		student[i]->num = temp();		//用temp函数初始student结构体
		student[i]->name = temp();
		student[i]->sex = temp();
		strcpy(student[i]->num, fstudent.num);
		strcpy(student[i]->name, fstudent.name);
		strcpy(student[i]->sex, fstudent.sex);	//将临时字符串的值赋给指针
		for (j = 0; j < SUBJECT_COUNT; j++)
		{
			student[i]->score[j] = fstudent.score[j];
		}

	}
	/*
	printf("是否继续输入？ Y/N：");
	fscanf(fp, " %c", &ch3);
	if (ch3 == 'N' || ch3 == 'n')
	{
	break;
	}
	*/
	fclose(fp);
	printf("加载完成！\n\n");
}
void mainTeacher()
{
	Sleep(1000);
	system("cls");
	char ch1;
	Student * student[STUDENT_COUNT];
	printf("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊程序设计及应用课程项目：学生成绩管理系统＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊\n\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t■王子铭版权所有\n\n");
	do
	{
		printf("\t\t\t1.输入2.输出3.插入4.删除5.修改6.查找7.排序8.保存9.加载0.退出\n");
		printf("请输入0~9：");
		ch1 = inputchar();
		switch (ch1)
		{
		case'1':
			input(student);		//引用函数，并调用参数
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
			printf("暂无该功能，请重新输入！\n");
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
	printf("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊程序设计及应用课程项目：学生成绩管理系统＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊\n\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t■王子铭版权所有\n\n");
	do
	{
		printf("\t\t\t1.输出\t\t2.查找\t\t3.排序\t\t4.加载\t\t0.退出\n");
		printf("请输入0~4：");
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
			printf("暂无该功能，请重新输入！\n");
			break;
		}
	} while (ch1 != '0');
}
void main()
{
	char account[50], password[50];			//设置临时账户密码数组
	Account Taccount[3];					//设置教师和学生结构体
	Account Saccount[3];
	FILE*fp = fopen("D:\\Taccount for SM.txt", "rb");
	int i, t = 0, s = 0, k = 0, ta, sa;
	for ( i = 0; i < STUDENT_COUNT; i++)		//读取教师的账号和密码
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
	for (i = 0; i < STUDENT_COUNT; i++)			//读取学生的账号和密码
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
		if (k != 0)			//遍历所有账号没有找到，证明账号不存在
		{
			printf("此账号不存在！请重新输入！\n");
		}
		printf("请输入账户：");
		scanf(" %s", account);
		for ( i = 0; i < STUDENT_COUNT; i++)
		{
			if (!strcmp(account,Taccount[i].account))	
			{
				t = 1;			//判断此账号存在，并为教师账号
				ta = i;			//表名为第i条账号
				break;
			}
		}
		for (i = 0; i < STUDENT_COUNT; i++)
		{
			if (!strcmp(account, Saccount[i].account))
			{
				s = 1;			//判断此账号存在，并为学生账号
				sa = i;			//表名为第i条账号
				break;
			}
		}
		k++;			
	} while (t==0&&s==0);		//判断账号不存在的情况下循坏运行
	k = 0;				//k作为存在验证
	do
	{
		if (k != 0)
		{
			printf("此密码不正确！请重新输入！\n");
		}
		printf("请输入密码：");
		scanf(" %s", password);
		if (t==1)		
		{
			if (!strcmp(password, Taccount[ta].password))	//比对输入的密码与找到的该条账号的密码
			{
				printf("\n你的身份是教师！\n");			
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
				printf("\n你的身份是学生！\n");
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