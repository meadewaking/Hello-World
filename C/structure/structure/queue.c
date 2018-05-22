#include<stdio.h>
#include<malloc.h>

#define  MAX_QUEUE_SIZE   20
#define OK 1
#define ERROR 0
typedef int ElemType;

typedef  struct  queue
{
	ElemType   Queue_array[MAX_QUEUE_SIZE];		//����ӵĴ�С
	int   front;		//�������
	int  rear;			//�����β
}SqQueue;

SqQueue Init_CirQueue()		//��ʼ������
{
	SqQueue  Q;			//��������
	Q.front = Q.rear = 0;	//���׺Ͷ�βָ��ͬһλ��
	return Q ;		//��������ɳ�ʼ���Ķ���
}

int Insert_CirQueue(SqQueue * Q, int  e)
/*  ������Ԫ��e���뵽ѭ������Q�Ķ�β  */
{
	if ((Q->rear + 1) % MAX_QUEUE_SIZE == Q->front)
		return  ERROR;      /*  ���������ش����־    */
	Q->Queue_array[Q->rear] = e;   /*  Ԫ��e���  */
	Q->rear = (Q->rear + 1) % MAX_QUEUE_SIZE;
	/*  ��βָ����ǰ�ƶ�  */
	return OK;        /*  ��ӳɹ�    */
}

int Delete_CirQueue(SqQueue * Q, ElemType  *x)
/*  ��ѭ������Q�Ķ���Ԫ�س���  */
{
	if (Q->front == Q->rear)
		return ERROR;       /*  �ӿգ����ش����־    */
	*x = Q->Queue_array[Q->front];  /* ȡ����Ԫ�� */
	Q->front = (Q->front + 1) % MAX_QUEUE_SIZE;
	/*  ����ָ����ǰ�ƶ�  */
	return OK;
}

void main()
{
	SqQueue  Q;			//��������
	Q.front = Q.rear = 0;	//���׺Ͷ�βָ��ͬһλ��

	int e = 0;
	int length = 0;
	printf("����������Ԫ�ظ��������19����");
	scanf_s("%d", &length);
	for (int i = 0; i < length; i++)
	{
		scanf_s("%d", &e);
		
		if (Insert_CirQueue(&Q, e)==0)
		{
			break;
		}
	}
	while (Delete_CirQueue(&Q,&e))
	{
		printf("%d\t", e);
		
	}
	printf("\nͷָ��λ��%d", Q.front);
	printf("\nβָ��λ��%d", Q.rear);
}