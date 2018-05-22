#include<stdio.h>
#define MAX_STACK 100
#define MAX_SIZE 100
typedef  struct  RecType		//节点结构体
{
	int key;          /* 关键字码  */
}RecType;
typedef  struct Sqlist		//序列结构体
{
	RecType  R[MAX_SIZE];	//定义数据节点
	int length;			//序列长度
}Sqlist;

/*void  quick_Sort(Sqlist  *L, int low, int high)		//快速排序
{
	int k, stack[MAX_STACK], top = 0, i = 0;
	do {
		while (low<high)		//当low低于high时继续单次quicksort
		{
			k = quick_one_pass(L, low, high);		//k记录每次qs的low值
			top++;
			stack[top] = high;				//整个序列的high进栈
			top++;
			stack[top] = k + 1;				//割裂后的第二个序列的low进栈
			//  第二个子序列的上,下界分别入栈  
			high = k - 1;			//此时整个序列的high变成第一序列的high
		}
		if (top > 0)
		{
			low = stack[top--];			//弹栈
			high = stack[top--];		//弹栈
			printf("low:%d  high:%d  top:%d\n", low, high,top);
		}
		i++;
	} while (top > 0 && low<high);			//栈为空的时候 
}*/
void  quick_Sort(Sqlist  *L, int low, int high)
{
	int k;
	if (low<high)
	{
		k = quick_one_pass(L, low, high);
		quick_Sort(L, low, k - 1);
		quick_Sort(L, k + 1, high);
	}     /*   序列分为两部分后分别对每个子序列排序   */
}

int  quick_one_pass(Sqlist  *L, int low, int high)		//一次快速排序
{
	int i = low, j = high;			//声明变量i和j
	L->R[0] = L->R[i];       /*   R[0]作为临时单元和哨兵  */
	do
	{
		while ((L->R[0].key <= L->R[j].key) && (j > i))		//将哨兵从后开始比较,并将high递减
		{
			j--;
		}
			
		if (j>i)			//如果high>low则将high赋给low,并将low升高
		{
			L->R[i] = L->R[j];
			i++;
		}
		while ((L->R[i].key <= L->R[0].key) && (j > i))		//将升高后的low和哨兵比较,并将low递增
		{
			i++;
		}
			
		if (j>i)			//如果依然high>low则将low赋给high,并将high降低
		{ 
			L->R[j] = L->R[i];
			j--;
		}
	} while (i != j);    /*   i=j时退出扫描  */
	L->R[i] = L->R[0];		//将哨兵的值赋给low
	return(i);				//返回low值
}
void main()
{
	Sqlist L;
	L.length = 0;
	srand((unsigned)time(NULL));		//生成随机值
	for (int i = 1; i < 21; i++)		//空出第0位置用作哨兵
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