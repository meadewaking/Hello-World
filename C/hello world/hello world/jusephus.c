#include<stdio.h>
#define size 50
void main()
{
	int m = 0, n = 0, i = 0;
	int array[size] = { 0 };
	int num = 0;
	
	printf("������������");
	scanf_s(" %d", &m);
	printf("��������������");
	scanf_s(" %d", &n);
	//��m��������
	for ( i = 0; i < m; i++)
	{
		array[i] = 1;	//��ӦΪ1����0û��
	}

	num = 0;
	for ( i = 0; m > 0; i++)
	{
		if (i==size)
		{
			i = 0;			//��Ȧ
		}
		if (1==array[i])
		{
			num++;
			if (num==n)
			{
				printf("%d\t", i + 1);	//�����λ��
				array[i] = 0;
				num = 0;
				m--;
			}		
		}
		
	}

}