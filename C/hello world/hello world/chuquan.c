#include<stdio.h>
void main()
{
	int n = 5, m = 0, i = 0, j = 0, step = 2, h = 0;
	int a[10], b[10];
	for ( i = 0; i < 10; i++)
	{
		a[i] = i+1;
	}
	i = 0;
	for ( j = 0; j < 5; j++)
	{
		for (h = m; 1 == 1;h++)
		{
			if (h == n)
			{
				h = 0;
			}
			if (a[h] != 0)
			{
				i++;
			}
			if (i==step)
			{
				b[j] = a[h];
				a[h] = 0;
				i = 0;
				break;
			}
		}
		m += step;
		if (m>n)
		{
			m = m%n;
		}
	}
	for ( i = 0; i < 5; i++)
	{
		printf("%d\n", b[i]);
	}
}