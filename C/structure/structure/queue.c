#include<stdio.h>
#include<malloc.h>

#define  MAX_QUEUE_SIZE   20
#define OK 1
#define ERROR 0
typedef int ElemType;

typedef  struct  queue
{
	ElemType   Queue_array[MAX_QUEUE_SIZE];		//定义队的大小
	int   front;		//定义队首
	int  rear;			//定义队尾
}SqQueue;

SqQueue Init_CirQueue()		//初始化队列
{
	SqQueue  Q;			//声明队列
	Q.front = Q.rear = 0;	//队首和队尾指向同一位置
	return Q ;		//返回已完成初始化的队列
}

int Insert_CirQueue(SqQueue * Q, int  e)
/*  将数据元素e插入到循环队列Q的队尾  */
{
	if ((Q->rear + 1) % MAX_QUEUE_SIZE == Q->front)
		return  ERROR;      /*  队满，返回错误标志    */
	Q->Queue_array[Q->rear] = e;   /*  元素e入队  */
	Q->rear = (Q->rear + 1) % MAX_QUEUE_SIZE;
	/*  队尾指针向前移动  */
	return OK;        /*  入队成功    */
}

int Delete_CirQueue(SqQueue * Q, ElemType  *x)
/*  将循环队列Q的队首元素出队  */
{
	if (Q->front == Q->rear)
		return ERROR;       /*  队空，返回错误标志    */
	*x = Q->Queue_array[Q->front];  /* 取队首元素 */
	Q->front = (Q->front + 1) % MAX_QUEUE_SIZE;
	/*  队首指针向前移动  */
	return OK;
}

void main()
{
	SqQueue  Q;			//声明队列
	Q.front = Q.rear = 0;	//队首和队尾指向同一位置

	int e = 0;
	int length = 0;
	printf("请输入插入的元素个数（最多19个）");
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
	printf("\n头指针位置%d", Q.front);
	printf("\n尾指针位置%d", Q.rear);
}