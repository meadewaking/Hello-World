#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
void main()
{
	char html[100][100];
	char find[50];
	FILE * fp = fopen("D:\\test.html", "r");
	int i = 0, k = 0, r = 0, j = 0;
	while (r<99)
	{
		fgets(html[r],99,fp);			//���ļ����ж�ȡ����ά������
		r++;
	}
	fclose(fp);
	while (1)
	{
		if (strstr(html[i],"<title>"))			//�����ַ������Ƿ����ָ���ַ���
		{
			//printf("%s\n", html[i]);
			while (1)
			{
				if (html[i][k] == '>')			//�ҵ�ָ���ַ�������ʼд�뵽find�ַ�����
				{
					k++;
					for ( j = 0; j < 50; j++)	
					{
						find[j] = html[i][k];	//д���ַ�
						k++;
						if (html[i][k]=='<')	//�ҵ���ָ������ֹ�ַ�����ֹд��
						{
							find[j + 1] = '\0';		//д����ֹ��
							printf("%s\n", find);
							return;		//��ֹ����
						}
					}
				
				}
				k++;
			}
			
			break;
		}
		i++;
		if (i>=100)
		{
			break;
		}
	}
	printf("%s\n", find);
}