#include<stdio.h>
void main()
{
	int score = 0, i = 0;
	char op;
	do
	{
		do
		{
			if (i == 0)
			{
				printf("请输入成绩：");
			}
			else
			{
				printf("请重新输入成绩：");
			}
			scanf_s("%d", &score);
			if (score >= 0 && score <= 100)
			{
				break;
			}
			printf("成绩无效！\n");
			i = i++;
		} while (score < 0 || score>100);
		if (score < 60)
		{
			printf("不及格\n");
		}
		else
		{
			printf("及格\n");
		}
		printf("是否继续？（继续，请输入y。终止，请输入n。）");
		scanf_s(" %c", &op);
	} while (op=='y'||op=='Y');
}