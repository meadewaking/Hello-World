#include<stdio.h>
void main()
{
	int num1 = 0, num2 = 0;
	char op;
	printf("请输入两个数：");
	scanf_s("%d %d", &num1, &num2);
	printf("请输入运算符：");
	scanf_s(" %c", &op);
	switch (op)
	{
	case'+':
		printf("%d+%d=%d\n", num1, num2, num1 + num2);	
		break;
	case'-':
		printf("%d-%d=%d\n", num1, num2, num1 - num2);	
		break;
	case'*':
		printf("%d*%d=%d\n", num1, num2, num1 * num2);	
		break;
	case'/':
		printf("%d/%d=%d\n", num1, num2, num1 / num2);	
		break;
	case'%':
		printf("%d%%d=%d\n", num1, num2, num1 % num2);	
		break;
	default:
		printf("暂不支持该运算\n");
		break;
	}
}