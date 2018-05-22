#include<stdio.h>
#define size 5
void main()
{
	int score[size];
	int i = 0;
	int sum=0;
	//遍历
	while( i < 5)
	{
		printf("请输入第%d科成绩", i + 1);
		scanf_s("%d", &score[i]);
        sum += score[i];
		i = i++;
	}
	for ( i = 0; i < 5; i++)
	{
	    printf("第%d科成绩为：%d\n",i+1,score[i]);
		
	}
	printf("总成绩：%d\n", sum);
}