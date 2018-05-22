#include<stdio.h>
#include<malloc.h>
#define STACK_SIZE 100		//栈初始量大小
#define STACKINCREMENT 10	//存储空间分配增量
#define ERROR 0;
#define OK 1;
typedef int ElemType;
typedef struct sqstack
{
	ElemType * bottom;
	ElemType * top;
	int stacksize;			//当前分配空间，以元素为单位
}SqStack;
int Init_Stack()		//初始化栈
{
	SqStack  S;
	S.bottom = (ElemType *)malloc(STACK_SIZE *sizeof(ElemType));
	if (!S.bottom)
		return  ERROR;
	S.top = S.bottom;    /*  栈空时栈顶和栈底指针相同  */
	S.stacksize = STACK_SIZE;		//初始化栈的大小
	return OK;
}
int push(SqStack * S, ElemType * e)		//压栈（元素进栈）
{
	if (S->top - S->bottom >= S->stacksize - 1)				//判断栈空间
	{
		S->bottom = (ElemType *)realloc(S,((S->stacksize + STACK_SIZE) *sizeof(ElemType)));   /*  栈满，追加存储空间  */
		if (!S->bottom)					
			return  ERROR;
		S->top = S->bottom + S->stacksize;		//将栈顶上移
		S->stacksize += STACKINCREMENT;			//增加原定栈增量
	}
	*(S->top) = *e;			//将一个地址的值赋予另一个地址
	S->top++; /*  栈顶指针加1，e成为新的栈顶 */
	return OK;
}
int pop(SqStack * S, ElemType * e)		//弹出元素
{
	if (S->top == S->bottom)			//判断栈是否为空
	{
		return ERROR;
	}
	S->top--;					//栈顶减1
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
			t = t / d;			//模拟短除法对输入的变量的每一位二进制元素进行压栈
		}
		while (pop(&S, &k))
		{
			printf("%d", k);	//弹栈，输出
		}
	}
	

}