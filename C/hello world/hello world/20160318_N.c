#include<stdio.h>
void main()
{
	while (1)
	{
		long int num, i, sum;
		printf("������������׳��ۼӵ�����");
		scanf_s("%d", &num);
		i = 1;
		sum = 1;
		while (i <= num)
		{
			sum *= i;
			i = i++;
		}
		printf("�׳���%d\n", sum);
		i = 0;
		sum = 0;
		while (i <= num)
		{
			sum += i;
			i = i++;
		}
		printf("�ۼ���%d\n", sum);
	}
}