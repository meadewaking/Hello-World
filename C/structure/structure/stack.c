#include<stdio.h>
#include<malloc.h>
#define STACK_SIZE 100		//ջ��ʼ����С
#define STACKINCREMENT 10	//�洢�ռ��������
#define ERROR 0;
#define OK 1;
typedef int ElemType;
typedef struct sqstack
{
	ElemType * bottom;
	ElemType * top;
	int stacksize;			//��ǰ����ռ䣬��Ԫ��Ϊ��λ
}SqStack;
int Init_Stack()		//��ʼ��ջ
{
	SqStack  S;
	S.bottom = (ElemType *)malloc(STACK_SIZE *sizeof(ElemType));
	if (!S.bottom)
		return  ERROR;
	S.top = S.bottom;    /*  ջ��ʱջ����ջ��ָ����ͬ  */
	S.stacksize = STACK_SIZE;		//��ʼ��ջ�Ĵ�С
	return OK;
}
int push(SqStack * S, ElemType * e)		//ѹջ��Ԫ�ؽ�ջ��
{
	if (S->top - S->bottom >= S->stacksize - 1)				//�ж�ջ�ռ�
	{
		S->bottom = (ElemType *)realloc(S,((S->stacksize + STACK_SIZE) *sizeof(ElemType)));   /*  ջ����׷�Ӵ洢�ռ�  */
		if (!S->bottom)					
			return  ERROR;
		S->top = S->bottom + S->stacksize;		//��ջ������
		S->stacksize += STACKINCREMENT;			//����ԭ��ջ����
	}
	*(S->top) = *e;			//��һ����ַ��ֵ������һ����ַ
	S->top++; /*  ջ��ָ���1��e��Ϊ�µ�ջ�� */
	return OK;
}
int pop(SqStack * S, ElemType * e)		//����Ԫ��
{
	if (S->top == S->bottom)			//�ж�ջ�Ƿ�Ϊ��
	{
		return ERROR;
	}
	S->top--;					//ջ����1
	*e = *(S->top);
	return OK;
}
void main()
{
	int t = 0;
	int k = 0;
	int d = 2;
	SqStack S;
	S.bottom = (ElemType *)malloc(STACK_SIZE *sizeof(ElemType));
	if (!S.bottom)
		return  ERROR;
	S.top = S.bottom;
	S.stacksize = STACK_SIZE;
	for (int i = 0; i < 1; i++)
	{
		scanf_s("%d",&t);
		while (t>0)
		{
			k = t % d;
			push(&S, &k);
			t = t / d;			//ģ��̳���������ı�����ÿһλ������Ԫ�ؽ���ѹջ
		}
		while (pop(&S, &k))
		{
			printf("%d", k);	//��ջ�����
		}
	}
	

}