#include<stdio.h>
void main()
{
	while (1)
	{
		int y;
		printf("��������ݣ�");
		scanf_s("%d", &y);

		if (y % 4 != 0)
		{
			printf("ƽ��\n");
		}
		else if (y % 100 != 0)
		{
			printf("����\n");
		}
		else if (y % 400 != 0)
		{
			printf("ƽ��\n");
		}
		else
		{
			printf("����\n");
		}
	}

}