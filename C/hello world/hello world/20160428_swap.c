#include<stdio.h>
void swap(int * pa, int * pb)		//���βθ�ֵʱϣ����ʵ�β����仯����Ҫָ��
{
	int temp;
	temp = *pa;
	*pa = *pb;
	*pb = temp;
}
int add(int a, int b)
{
	return a + b;
}
void main()
{
	int a = 1, b = 2;
	printf("%d+%d=%d\n", a, b, add(a, b));
	printf("%d,%d\n", a, b);
	swap(&a, &b);
	printf("%d,%d\n", a, b);
}