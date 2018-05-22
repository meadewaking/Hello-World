#include<stdio.h>
#include<math.h>
#include<stdlib.h>
void main() {
	/*			蒙特卡洛法
	double r = 1.00, pi = 0.00;
	double x = 0.00, y = 0.00;
	double hits = 0, points = 1e+9;
	srand(time(NULL));
	for (int i = 0; i < points; i++)
	{
		x = (double)rand() / RAND_MAX;
		y = (double)rand() / RAND_MAX;
		if (sqrt(x*x + y*y) <= 1)
		{
			hits++;
		}
	}
	pi = (hits / points) * 4;
	printf("%lf\n", pi);
	*/
	//泰勒展开
	double k = 1.00, j = 0.00, sum = 1.00, pi = 0.00;
	for (int i = 1; i < 1e+9; i++)
	{
		if (i%2 == 0)
		{
			j = 1;
		}
		else
		{
			j = -1;
		}
		k = k + 2;
		sum += j*(1 / k);
	}
	pi = sum * 4;
	printf("%1.20lf\n", pi);
}