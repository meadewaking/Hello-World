#include<stdio.h>
void main()
{
	int num = 0;	//������ʼ����num
	printf("������һ������");	//������ʾ
	scanf_s("%d", &num);		//����ֵ
	if (num%2==0)			//�ж��Ƿ�����2
	{
		printf("%d��ż��\n", num);		//������
	}
	else
	{
		printf("%d������\n", num);		//������
	}
}