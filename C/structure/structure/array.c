#include<stdio.h>
#define MAX_SIZE 10
typedef int elemtype;
typedef struct
{
	int   row;     /*  ���±�  */
	int  col;        /*  ���±�  */
	elemtype value;      /*  Ԫ��ֵ  */
}Triple;		//��Ԫ��ڵ�
typedef struct
{
	int  rn;         /*   ����   */
	int  cn;         /*   ����   */
	int  tn;         /*    ��0Ԫ�ظ���   */
	Triple   data[MAX_SIZE];
}TMatrix;		//��Ԫ��
void main()
{
	int i = 0;
	int j = 0;
	int k = 0;		//��ʼ��ѭ������

	TMatrix tmatrix;
	tmatrix.rn = 10;
	tmatrix.cn = 10;
	tmatrix.tn = 5;			//��ʼ����Ԫ��

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
	tmatrix.data[4].value = 7;		//��ʼ���ڵ�

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
