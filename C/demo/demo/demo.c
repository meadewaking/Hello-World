#include <stdio.h>
#include <locale.h>
#define M 10
int main()
{
	while (1)
	{
		unsigned a[M];					//���޷������δ棬ÿ10Ϊһ��λ��������ÿ��Ԫ�����9
		unsigned long k;				//k��������ÿ�γ˻�
		int i, j, m, t, n = 1, r;		//n�����鳤�ȣ�r�ǽ�λ

		setlocale(LC_ALL, "chs");
		a[0] = 1;

		while (1)
		{
			printf("\n����m t(������Ʒ�����m��t�η�):");
			fflush(stdout);
			rewind(stdin);
			if (2 == scanf_s("%d %d", &m, &t))
			{
				if
					(m > 1 && t > 1)
					break;
				else
					printf("Ҫ��m>1��t>1");
			}
		}

		for (j = 0; j < t; j++)			//ѭ���˷���
		{
			r = 0;						//ÿ��ѭ����r����
			for (i = 0; i < n; i++)		//ѭ�����鳤�ȴ�
			{
				k = a[i] * m + r;		//���γ˷���ÿһλ�ĳ˺��С����һ��ѭ�����ڽ�λΪ0,��Ӱ����
				a[i] = k % 10;			//���浥��
				r = k / 10;
			}
			while (r > 0)
			{
				a[n++] = r % 10;
				if (n >= M - 1)
				{
					printf("%d��%d�η����������%dλ,�޷�����!\n", m, t, M);
					return 1;
				}
				r /= 10;
			}
		}

		printf("%d��%d�η�=", m, t);
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