#include<stdio.h>
void main()
/*
if (year % 4 == 0)
{
	if (year % 100 == 0)
	{
		if (year % 400 == 0)
		{
			printf("����");
		}
		else
		{
			printf("ƽ��");
		}
	}
	else
	{
		printf("����");
	}
}
else
{
	printf("ƽ��");
}
*/
	
{
	while (1)		//���ڲ���
	{
		int y;		//������ݱ���
		printf("��������ݣ�");		//������ʾ
		scanf_s("%d", &y);			//�������

		if (y % 4 != 0)				//�ж��Ƿ�����4
		{
			printf("ƽ��\n");		//������
		}
		else if (y % 100 != 0)		//�ж��Ƿ�����100
		{
			printf("����\n");		//������
		}
		else if (y % 400 != 0)		//�ж��Ƿ�����400
		{
			printf("ƽ��\n");		//������
		}
		else					
		{
			printf("����\n");		//������
		}
	}

}