#include<stdio.h>
#define MAX_SIZE 10
typedef int elemtype;
typedef struct
{
	int   row;     /*  行下标  */
	int  col;        /*  列下标  */
	elemtype value;      /*  元素值  */
}Triple;		//三元表节点
typedef struct
{
	int  rn;         /*   行数   */
	int  cn;         /*   列数   */
	int  tn;         /*    非0元素个数   */
	Triple   data[MAX_SIZE];
}TMatrix;		//三元表
void main()
{
	int i = 0;
	int j = 0;
	int k = 0;		//初始化循环变量

	TMatrix tmatrix;
	tmatrix.rn = 10;
	tmatrix.cn = 10;
	tmatrix.tn = 5;			//初始化三元表

	tmatrix.data[0].row = 1;
	tmatrix.data[0].col = 3;
	tmatrix.data[0].value = 6;

	tmatrix.data[1].row = 3;
	tmatrix.data[1].col = 5;
	tmatrix.data[1].value = 16;

	tmatrix.data[2].row = 4;
	tmatrix.data[2].col = 2;
	tmatrix.data[2].value = 12;

	tmatrix.data[3].row = 6;
	tmatrix.data[3].col = 2;
	tmatrix.data[3].value = 8;

	tmatrix.data[4].row = 7;
	tmatrix.data[4].col = 7;
	tmatrix.data[4].value = 7;		//初始化节点

	for (i = 0; i < tmatrix.rn; i++)
	{
		for (j = 0; j < tmatrix.cn; j++)
		{
			if ((i == tmatrix.data[k].row) && (j == tmatrix.data[k].col))
			{
				printf("%d ", tmatrix.data[k].value);
				k++;
			}
			else
			{
				printf("0 ");
			}

		}
		printf("\n");
	}
}
