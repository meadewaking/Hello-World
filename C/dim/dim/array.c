#include<stdio.h>
#define size 5
void main()
{
	int score[size];
	int i = 0;
	int sum=0;
	//����
	while( i < 5)
	{
		printf("�������%d�Ƴɼ�", i + 1);
		scanf_s("%d", &score[i]);
        sum += score[i];
		i = i++;
	}
	for ( i = 0; i < 5; i++)
	{
	    printf("��%d�Ƴɼ�Ϊ��%d\n",i+1,score[i]);
		
	}
	printf("�ܳɼ���%d\n", sum);
}