#include<stdio.h>
void main()
{
	double num = 0.0, n = 1.0;
	int i = 1;
	while (i<=100000)
	{
		if (i % 2 == 0)
		{
			num = num + (-1 / n);
		}
		else
		{
			num = num + (1 / n);
		}
			i = i++;
			n = n + 1;
			printf("%lf\t\n", num);
	}
}