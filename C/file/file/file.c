#include<stdio.h>
#pragma warning(disable:4996)
void main()
{
	/*
	int num;
	char str[20];
	FILE * fp = fopen("D:\\helloworld.txt", "w");
	gets(str);
	fprintf(fp, "%s\n", str);
	fclose(fp);
	*/
	FILE*fp = fopen("D:StudentMis.txt", "r");
	char str[200];
	fgets(str,200,fp);
	printf("%s\n", str);
	fclose(fp);
}