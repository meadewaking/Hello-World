#include<stdio.h>
#include<string.h>
#define y "��"
#pragma warning(disable:4996)

int main()
{
	while (1)
	{
		char str[20];
		printf("�������Ƿ�\n");
		printf("�Ƿ����Żݻ��");
		scanf("%s", str);

		if (!strcmp(y,str))
		{
			printf("�������Żݻ��\n");
		}
		else
		{
			printf("�Ƿ��к���������ײͣ�\n");
		}
		printf("%d", !0);
	}
	return 0;
}