#include<stdio.h>
void main()
{
	while (1)
	{


		int number;
		printf("������һ��������");
		scanf_s("%d", &number);

		if (number > 100 || number < 0)
			printf("��Ч\n");
		else if (number >= 90)
			printf("����\n");
		else if (number >= 80)
			printf("����\n");
		else if (number >= 70)
			printf("�е�\n");
		else if (number >= 60)
			printf("����\n");
		else if (number <= 60)
			printf("��\n");
		else if (number > 100 || number < 0)
			printf("��Ч\n");

	}

}       