#include<stdio.h>
#include<math.h>
void main()
{
	int cases = 0, w = 0, h = 0, p = 0, n = 0, i = 0, j = 0, s = 0, sum = 0;
	int a[10];
	printf("��������Ը�����");
	scanf_s("%d", &cases);
	for (j = 0; j < cases; j++)
	{
		do
		{
			printf("������N,P,W,H:");
			scanf_s("%d %d %d %d", &n, &p, &w, &h);
			printf("������ÿ��������");
			for (i = 0; i < n; i++)
			{
				scanf_s("%d", &a[i]);
			}

			if (n >= 1 && n <= 1000 && w >= 1 && w <= 1000 && h >= 1 && h <= 1000 && p >= 1 && p <= 1e+6)
			{
				break;
			}
			else
			{
				printf("���������Χ�����������룡");
			}
		} while (1);
		for (i = 0; i < n; i++)
		{
			sum += a[i];
		}
		s = sqrt((p*h*w) / sum);
		printf("�����õ��������Ϊ%d\n", s);
	}
}