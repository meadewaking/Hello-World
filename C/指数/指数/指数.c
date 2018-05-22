#include<stdio.h>
main()
{
	long double a, b;
	a = 1.00;
	b = 1.00;
	while (a <=1026.00) {
		b = b * 2.00;
		printf("%lf\t%lf\n",a,b);
		a = a + 1.00;
	}
}