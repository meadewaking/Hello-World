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
		printf("1.����\n2.�����������\n3.���\n4.����\n5.��ֵ\n6.����\n7.ɾ��\n8.����\n9.����\n0.ɾ��\n");
		printf("��������Ҫʵ�ֵĹ��ܣ�");
		scanf_s(" %c", &ch);
		switch (ch)
		{
		case'1':
			//����
			/*
			for (i = 0; i < size; i++)
			{
				printf("�������%d�Ƴɼ�", i + 1);
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
			//����
			for (i = 0; i < size; i++)
			{
				printf("%d\t", score[i]);
			}
			break;
		case'3':
			//���
			i = 0;
			while (i < 5)
			{
				sum += score[i];
				i = i++;
			}
			printf("��Ϊ%d\n", sum);
			break;
		case'4':
			//����
			for (i = 0; i < (size - 1) / 2; i++)
			{
				//ѭ��size-1��һ���
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
			//��ֵ		
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
			printf("���ֵ%d���ǵ�%d��Ԫ��\n", score[max], max + 1);
			printf("��Сֵ%d���ǵ�%d��Ԫ��\n", score[min], min + 1);
			break;
		case'6':
			//����
			printf("����������Ҫ���ҵ�����");
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
				printf("δ�ҵ�\n");
			}
			else
			{
				printf("%d�ǵ�%d����\n", num, i + 1);
			}
			break;
		case'7':
			//ɾ��
			printf("����������Ҫɾ���������±꣺");
			scanf_s("%d", &index);
			for (i = index; i < size - 1; i++)
			{
				score[i] = score[i + 1];			
			}
			score[size] = 0;
			printf("ɾ����Ϊ��\n");
			for (i = 0; i < size-1; i++)
			{
				printf("%d\n", score[i]);
			}
			break;
		case'8':
			//����
			printf("����������Ҫ����������±꣺");
			scanf_s("%d", &index);
			printf("����������Ҫ��������ֵ��");
			scanf_s("%d", &num);
			for (i = size-1; i > index; i--)
			{
				score[i] = score[i-1];
			}
			score[index] = num;
			printf("�����\n");
			for ( i = 0; i < size; i++)
			{				
				printf("%d\n", score[i]);
			}
			break;
		case'9':
			//����
			printf("1.ð�ݷ�\n2.ѡ��\n");
			scanf_s(" %c", &ch2);
			switch (ch2)
			{
			case'1':
				//ð�ݷ�
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
				//ѡ��
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
				printf("����������\n");
				break;
			}
			break;
		 default:
			printf("���޴˹���\n");
			break;
		}
	} while (ch!=0);
}
