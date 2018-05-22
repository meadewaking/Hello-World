#include<stdio.h>
void main()
{
	double atom = 3.0e-23, kua = 0, sum = 0;
	printf("请输入夸脱数：");
	scanf_s("%lf", &kua);
	sum = kua * 950 / atom;
	printf("水分子数为%lf", sum);
}