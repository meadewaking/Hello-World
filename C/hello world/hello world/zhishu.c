#include<stdio.h>
#include<math.h>
void main()
{
	int num = 0, n = 2, flag = 1, i = 2,count=0;
	/*while (1)
	{
		do
		{
			printf("����������һ������3������");
			scanf_s("%d", &num);
			if (num>3)
			{
				break;
			}
			printf("����Υ�������������롣");
		} while (num<=3);
		while (n < num)
		{
			if (num%n == 0)
			{
				printf("����һ������\n");
				break;
			}
			n = n + 1;
		}
		if (n==num)
		{
			printf("����һ������\n");
		}
	}*/
	/*printf("��ӡС�ڴ���������������");
	scanf_s("%d", &num);
	while (i < num)
	{
		for (n = 2; n < i;n++)
		{
			if (i%n == 0)
			{
				flag = 0;
				break;
			}
			else
			{
				flag = 1;
			}
		}
		if (flag)
		{
			printf("%d\t", i);
			count++;
		}
		i++;
	}*/
	while (count < 300000)
	{
		for (n = 2; n < sqrt(i); n++)	//brilliant������
		{
			if (i%n == 0)
			{
				flag = 0;
				break;		//ʹ�ú��������¼���ѭ������
			}
			else
			{
				flag = 1;
			}
		}
		if (flag)
		{
			printf("%d\t", i);
			count++;
		}
		i++;
	}
}
	