#include<stdio.h>
void main() {
	char op;
	float x, y;
	printf("������x&y��\n");
	scanf_s("%f", &x);
	scanf_s("%f", &y);
	printf("������op��\n");
	getchar();
	scanf_s("%c", &op);
	switch (op)
	{
	case '+' :
		printf("x+y=%f\n", x + y);
		break;
	case '-':
		printf("x+y=%f\n", x - y);
		break;
	case '*':
		printf("x+y=%f\n", x * y);
		break;
	case '/':
		if (y!=0)
		{
			printf("x+y=%f\n", x + y);
			break;
		}
		else
		{
			printf("0��������ĸ\n");
			break;
		}
	default:
		break;
	}
}