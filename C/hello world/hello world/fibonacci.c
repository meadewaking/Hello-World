#include<stdio.h>
void main()
{
	

		int a[20];
		int i = 1, n = 0;
		a[0] = 0;
		a[1] = 1;
		a[2] = 1;
		while (i <= 20)
		{
			a[i + 2] = a[i + 1] + a[i];
			i++;
		}
		i = 1;
		while (i <= 20)
		{
			printf("%d\n", a[i]);
			i++;
		}
}