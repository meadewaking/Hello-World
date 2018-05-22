#include <stdio.h>
#include <locale.h>
#define M 10
int main()
{
	while (1)
	{
		unsigned a[M];					//以无符号整形存，每10为一进位，即数组每个元素最大9
		unsigned long k;				//k用来保存每次乘积
		int i, j, m, t, n = 1, r;		//n是数组长度，r是进位

		setlocale(LC_ALL, "chs");
		a[0] = 1;

		while (1)
		{
			printf("\n输入m t(用万进制方法求m的t次方):");
			fflush(stdout);
			rewind(stdin);
			if (2 == scanf_s("%d %d", &m, &t))
			{
				if
					(m > 1 && t > 1)
					break;
				else
					printf("要求m>1且t>1");
			}
		}

		for (j = 0; j < t; j++)			//循环乘方次
		{
			r = 0;						//每次循环将r清零
			for (i = 0; i < n; i++)		//循环数组长度次
			{
				k = a[i] * m + r;		//单次乘方内每一位的乘后大小，第一次循环由于进位为0,不影响结果
				a[i] = k % 10;			//储存单个
				r = k / 10;
			}
			while (r > 0)
			{
				a[n++] = r % 10;
				if (n >= M - 1)
				{
					printf("%d的%d次方超过万进制%d位,无法计算!\n", m, t, M);
					return 1;
				}
				r /= 10;
			}
		}

		printf("%d的%d次方=", m, t);
		for (i = n - 1; i >= 0; i--)
		{
			if (i == n - 1)
				printf("%d", a[i]);
			else
				printf("%d", a[i]);
		}
	}
	return 0;
}