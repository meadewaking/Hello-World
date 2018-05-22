#include<stdio.h>
void main()
{
	int score = 0;	//声明成绩变量score

	printf("请输入成绩：");		//输入成绩提示
	scanf_s("%d", &score);		//输入成绩

	if (score<60)		//分数判定
	{
		printf("需要补考\n");		//结果输出
	}
}