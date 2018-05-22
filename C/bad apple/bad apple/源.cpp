#include<stdio.h>
#include<graphics.h>
#include<tchar.h>
void main()
{
	TCHAR s[20];
	_stprintf_s(s, _T("%d"), 1024);
	outtextxy(10, 20, s);
}