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
		fgets(html[r],99,fp);			//将文件按行读取到二维数组中
		r++;
	}
	fclose(fp);
	while (1)
	{
		if (strstr(html[i],"<title>"))			//查找字符串中是否包含指定字符串
		{
			//printf("%s\n", html[i]);
			while (1)
			{
				if (html[i][k] == '>')			//找到指定字符，并开始写入到find字符串中
				{
					k++;
					for ( j = 0; j < 50; j++)	
					{
						find[j] = html[i][k];	//写入字符
						k++;
						if (html[i][k]=='<')	//找到了指定的终止字符，终止写入
						{
							find[j + 1] = '\0';		//写入终止符
							printf("%s\n", find);
							return;		//终止程序
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