#include<stdio.h>
void main()
{
	int x = 10, y = 90, n = 1;
	while (n <= 120)
	{
		if (n % 6==0)
			x = x * 2;
		if (n % 4==0)
			y = y * 2;
		if (n % 2==1)
			y = y - x;
		n = n + 1;
	}
	printf("y=%d", y);
}