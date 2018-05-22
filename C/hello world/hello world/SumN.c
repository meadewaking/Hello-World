#include<stdio.h>

void main() {
	int i = 0, num = 0, sum = 0;
	
	printf("please input number£º");
	scanf_s("%d", &num);

	while (num > 100)
	{
		printf("this is illegal number\n");
		printf("please input number£º");
		scanf_s("%d", &num);
	}
	for ( i = 0; i <= num; i++)
	{
		sum += i;
	}
	printf("the sum is %d\n", sum);
}