#include<stdio.h>
void main()
{
	int score = 0;	//�����ɼ�����score

	printf("������ɼ���");		//����ɼ���ʾ
	scanf_s("%d", &score);		//����ɼ�

	if (score<60)		//�����ж�
	{
		printf("��Ҫ����\n");		//������
	}
}