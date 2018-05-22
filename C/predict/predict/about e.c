#include<stdio.h>
void main()
{
	long double sum = 1.0000, t = 1.0000;
	int j = 1, i = 1;
	while (i <= 100)
	{
		while (j<=i)
		{
			t = t*j;
			j = j++;
		}
		sum = sum + (1 / t);
		i = i++;
	}
	printf("%1.50lf\n", sum);
}