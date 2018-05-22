#include<stdio.h>
#include<string.h>
#include<windows.h>
#pragma warning(disable:4996)
void main()
{
	FILE * fp = fopen("D:\\BadApple.txt", "r");
	char buffer[162];
	char flush[5];
	char screen[10000] = { 0 };
	char null[2] = { 0 };

	for (int i = 0; i < 7500; i++)
	{
		for (int j = 0; j < 61; j++)
		{
			if (fgets(buffer, 162, fp) == NULL)
			{				
				break;
			}
			strcat(screen, buffer);
		}
		fgets(flush, 2, fp);
		printf("%s", screen);
		//Sleep(5);
		system("cls");
		strcpy(screen, null);
	}
	fclose(fp);
}
