#include<stdio.h>
#define size 10
void main()
{
	int score[size];
	int i = 0;
	int sum = 0;
	float temp = 0;
	int min = 0;
	int j = 0;
	int max = 0;
	int num = 0;
	int index = 0;
	char ch = '0';
	char ch2 = '0';
	do
	{
		printf("1.输入\n2.遍历（输出）\n3.求和\n4.倒置\n5.最值\n6.查找\n7.删除\n8.插入\n9.排序\n0.删除\n");
		printf("请输入你要实现的功能：");
		scanf_s(" %c", &ch);
		switch (ch)
		{
		case'1':
			//输入
			/*
			for (i = 0; i < size; i++)
			{
				printf("请输入第%d科成绩", i + 1);
				scanf_s("%d", &score[i]);
			}
			break;
			*/
			srand((unsigned)time(NULL));
			for ( i = 0; i < size; i++)
			{
				score[i] = rand()%100;
			}
			break;

		case'2':
			//遍历
			for (i = 0; i < size; i++)
			{
				printf("%d\t", score[i]);
			}
			break;
		case'3':
			//求和
			i = 0;
			while (i < 5)
			{
				sum += score[i];
				i = i++;
			}
			printf("和为%d\n", sum);
			break;
		case'4':
			//倒置
			for (i = 0; i < (size - 1) / 2; i++)
			{
				//循环size-1的一半次
				temp = score[i];
				score[i] = score[(size - 1) - i];
				score[(size - 1) - i] = temp;
			}
			for (i = 0; i < size; i++)
			{
				printf("%d\n", score[i]);
			}
			break;
		case'5':
			//最值		
			for (i = 1; i < size; i++)
			{
				if (score[min] > score[i])
				{
					min = i;
				}
			}
			for (i = 1; i < size; i++)
			{
				if (score[max] < score[i])
				{
					max = i;
				}
			}
			printf("最大值%d，是第%d个元素\n", score[max], max + 1);
			printf("最小值%d，是第%d个元素\n", score[min], min + 1);
			break;
		case'6':
			//查找
			printf("请输入你想要查找的数：");
			scanf_s("%d", &num);
			for (i = 0; i < size; i++)
			{
				if (num == score[i])
				{
					break;
				}
			}
			if (i == size)
			{
				printf("未找到\n");
			}
			else
			{
				printf("%d是第%d个数\n", num, i + 1);
			}
			break;
		case'7':
			//删除
			printf("请输入你想要删除的数的下标：");
			scanf_s("%d", &index);
			for (i = index; i < size - 1; i++)
			{
				score[i] = score[i + 1];			
			}
			score[size] = 0;
			printf("删除后为：\n");
			for (i = 0; i < size-1; i++)
			{
				printf("%d\n", score[i]);
			}
			break;
		case'8':
			//插入
			printf("请输入你想要插入的数的下标：");
			scanf_s("%d", &index);
			printf("请输入你想要插入数的值：");
			scanf_s("%d", &num);
			for (i = size-1; i > index; i--)
			{
				score[i] = score[i-1];
			}
			score[index] = num;
			printf("插入后：\n");
			for ( i = 0; i < size; i++)
			{				
				printf("%d\n", score[i]);
			}
			break;
		case'9':
			//排序
			printf("1.冒泡法\n2.选择法\n");
			scanf_s(" %c", &ch2);
			switch (ch2)
			{
			case'1':
				//冒泡法
				for (i = 0; i < size - 1; i++)
				{
					for (j = 0; j < size - 1 - i; j++)
					{
						if (score[j] > score[j + 1])
						{
							temp = score[j];
							score[j] = score[j + 1];
							score[j + 1] = temp;
						}
					}
				}
				for (i = 0; i < size; i++)
				{
					printf("%d\t", score[i]);
				}
				break;
			case'2':
				//选择法
				for ( i = 0; i < size-1; i++)
				{
					for ( j = i; j < size; j++)
					{
						if (score[i]>score[j])
						{
							temp = score[i];
							score[i] = score[j];
							score[j] = temp;
						}
					}
				}
				break;
			default:
				printf("请重新输入\n");
				break;
			}
			break;
		 default:
			printf("暂无此功能\n");
			break;
		}
	} while (ch!=0);
}
