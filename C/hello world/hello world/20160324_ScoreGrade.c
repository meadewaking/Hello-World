#include<stdio.h>
void main()
{
	int score = 0, i = 0;
	char op;
	do
	{
		do
		{
			if (i == 0)
			{
				printf("������ɼ���");
			}
			else
			{
				printf("����������ɼ���");
			}
			scanf_s("%d", &score);
			if (score >= 0 && score <= 100)
			{
				break;
			}
			printf("�ɼ���Ч��\n");
			i = i++;
		} while (score < 0 || score>100);
		if (score < 60)
		{
			printf("������\n");
		}
		else
		{
			printf("����\n");
		}
		printf("�Ƿ��������������������y����ֹ��������n����");
		scanf_s(" %c", &op);
	} while (op=='y'||op=='Y');
}