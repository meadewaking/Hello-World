#include<stdio.h>
#include<malloc.h>
#include<string.h>
#pragma warning(disable:4996)
int count = 0;			//ȫ�ֱ��������Ա����¼��
typedef struct Node {	//����ڵ�
	struct data			//���ݽڵ�
	{
		int id;
		char * name;
		char * sex;
		char * birth;
		char * address;
		char * number;
	}data;
	struct Node * next;		//βָ��
}Node, *LinkList;
typedef struct tdata			//��ʱ���ݽڵ�
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
	char * temp;			//�ַ�ָ��ת������
	temp = (char *)malloc(strlen(buffer) + 1);
	strcpy(temp, buffer);
	return temp;
}
Node * inputsingle() 
{
	Node * node;
	node = (Node *)malloc(sizeof(Node));		//Ϊnode�����ڴ�
	int t;
	printf("�������ţ�\n");
	scanf("%d", &t);
	node->data.id = t;
	getchar();
	printf("������������\n");
	node->data.name = inputstring();
	printf("�������Ա�\n");
	node->data.sex = inputstring();
	printf("���������գ�\n");
	node->data.birth = inputstring();
	printf("�������ַ��\n");
	node->data.address = inputstring();
	printf("������绰���룺\n");
	node->data.number = inputstring();

	return node;
}
void create(LinkList L) {			//β�巨����������
	Node * r, *s;		//sָ��Ϊnode�����ڴ�
	int flag = 1;		//���ý�����ʶ
	r = L;				//rָ��ָ��ǰ��β
	int c = 1;
	while (flag)
	{
		
		if (c != 0)
		{
			s = inputsingle();
			r->next = s;							//ָ����һnode
			r = s;									//ָ�ص�ǰ��λ
			count++;
			printf("�Ƿ���������˳��밴0�������밴�������ּ���\n");
			scanf("%d", &c);
		}
		else
		{
			flag = 0;			//��������
			r->next = NULL;		//βָ��ָ���
		}
	}
}
void output(LinkList L) {
	Node * r;
	r = L->next;		//����ʼ��βָ�룬ָ���һ��Node����ʼ����
	printf("���\t����\t�Ա�\t����\t��ַ\t�绰����\n");
	while (r!=NULL)
	{
		printf("%d\t%s\t%s\t%s\t%s\t%s\n",r->data.id, r->data.name, r->data.sex, r->data.birth, r->data.address, r->data.number);	
		r = r->next;	//ָ�ر���ѭ�����
	}
	
}
void insert(LinkList L)
{
	int i = 0;
	printf("������Ҫ�����λ�ã�");
	scanf_s("%d", &i);
	if (i <= 0)
	{
		printf("����λ�÷Ƿ�\n");
	}
	int k = 0;					//ѭ������
	Node *pre = L, *s;			
	while (pre != NULL&&k < i - 1)		//���Ҳ����λ��
	{
		pre = pre->next;
		k++;
	}
	if (pre == NULL)		//βָ��Ϊ��֤���������λ�ó������ĳ���
	{
		printf("����λ�÷Ƿ�\n");
	}
	s = inputsingle();		//��������
	s->next = pre->next;		//��ԭλ�õ�Node��βָ���ƽ��������Node
	pre->next = s;			//��ԭλ�õ�Node��βָ��ָ������Node
	count++;
}
void deletesingle(LinkList L)
{
	int i = 0;
	printf("������Ҫɾ����λ�ã�");
	scanf_s("%d", &i);
	if (i <= 0)
	{
		printf("Ҫɾ����λ��������\n");
	}
	int k = 0;					//ѭ������
	Node *pre = L, *s;				//s��Ϊ��ʱ�нӣ�pre�нӲ�������
	while (pre != NULL&&k < i - 1)		//����ɾ����λ��
	{
		pre = pre->next;
		k++;
	}
	if (pre == NULL)		//βָ��Ϊ��֤����ɾ����λ�ó������ĳ���
	{
		printf("Ҫɾ����λ��������\n");
	}
	s = pre->next;			//�н�Ҫɾ���Ľڵ�
	pre->next = s->next;		//����һ�ڵ��βָ��ָ��Ҫɾ���Ľڵ����һ���ڵ�
	free(s);		//�ͷ�Ҫɾ���Ľڵ���ڴ�
}
void deleteall(LinkList L)
{
	Node *pre = L, *s;
	while (pre->next!= NULL)		//�ж��Ƿ�����һ���ڵ�
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
	//printf("������Ҫ���ҵ���ϵ�����֣�\n");
	scanf("%s", tname);
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name,tname))
		{
			break;
		}
		pre = pre->next;
	}
	if (pre == NULL)		//βָ��Ϊ��
	{
		printf("ͨѶ¼��û��Ҫ���ҵ�����\n");
	}
	printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
}
void searchbynum(LinkList L)
{
	char tnum[50];
	Node *pre = L;
	printf("������Ҫ���ҵ���ϵ�˵ĺ��룺\n");
	scanf("%s", tnum);
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.number, tnum))
		{
			break;
		}
		pre = pre->next;
	}
	if (pre == NULL)		//βָ��Ϊ��
	{
		printf("ͨѶ¼��û��Ҫ���ҵ�����\n");
	}
	printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
}
void dimsearchA(LinkList L, char dim[])
{
	Node *pre = L;
	pre = pre->next;
	int i = 0;
	while (pre != NULL)		//����λ��
	{
		if ((strlen(dim)) > (strlen(pre->data.name)))		//�������������ݳ���Ŀ����������β���
		{
			pre = pre->next;
			continue;
		}
		if (strstr(pre->data.name, dim))		//ģ��ƥ�䣬string���е�kmp��װ
		{
			printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
			i++;
		}
		pre = pre->next;
	}
	if (i == 0)		//βָ��Ϊ��
	{
		printf("ͨѶ¼��û��Ҫ���ҵ�����\n");
	}
}
void dimsearchU(LinkList L, char dim[])
{
	Node *pre = L;
	pre = pre->next;
	int i = 0;
	while (pre != NULL)		//����λ��
	{
		if ((strlen(dim)) > (strlen(pre->data.number)))		//�������������ݳ���Ŀ����������β���
		{
			pre = pre->next;
			continue;
		}
		if (strstr(pre->data.number, dim))		//ģ��ƥ�䣬string���е�kmp��װ
		{
			printf("%d\t%s\t%s\t%s\t%s\t%s\n", pre->data.id, pre->data.name, pre->data.sex, pre->data.birth, pre->data.address, pre->data.number);
			i++;
		}
		pre = pre->next;
	}
	if (i == 0)		//βָ��Ϊ��
	{
		printf("ͨѶ¼��û��Ҫ���ҵ�����\n");
	}
}
void dimname(LinkList L)
{
	char dname[50];
	printf("������Ҫ���ҵ���ϵ�����֣�\n");
	scanf("%s", dname);
	dimsearchA(L, dname);
}
void dimnum(LinkList L)
{
	char dnum[50];
	printf("������Ҫ���ҵ���ϵ�˺��룺\n");
	scanf("%s", dnum);
	dimsearchU(L, dnum);
}
void altertable(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵı�ţ�");
			scanf("%d", &pre->data.id);
			printf("������Ҫ�޸ĵ�������");
			scanf("%s", pre->data.name);
			printf("������Ҫ�޸ĵ��Ա�");
			scanf("%s", pre->data.sex);
			printf("������Ҫ�޸ĵ����գ�");
			scanf("%s", pre->data.birth);
			printf("������Ҫ�޸ĵĵ�ַ��");
			scanf("%s", pre->data.address);
			printf("������Ҫ�޸ĵĵ绰�ţ�");
			scanf("%s", pre->data.number);
		}
		pre = pre->next;
	}
}
void altername(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵ�������");
			scanf("%s", pre->data.name);
		}
		pre = pre->next;
	}
}
void altersex(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵ��Ա�");
			scanf("%s", pre->data.sex);
		}
		pre = pre->next;
	}
}
void alterbirth(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵ����գ�");
			scanf("%s", pre->data.birth);
		}
		pre = pre->next;
	}
}
void alteraddress(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵĵ�ַ��");
			scanf("%s", pre->data.address);
		}
		pre = pre->next;
	}
}
void alternumber(LinkList L, char name[])
{
	Node *pre = L;
	pre = pre->next;
	while (pre != NULL)		//����λ��
	{
		if (!strcmp(pre->data.name, name))		//ģ��ƥ��
		{
			printf("������Ҫ�޸ĵĵ绰�ţ�");
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
	}     /*   ���з�Ϊ�����ֺ�ֱ��ÿ������������   */
}
int  quick_one_pass(quick * Q, int low, int high)		//һ�ο�������
{
	int i = low, j = high;			//��������i��j
	strcpy(Q->qdata[0].address, Q->qdata[i].address);       /*   R[0]��Ϊ��ʱ��Ԫ���ڱ�  */
	strcpy(Q->qdata[0].birth, Q->qdata[i].birth);
	strcpy(Q->qdata[0].name, Q->qdata[i].name);
	strcpy(Q->qdata[0].number, Q->qdata[i].number);
	strcpy(Q->qdata[0].sex, Q->qdata[i].sex);
	Q->qdata[0].id = Q->qdata[i].id;
	do
	{
		while ((strcmp(Q->qdata[0].name , Q->qdata[j].name) >= 0) && (j > i))		//���ڱ��Ӻ�ʼ�Ƚ�,����high�ݼ�
		{
			j--;
		}

		if (j>i)			//���high>low��high����low,����low����
		{
			strcpy(Q->qdata[i].address, Q->qdata[j].address);       /*   R[0]��Ϊ��ʱ��Ԫ���ڱ�  */
			strcpy(Q->qdata[i].birth, Q->qdata[j].birth);
			strcpy(Q->qdata[i].name, Q->qdata[j].name);
			strcpy(Q->qdata[i].number, Q->qdata[j].number);
			strcpy(Q->qdata[i].sex, Q->qdata[j].sex);
			Q->qdata[i].id = Q->qdata[j].id;
			i++;
		}
		while ((strcmp(Q->qdata[i].name , Q->qdata[0].name) >= 0) && (j > i))		//�����ߺ��low���ڱ��Ƚ�,����low����
		{
			i++;
		}

		if (j>i)			//�����Ȼhigh>low��low����high,����high����
		{
			strcpy(Q->qdata[j].address, Q->qdata[i].address);       /*   R[0]��Ϊ��ʱ��Ԫ���ڱ�  */
			strcpy(Q->qdata[j].birth, Q->qdata[i].birth);
			strcpy(Q->qdata[j].name, Q->qdata[i].name);
			strcpy(Q->qdata[j].number, Q->qdata[i].number);
			strcpy(Q->qdata[j].sex, Q->qdata[i].sex);
			Q->qdata[j].id = Q->qdata[i].id;
			j--;
		}
	} while (i != j);    /*   i=jʱ�˳�ɨ��  */
	strcpy(Q->qdata[i].address, Q->qdata[0].address);       /*   R[0]��Ϊ��ʱ��Ԫ���ڱ�  */
	strcpy(Q->qdata[i].birth, Q->qdata[0].birth);
	strcpy(Q->qdata[i].name, Q->qdata[0].name);
	strcpy(Q->qdata[i].number, Q->qdata[0].number);
	strcpy(Q->qdata[i].sex, Q->qdata[0].sex);
	Q->qdata[i].id = Q->qdata[0].id;		//���ڱ���ֵ����low
	return(i);				//����lowֵ
}
void save(LinkList L)
{
	Node * r;
	r = L->next;
	FILE*fp = fopen("D:\\DT.txt", "wb");
	if (fp == NULL)
	{
		printf("�������");
		return;
	}
	fprintf(fp, "%02d", count);			//�����¼���������99��
	while (r != NULL)
	{
		fwrite(&r->data.id, sizeof(r->data.id), 1, fp);		//��node��data���棬����next������
		fwrite(&(*(r->data.name)), sizeof(r->data.name), 1, fp);
		fwrite(&(*(r->data.sex)), sizeof(r->data.sex), 1, fp);
		fwrite(&(*(r->data.birth)), sizeof(r->data.birth), 1, fp);
		fwrite(&(*(r->data.address)), sizeof(r->data.address), 1, fp);
		fwrite(&(*(r->data.number)), sizeof(r->data.number), 1, fp);
		r = r->next;
	}
	fclose(fp);
	printf("����ɹ�\n");
}
void load(LinkList L)
{
	tdata temp;
	Node * node;
	Node * r, *s;		//sָ��Ϊnode�����ڴ�
	r = L;
	FILE*fp = fopen("D:\\DT.txt", "rb");
	if (fp == NULL)
	{
		printf("���ش���");
		return;
	}
	fscanf(fp, "%02d", &count);		//��ȡ��¼����
	fseek(fp, 2, SEEK_SET);			//���ļ�ָ���������λ
	for (int i = 0; i < count; i++)
	{
		node = (Node *)malloc(sizeof(Node));
		fread(&node->data.id, sizeof(node->data.id), 1, fp);			//Ϊdata�����ڴ�
		fread(&temp.name, sizeof(node->data.name), 1, fp);				//����ʱdata�ṹ���ȡ���ݣ���ת��ָ�븳��data
		node->data.name = tochar(temp.name);
		fread(&temp.sex, sizeof(node->data.sex), 1, fp);
		node->data.sex = tochar(temp.sex);
		fread(&temp.birth, sizeof(node->data.birth), 1, fp);
		node->data.birth = tochar(temp.birth);
		fread(&temp.address, sizeof(node->data.address), 1, fp);
		node->data.address = tochar(temp.address);
		fread(&temp.number, sizeof(node->data.number), 1, fp);
		node->data.number = tochar(temp.number);
		s = node;			//ת��nodeָ��
		r->next = s;		//����	
		r = s;
	}
	r->next = NULL;
	fclose(fp);
	printf("���سɹ�\n");
}
void main() {
	Node * r;
	quick Q;			//������ʱ�ṹ��
	LinkList L = (Node *)malloc(sizeof(Node));		//����������ڴ�
	L->next = NULL;					//��ʼֵ��Ϊ�ղ���ȡ���ֵ
	int c;		//��ѡ��
	int d;		//��ѡ��
	int k = 1;
	do
	{
		printf("\t\t\t\t\t\t��ӭʹ��ͨѶ¼����ϵͳ\n");
		printf("1.����ͨѶ¼\n");
		printf("2.��ʾͨѶ¼\n");
		printf("3.������ϵ��\n");
		printf("4.ɾ����ϵ��\n");
		printf("5.������ϵ��\n");
		printf("6.�޸���ϵ��\n");
		printf("7.��ϵ������\n");
		printf("8.����ͨѶ¼\n");
		printf("9.����ͨѶ¼\n");
		printf("0.�˳�\n");
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
			printf("1.ɾ����ϵ��\t");
			printf("2.ɾ��ͨѶ¼\n");
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
				printf("���޴˹���\n");
				break;
			}
			break;
		case 5:
			printf("1.����������\t");
			printf("2.���绰����\n");
			printf("3.������ģ������\t");
			printf("4.���绰ģ������\n");
			scanf("%d", &d);
			switch (d)
			{
			case 1:
				searchbyname(L);
				printf("������Ҫ���ҵ���ϵ�����֣�\n");
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
				printf("���޴˹���\n");
				break;
			}
			break;
		case 6:
			printf("1.������Ҫ�޸ĵ���ϵ�˵����֣�\n");
			char name[50];
			scanf("%s", name);
			dimsearchA(L, name);
			printf("������Ҫ�޸ĵ����ݣ�\n");
			printf("1.�޸�ȫ��\t");
			printf("2.�޸�����\n");
			printf("3.�޸��Ա�\t");
			printf("4.�޸�����\n");
			printf("5.�޸ĵ绰\t");
			printf("6.�޸ĵ�ַ\n");
			printf("0.�����ϼ��˵�\n");
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
				printf("���޴˹���\n");
				break;
			}
			break;
		case 7:
			r = L->next;		//����ʼ��βָ�룬ָ���һ��Node����ʼ����
			while (r != NULL)
			{
				strcpy(Q.qdata[k].address, r->data.address);
				strcpy(Q.qdata[k].birth, r->data.birth);
				Q.qdata[k].id = r->data.id;
				strcpy(Q.qdata[k].name, r->data.name);
				strcpy(Q.qdata[k].number, r->data.number);
				strcpy(Q.qdata[k].sex, r->data.sex);
				r = r->next;	//ָ�ر���ѭ�����
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
				r = r->next;	//ָ�ر���ѭ�����
				k++;
			}
			printf("����ɹ�\n");
			break;
		case 8:
			save(L);
			break;
		case 9:
			load(L);
			break;
		case 0:
			printf("ллʹ�ã�");
			break;
		default:
			printf("���޴˹���\n");
			break;
		}
	} while (c!=0);
	
}