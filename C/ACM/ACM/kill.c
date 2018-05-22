#include<stdio.h>
void main()
{
	int k = 0;		//被杀人数
	int j = 0;
	int man[510];		//排成排等待被杀的人
	int killer = 0;		//随机产生的杀人序数，产生一个小于人数的奇数
	int survivor[110];	//幸存者

	for (int i = 0; i < 100; i++)
	{
		k = 0;
		for (int i1 = 0; i1 < 500; i1++)
		{
			man[i1 + 1] = i1 + 1;		//填装
		}

		for (int i2 = 0; i2 < (500 - 2); i2++)
		{

			srand((unsigned)time(NULL));		//生成随机奇数
			killer = (rand() % (500 - k)) / 2 * 2 + 1;

			j = killer;

			for (int i3 = 0; i3 < (500 - killer); i3++)	//杀掉与随机数相等的人
			{
				man[j] = man[j + 1];
				j++;
			}
			k++;
		}
		//printf("%d\n", man[1]);
		survivor[i] = man[1];
	}
	
}