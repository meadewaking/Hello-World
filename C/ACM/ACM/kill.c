#include<stdio.h>
void main()
{
	int k = 0;		//��ɱ����
	int j = 0;
	int man[510];		//�ų��ŵȴ���ɱ����
	int killer = 0;		//���������ɱ������������һ��С������������
	int survivor[110];	//�Ҵ���

	for (int i = 0; i < 100; i++)
	{
		k = 0;
		for (int i1 = 0; i1 < 500; i1++)
		{
			man[i1 + 1] = i1 + 1;		//��װ
		}

		for (int i2 = 0; i2 < (500 - 2); i2++)
		{

			srand((unsigned)time(NULL));		//�����������
			killer = (rand() % (500 - k)) / 2 * 2 + 1;

			j = killer;

			for (int i3 = 0; i3 < (500 - killer); i3++)	//ɱ�����������ȵ���
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