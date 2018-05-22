#include<stdio.h>
void main()
{
	int a = 0, b = 0, i, s = 0;					//a表示底数 ，b为指数，i为每次乘方循环计数，s记录进位，
	int r[100] = {0};
	scanf_s("%d\n%d", &a, &b);				//分别输入底数和指数，底数不能大于每次取位长度
	r[0] = a;								//将第一位赋值为底数
	for ( i = 0; i < b; i++)
	{
		r[i]=
	}
	for ( i = b; i > 0; i--)
	{
		printf("%d", r[i]);
	}
}