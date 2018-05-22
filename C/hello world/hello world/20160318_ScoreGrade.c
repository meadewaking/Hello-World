#include<stdio.h>
void main()
{
	while (1)
	{


		int number = 0;		//声明分数变量
		printf("请输入分数；");		//输入提示
		scanf_s("%d", &number);		//输入分数

		if (number > 100 || number < 0)		//判定分数是否有效
			printf("无效\n");				//输出结果
		else if (number >= 90)				//判定分数区间
			printf("优秀\n");				//输出结果
		else if (number >= 80)				//判定分数区间
			printf("良好\n");				//输出结果
		else if (number >= 70)				//判定分数区间
			printf("中等\n");				//输出结果
		else if (number >= 60)				//判定分数区间
			printf("及格\n");				//输出结果
		else								//判定分数区间
		{
			printf("不及格\n");				//输出结果
		}

	}

}       