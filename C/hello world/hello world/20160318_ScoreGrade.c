#include<stdio.h>
void main()
{
	while (1)
	{


		int number = 0;		//������������
		printf("�����������");		//������ʾ
		scanf_s("%d", &number);		//�������

		if (number > 100 || number < 0)		//�ж������Ƿ���Ч
			printf("��Ч\n");				//������
		else if (number >= 90)				//�ж���������
			printf("����\n");				//������
		else if (number >= 80)				//�ж���������
			printf("����\n");				//������
		else if (number >= 70)				//�ж���������
			printf("�е�\n");				//������
		else if (number >= 60)				//�ж���������
			printf("����\n");				//������
		else								//�ж���������
		{
			printf("������\n");				//������
		}

	}

}       