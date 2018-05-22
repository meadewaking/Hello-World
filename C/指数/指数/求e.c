#include<stdio.h>
void main()
{
	double sum = 0, pro = 1;
	int i, k;
	for ( i = 1; i < 9; i++)
	{
		pro = 1;
		for ( k = i; k > 0; k--)
		{
			pro = pro*k;
		}
		sum += 1 / pro;
	}
	printf("%1.10lf\n", sum+1);
}