#include<stdio.h>
/*void main()
{
	int num[11], i = 0;
	while (i<10)
	{
		printf("�������%d������", i);
		scanf_s("%d", &num[i]);
		i = i++;
	}
	i = i--;
	while (i>=0)
	{
		printf("%d\n", num[i]);
		i = i--;
	}
}*/
/*void main()
{
	int x = 0, y = 0, z = 0;
	printf("������3������");
	scanf_s("%d%d%d", &x, &y, &z);
	if (x>y&&x>z)
	{
		printf("%d��������\n", x);
	}
	else if (y>x&&y>z)
	{
		printf("%d��������\n", y);
	}
	else
	{
		printf("%d��������\n", z);
	}
}*/
void main()
{
	int num[50], i = 0, max = 0;
	while (i<10)
	{
		scanf_s("%d", &num[i]);
		i = i++;
	}
	max = num[0];
	for ( i = 0; i < 10; i++)
	{
		if (num[i] > max)
		{
			max = num[i];
		}
	}
	printf("%d��������\n",max);
}