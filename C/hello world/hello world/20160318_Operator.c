#include<stdio.h>
void main()
{
	int num1 = 0, num2 = 0;
	char op;
	printf("��������������");
	scanf_s("%d %d", &num1, &num2);
	printf("�������������");
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
		printf("�ݲ�֧�ָ�����\n");
		break;
	}
}