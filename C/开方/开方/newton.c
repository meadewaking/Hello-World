#include<stdio.h>
void main()
{
	int i;
	double x = 5.00;
	for ( i = 0; i < 1e+3; i++)
	{
		x = x - ((x*x - 2) / (2 * x));
	}
	printf("%1.51lf\n", x);
}