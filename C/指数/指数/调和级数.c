#include<stdio.h>
#include<math.h>
void main()
{
	double sum = 0, i = 0;

	for (i = 0; i < 1e+4; i++)
	{
		sum += pow(0.5, i);
	}
	printf("%lf\n", sum,i);
}