#include<stdio.h>
float add(float a,float b)
{
	float sum1;
	sum1 = a + b;
	return sum1;
}
void main()
{
	float a = 0, b = 0, sum = 0;
	printf("请输入要求和的数：");
	scanf_s(" %f", &a);
	scanf_s(" %f", &b);
	sum = add(a, b);
	printf("%.2f", sum);
}