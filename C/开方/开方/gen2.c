#include<stdio.h>
#define M 1000
void main()
{
	double a = 1.0, b = 0.0, n = 0.1;
	while (n > 0.000000000000001)
	{
		while (b < 2)
		{
			a = a + n;
			b = a*a;
		}
		a = a - n;
		n = n / 10;
		b = 0;
	}
	printf("%1.51lf\n",a);
}