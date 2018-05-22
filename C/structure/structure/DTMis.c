#include<stdio.h>
#include<malloc.h>
#include<string.h>
#pragma warning(disable:4996)
int count = 0;			//全局变量，用以保存记录数
typedef struct Node {	//链表节点
	struct data			//数据节点
	{
		int id;
		char * name;
		char * sex;
		char * birth;
		char * address;
		char * number;
	}data;
	struct Node * next;		//尾指针
}Node, *LinkList;
typedef struct tdata			//临时数据节点
{
	int id;
	char name[100];
	char sex[100];
	char birth[100];
	char address[100];
	char number[100];
}tdata;
typedef struct quick
{
	tdata qdata[100];

}quick;
char * inputstring(){
	char buffer[256];
	char * temp;
	gets(buffer);
	temp = (char *)malloc(strlen(buffer) + 1);	
	strcpy(temp, buffer);
	return temp;
}
char * tochar(char buffer[]) {
	char * temp;			//字符指针转换函数
	temp = (char *)malloc(strlen(buffer) + 1);
	strcpy(temp, buffer);
	return temp;
}
Node * inputsingle() 
{
	Node * node;
	node = (Node *)malloc(sizeof(Node));		//为node申请内存
	int t;
	printf("请输入编号：\n");
	scanf("%d", &t);
	node->data.id = t;
	getchar();
	printf("请输入姓名：\n");
	node->data.name = inputstring();
	printf("请输入性别：\n");
	node->data.sex = inputstring();
	printf("请输入生日：\n");
	node->data.birth = inputstring();
	printf("请输入地址：\n");
	node->data.address = inputstring();
	printf("请输入电话号码：\n");
	node->data.number = inputstring();

	return node;
}
void create(LinkList L) {			//尾插法建立单链表
	Node * r, *s;		//s指针为node开辟内存
	int flag = 1;		//设置结束标识
	r = L;				//r指针指向当前表尾
	int c = 1;
	while (flag)
	{
		
		if (c != 0)
		{
			s = inputsingle();
			r->next = s;							//指向下一node
			r = s;									//指回当前表位
			count++;
			printf("是否继续？（退出请按0，继续请按任意数字键）\n");
			scanf("%d", &c);
		}
		else
		{
			flag = 0;			//结束输入
			r->next = NULL;		//尾指针指向空
		}
	}
}
void output(LinkList L) {
	Node * r;
	r = L->next;		//将初始的尾指针，指向第一个Node，初始化链
	printf("编号\t姓名\t性别\t生日\t地址\t电话号码\n");
	while (r!=NULL)
	{
		printf("%d\t%s\t%s\t%s\t%s\t%s\n",r->data.id, r->data.name, r->data.sex, r->data.birth, r->data.address, r->data.number);	
		r = r->next;	//指回本身，循环输出
	}
	
}
void insert(LinkList L)
{
	int i = 0;
	printf("请输入要插入的位置：");
	scanf_s("%d", &i);
	if (i <= 0)
	{
		printf("插入位置非法\n");
	}
	int k = 0;					//循环变量
	Node *pre = L, *s;			
	while (pre != NULL&&k < i - 1)		//查找插入的位置
	{
		pre = pre->next;
		k++;
	}
	if (pre == NULL)		//尾指针为空证明，插入的位置超过链的长度
	{
		printf("插入位置非法\n");
	}
	s = inputsingle();		//插入数据
	s->next = pre->next;		//将原位置的Node的尾指针移交给插入的Node
	pre->next = s;			//将原位置的Node的尾指针指向插入的Node
	count++;
}
void deletesingle(LinkList L)
{
	int i = 0;
	printf("请输入要删除的位置：");
	scanf_s("%d", &i);
	if (i <= 0)
	{
		printf("要删除的位置无数据\n");
	}
	int k = 0;					//循环变量
	Node *pre = L, *s;				//s作为临时承接，pre承接操作的链
	while (pre != NULL&&k < i - 1)		//查找删除的位置
	{
		pre = pre->next;
		k++;
	}
	if (pre == NULL)		//尾指针为空证明，删除的位置超过链的长度
	{
		printf("要删除的位置无数据\n");
	}
	s = pre->next;			//承接要删除的节点
	pre->next = s->next;		//将上一节点的尾指针指向要删除的节点的下一个节点
	free(s);		//释放要删除的节点的内存
}
void deleteall(LinkList L)
{
	Node *pre = L, *s;
	while (pre->next!= NULL)		//判断是否还有下一个节点
	{
		s = pre->next;			
		pre->next = s->next;		
		free(s);
	}
}
void searchbyname(LinkList L)
{
	char tname[50];
	Node *pre = L;
	//printf("请输入要查找的联系人名字：\n");
	scanf("%s", tname);
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name,tname))
		{
			break;
		}
		pre = pre->next;
	}
	if (pre == NULL)		//尾指针为空
	{
		printf("通讯录中没有要查找的内容\n");
	}
	printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
}
void searchbynum(LinkList L)
{
	char tnum[50];
	Node *pre = L;
	printf("请输入要查找的联系人的号码：\n");
	scanf("%s", tnum);
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.number, tnum))
		{
			break;
		}
		pre = pre->next;
	}
	if (pre == NULL)		//尾指针为空
	{
		printf("通讯录中没有要查找的内容\n");
	}
	printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
}
void dimsearchA(LinkList L, char dim[])
{
	Node *pre = L;
	pre = pre->next;
	int i = 0;
	while (pre != NULL)		//查找位置
	{
		if ((strlen(dim)) > (strlen(pre->data.name)))		//如果，输入的内容长于目标则结束本次查找
		{
			pre = pre->next;
			continue;
		}
		if (strstr(pre->data.name, dim))		//模糊匹配，string库中的kmp封装
		{
			printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
			i++;
		}
		pre = pre->next;
	}
	if (i == 0)		//尾指针为空
	{
		printf("通讯录中没有要查找的内容\n");
	}
}
void dimsearchU(LinkList L, char dim[])
{
	Node *pre = L;
	pre = pre->next;
	int i = 0;
	while (pre != NULL)		//查找位置
	{
		if ((strlen(dim)) > (strlen(pre->data.number)))		//如果，输入的内容长于目标则结束本次查找
		{
			pre = pre->next;
			continue;
		}
		if (strstr(pre->data.number, dim))		//模糊匹配，string库中的kmp封装
		{
			printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
			i++;
		}
		pre = pre->next;
	}
	if (i == 0)		//尾指针为空
	{
		printf("通讯录中没有要查找的内容\n");
	}
}
void dimname(LinkList L)
{
	char dname[50];
	printf("请输入要查找的联系人名字：\n");
	scanf("%s", dname);
	dimsearchA(L, dname);
}
void dimnum(LinkList L)
{
	char dnum[50];
	printf("请输入要查找的联系人号码：\n");
	scanf("%s", dnum);
	dimsearchU(L, dnum);
}
void altertable(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的编号：");
			scanf("%d", &pre->data.id);
			printf("请输入要修改的姓名：");
			scanf("%s", pre->data.name);
			printf("请输入要修改的性别：");
			scanf("%s", pre->data.sex);
			printf("请输入要修改的生日：");
			scanf("%s", pre->data.birth);
			printf("请输入要修改的地址：");
			scanf("%s", pre->data.address);
			printf("请输入要修改的电话号：");
			scanf("%s", pre->data.number);
		}
		pre = pre->next;
	}
}
void altername(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的姓名：");
			scanf("%s", pre->data.name);
		}
		pre = pre->next;
	}
}
void altersex(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的性别：");
			scanf("%s", pre->data.sex);
		}
		pre = pre->next;
	}
}
void alterbirth(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的生日：");
			scanf("%s", pre->data.birth);
		}
		pre = pre->next;
	}
}
void alteraddress(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的地址：");
			scanf("%s", pre->data.address);
		}
		pre = pre->next;
	}
}
void alternumber(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//查找位置
	{
		if (!strcmp(pre->data.name, name))		//模糊匹配
		{
			printf("请输入要修改的电话号：");
			scanf("%s", pre->data.number);
		}
		pre = pre->next;
	}
}
void quick_Sort(quick * Q, int low, int high)
{
	int k;
	if (low<high)
	{
		k = quick_one_pass(Q, low, high);
		quick_Sort(Q, low, k - 1);
		quick_Sort(Q, k + 1, high);
	}     /*   序列分为两部分后分别对每个子序列排序   */
}
int  quick_one_pass(quick * Q, int low, int high)		//一次快速排序
{
	int i = low, j = high;			//声明变量i和j
	strcpy(Q->qdata[0].address, Q->qdata[i].address);       /*   R[0]作为临时单元和哨兵  */
	strcpy(Q->qdata[0].birth, Q->qdata[i].birth);
	strcpy(Q->qdata[0].name, Q->qdata[i].name);
	strcpy(Q->qdata[0].number, Q->qdata[i].number);
	strcpy(Q->qdata[0].sex, Q->qdata[i].sex);
	Q->qdata[0].id = Q->qdata[i].id;
	do
	{
		while ((strcmp(Q->qdata[0].name , Q->qdata[j].name) >= 0) && (j > i))		//将哨兵从后开始比较,并将high递减
		{
			j--;
		}

		if (j>i)			//如果high>low则将high赋给low,并将low升高
		{
			strcpy(Q->qdata[i].address, Q->qdata[j].address);       /*   R[0]作为临时单元和哨兵  */
			strcpy(Q->qdata[i].birth, Q->qdata[j].birth);
			strcpy(Q->qdata[i].name, Q->qdata[j].name);
			strcpy(Q->qdata[i].number, Q->qdata[j].number);
			strcpy(Q->qdata[i].sex, Q->qdata[j].sex);
			Q->qdata[i].id = Q->qdata[j].id;
			i++;
		}
		while ((strcmp(Q->qdata[i].name , Q->qdata[0].name) >= 0) && (j > i))		//将升高后的low和哨兵比较,并将low递增
		{
			i++;
		}

		if (j>i)			//如果依然high>low则将low赋给high,并将high降低
		{
			strcpy(Q->qdata[j].address, Q->qdata[i].address);       /*   R[0]作为临时单元和哨兵  */
			strcpy(Q->qdata[j].birth, Q->qdata[i].birth);
			strcpy(Q->qdata[j].name, Q->qdata[i].name);
			strcpy(Q->qdata[j].number, Q->qdata[i].number);
			strcpy(Q->qdata[j].sex, Q->qdata[i].sex);
			Q->qdata[j].id = Q->qdata[i].id;
			j--;
		}
	} while (i != j);    /*   i=j时退出扫描  */
	strcpy(Q->qdata[i].address, Q->qdata[0].address);       /*   R[0]作为临时单元和哨兵  */
	strcpy(Q->qdata[i].birth, Q->qdata[0].birth);
	strcpy(Q->qdata[i].name, Q->qdata[0].name);
	strcpy(Q->qdata[i].number, Q->qdata[0].number);
	strcpy(Q->qdata[i].sex, Q->qdata[0].sex);
	Q->qdata[i].id = Q->qdata[0].id;		//将哨兵的值赋给low
	return(i);				//返回low值
}
void save(LinkList L)
{
	Node * r;
	r = L->next;
	FILE*fp = fopen("D:\\DT.txt", "wb");
	if (fp == NULL)
	{
		printf("保存错误");
		return;
	}
	fprintf(fp, "%02d", count);			//保存记录条数，最多99条
	while (r != NULL)
	{
		fwrite(&r->data.id, sizeof(r->data.id), 1, fp);		//将node中data保存，保存next无意义
		fwrite(&(*(r->data.name)), sizeof(r->data.name), 1, fp);
		fwrite(&(*(r->data.sex)), sizeof(r->data.sex), 1, fp);
		fwrite(&(*(r->data.birth)), sizeof(r->data.birth), 1, fp);
		fwrite(&(*(r->data.address)), sizeof(r->data.address), 1, fp);
		fwrite(&(*(r->data.number)), sizeof(r->data.number), 1, fp);
		r = r->next;
	}
	fclose(fp);
	printf("保存成功\n");
}
void load(LinkList L)
{
	tdata temp;
	Node * node;
	Node * r, *s;		//s指针为node开辟内存
	r = L;
	FILE*fp = fopen("D:\\DT.txt", "rb");
	if (fp == NULL)
	{
		printf("加载错误");
		return;
	}
	fscanf(fp, "%02d", &count);		//读取记录条数
	fseek(fp, 2, SEEK_SET);			//将文件指针向后移两位
	for (int i = 0; i < count; i++)
	{
		node = (Node *)malloc(sizeof(Node));
		fread(&node->data.id, sizeof(node->data.id), 1, fp);			//为data申请内存
		fread(&temp.name, sizeof(node->data.name), 1, fp);				//用临时data结构体读取数据，并转成指针赋给data
		node->data.name = tochar(temp.name);
		fread(&temp.sex, sizeof(node->data.sex), 1, fp);
		node->data.sex = tochar(temp.sex);
		fread(&temp.birth, sizeof(node->data.birth), 1, fp);
		node->data.birth = tochar(temp.birth);
		fread(&temp.address, sizeof(node->data.address), 1, fp);
		node->data.address = tochar(temp.address);
		fread(&temp.number, sizeof(node->data.number), 1, fp);
		node->data.number = tochar(temp.number);
		s = node;			//转交node指针
		r->next = s;		//成链	
		r = s;
	}
	r->next = NULL;
	fclose(fp);
	printf("加载成功\n");
}
void main() {
	Node * r;
	quick Q;			//排序临时结构体
	LinkList L = (Node *)malloc(sizeof(Node));		//整个链表的内存
	L->next = NULL;					//初始值设为空不能取随机值
	int c;		//主选择
	int d;		//次选择
	int k = 1;
	do
	{
		printf("\t\t\t\t\t\t欢迎使用通讯录管理系统\n");
		printf("1.创建通讯录\n");
		printf("2.显示通讯录\n");
		printf("3.插入联系人\n");
		printf("4.删除联系人\n");
		printf("5.查找联系人\n");
		printf("6.修改联系人\n");
		printf("7.联系人排序\n");
		printf("8.保存通讯录\n");
		printf("9.加载通讯录\n");
		printf("0.退出\n");
		scanf("%d", &c);
		switch (c)
		{
		case 1 :
			create(L);
			break;
		case 2 :
			output(L);
			break;
		case 3:
			insert(L);
			break;
		case 4:
			printf("1.删除联系人\t");
			printf("2.删除通讯录\n");
			scanf("%d", &d);
			switch (d)
			{
			case 1:
				deletesingle(L);
				break;
			case 2:
				deleteall(L);
				break;
			default:
				printf("暂无此功能\n");
				break;
			}
			break;
		case 5:
			printf("1.按姓名查找\t");
			printf("2.按电话查找\n");
			printf("3.按姓名模糊查找\t");
			printf("4.按电话模糊查找\n");
			scanf("%d", &d);
			switch (d)
			{
			case 1:
				searchbyname(L);
				printf("请输入要查找的联系人名字：\n");
				break;
			case 2:
				searchbynum(L);
				break;
			case 3:
				dimname(L);
				break;
			case 4:
				dimnum(L);
				break;
			default:
				printf("暂无此功能\n");
				break;
			}
			break;
		case 6:
			printf("1.请输入要修改的联系人的名字：\n");
			char name[50];
			scanf("%s", name);
			dimsearchA(L, name);
			printf("请输入要修改的内容：\n");
			printf("1.修改全部\t");
			printf("2.修改姓名\n");
			printf("3.修改性别\t");
			printf("4.修改生日\n");
			printf("5.修改电话\t");
			printf("6.修改地址\n");
			printf("0.返回上级菜单\n");
			scanf("%d", &d);
			switch (d)
			{
			case 1:
				altertable(L,name);
				break;
			case 2:
				altername(L, name);
				break;
			case 3:
				altersex(L, name);
				break;
			case 4:
				alterbirth(L, name);
				break;
			case 5:
				alternumber(L, name);
				break;
			case 6:
				alteraddress(L, name);
				break;
			case 0:
				break;
			default:
				printf("暂无此功能\n");
				break;
			}
			break;
		case 7:
			r = L->next;		//将初始的尾指针，指向第一个Node，初始化链
			while (r != NULL)
			{
				strcpy(Q.qdata[k].address, r->data.address);
				strcpy(Q.qdata[k].birth, r->data.birth);
				Q.qdata[k].id = r->data.id;
				strcpy(Q.qdata[k].name, r->data.name);
				strcpy(Q.qdata[k].number, r->data.number);
				strcpy(Q.qdata[k].sex, r->data.sex);
				r = r->next;	//指回本身，循环输出
				k++;
			}
			quick_Sort(&Q,1,count);
			k = 1;
			r = L->next;
			while (r != NULL)
			{
				strcpy(r->data.address, Q.qdata[k].address);
				strcpy(r->data.birth, Q.qdata[k].birth);
				r->data.id = Q.qdata[k].id;
				strcpy(r->data.name, Q.qdata[k].name);
				strcpy(r->data.number, Q.qdata[k].number);
				strcpy(r->data.sex, Q.qdata[k].sex);
				r = r->next;	//指回本身，循环输出
				k++;
			}
			printf("排序成功\n");
			break;
		case 8:
			save(L);
			break;
		case 9:
			load(L);
			break;
		case 0:
			printf("谢谢使用！");
			break;
		default:
			printf("暂无此功能\n");
			break;
		}
	} while (c!=0);
	
}