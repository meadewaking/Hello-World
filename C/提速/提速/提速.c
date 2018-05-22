#include<stdio.h>
#include<string.h>
#define y "是"
#pragma warning(disable:4996)

int main()
{
	while (1)
	{
		char str[20];
		printf("请输入是否\n");
		printf("是否有优惠活动？");
		scanf("%s", str);

		if (!strcmp(y,str))
		{
			printf("怎样的优惠活动？\n");
		}
		else
		{
			printf("是否有寒暑假提速套餐？\n");
		}
		printf("%d", !0);
	}
	return 0;
}