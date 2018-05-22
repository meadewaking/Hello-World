#include<stdio.h>
#define MAX_STACK 100
#define MAX_SIZE 100
typedef  struct  RecType		//�ڵ�ṹ��
{
	int key;          /* �ؼ�����  */
}RecType;
typedef  struct Sqlist		//���нṹ��
{
	RecType  R[MAX_SIZE];	//�������ݽڵ�
	int length;			//���г���
}Sqlist;

/*void  quick_Sort(Sqlist  *L, int low, int high)		//��������
{
	int k, stack[MAX_STACK], top = 0, i = 0;
	do {
		while (low<high)		//��low����highʱ��������quicksort
		{
			k = quick_one_pass(L, low, high);		//k��¼ÿ��qs��lowֵ
			top++;
			stack[top] = high;				//�������е�high��ջ
			top++;
			stack[top] = k + 1;				//���Ѻ�ĵڶ������е�low��ջ
			//  �ڶ��������е���,�½�ֱ���ջ  
			high = k - 1;			//��ʱ�������е�high��ɵ�һ���е�high
		}
		if (top > 0)
		{
			low = stack[top--];			//��ջ
			high = stack[top--];		//��ջ
			printf("low:%d  high:%d  top:%d\n", low, high,top);
		}
		i++;
	} while (top > 0 && low<high);			//ջΪ�յ�ʱ�� 
}*/
void  quick_Sort(Sqlist  *L, int low, int high)
{
	int k;
	if (low<high)
	{
		k = quick_one_pass(L, low, high);
		quick_Sort(L, low, k - 1);
		quick_Sort(L, k + 1, high);
	}     /*   ���з�Ϊ�����ֺ�ֱ��ÿ������������   */
}

int  quick_one_pass(Sqlist  *L, int low, int high)		//һ�ο�������
{
	int i = low, j = high;			//��������i��j
	L->R[0] = L->R[i];       /*   R[0]��Ϊ��ʱ��Ԫ���ڱ�  */
	do
	{
		while ((L->R[0].key <= L->R[j].key) && (j > i))		//���ڱ��Ӻ�ʼ�Ƚ�,����high�ݼ�
		{
			j--;
		}
			
		if (j>i)			//���high>low��high����low,����low����
		{
			L->R[i] = L->R[j];
			i++;
		}
		while ((L->R[i].key <= L->R[0].key) && (j > i))		//�����ߺ��low���ڱ��Ƚ�,����low����
		{
			i++;
		}
			
		if (j>i)			//�����Ȼhigh>low��low����high,����high����
		{ 
			L->R[j] = L->R[i];
			j--;
		}
	} while (i != j);    /*   i=jʱ�˳�ɨ��  */
	L->R[i] = L->R[0];		//���ڱ���ֵ����low
	return(i);				//����lowֵ
}
void main()
{
	Sqlist L;
	L.length = 0;
	srand((unsigned)time(NULL));		//�������ֵ
	for (int i = 1; i < 21; i++)		//�ճ���0λ�������ڱ�
	{
		L.R[i].key = rand() % 1000;
		L.length++;
	}
	for (int k = 1; k < 21; k++)
	{
		printf("%d  ", L.R[k].key);
	}
	printf("\n");

	quick_Sort(&L, 1, L.length);
			
	for (int k = 1; k < 21; k++)
	{
		printf("%d  ", L.R[k].key);
	}
}