#include<stdio.h>
#include <graphics.h>
#include <tchar.h>
#include<mmsystem.h>
#pragma warning(disable:4996)
#pragma comment(lib, "WINMM.LIB")
void bad apple()
{
	initgraph(960,720);
	FILE * fp = fopen("D:\\BadApple.txt", "r");
	TCHAR buffer[162];
	TCHAR flush[5];
	int i, j;
	settextstyle(10, 6, _T("ºÚÌå"));
	mciSendString(TEXT("open D:\BadApple.mp3 alias BadApple"), NULL, 0, NULL);
	mciSendString(TEXT("play BadApple"), NULL, 0, NULL);
	for ( j = 0; j < 7500; j++)
	{
		for ( i = 0; i < 61; i++)
		{
			if (_fgetts(buffer, 162, fp) == NULL)
				break;
			outtextxy(0, i * 12, buffer);
		}
		_fgetts(flush, 2, fp);
		Sleep(14.9999999999995);
	}

	fclose(fp);
	closegraph();
}