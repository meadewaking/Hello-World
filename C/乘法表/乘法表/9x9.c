#include<stdio.h>
void main()
{
	int i = 1;
	int g = 1;
	for ( i = 1; i < 10; i++)
	{
		for (g = 1; g <= i; g++)
		{
			printf("%dx%d=%d\t", i, g, i*g);
		}
		printf("\n");
	}
}