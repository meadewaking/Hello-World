#include<stdio.h>
void main()
{
	while (1)
	{


		int number;
		printf("请输入一个整数；");
		scanf_s("%d", &number);

		if (number > 100 || number < 0)
			printf("无效\n");
		else if (number >= 90)
			printf("优秀\n");
		else if (number >= 80)
			printf("良好\n");
		else if (number >= 70)
			printf("中等\n");
		else if (number >= 60)
			printf("及格\n");
		else if (number <= 60)
			printf("挂\n");
		else if (number > 100 || number < 0)
			printf("无效\n");

	}

}       