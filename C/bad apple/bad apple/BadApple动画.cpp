#include <graphicse.h>		//ege 绘图库
#include <stdio.h>
#include <ege/fps.h>		//ege库
#include <time.h>

int main(void)
{
	char Line[162];			//线画面缓存
	FILE *fp;
	int loop;


	/*初始化*/
	initgraph(960, 720, ege::INIT_WITHLOGO);	//初始化绘图区，第三个参数为播放logo
	setbkcolor(EGERGB(0xF5, 0xF5, 0xDC));		//设置背景颜色为米色


	/*打开文件*/
	if((fp = fopen("D:\\BadApple.txt", "r")) == NULL)
	{
		outtextxy(0, 0, "文件打开失败");		//绘图区输出文字
		Sleep(2000);			//挂起2s
		exit(EXIT_FAILURE);			//自动退出
	}

	/*添加音乐*/
	mciSendString(TEXT("open D:\BadApple.mp3 alias BadApple"), NULL, 0, NULL);		//添加指定音乐
	mciSendString(TEXT("play BadApple"), NULL, 0, NULL);			//播放


	/*动画演示*/
	fps f;				//fps为一个类,只调用了其静态方法,用途位置,如不声明画面扭曲变形
	setcolor(EGERGB(0xFF, 0x0, 0x0));			//设置颜色
    for ( ; !feof(fp) && is_run(); delay_fps(30))		//循环播放每一帧画面,直到播完位置,每1/30秒执行一次
	{

		for(loop = 0; loop < 61; loop++)		//循环读取每一线画面
		{
			if(fgets(Line, 162, fp) == NULL)
				break;
			setcolor(EGERGB(0x0, 0xFF, 0x0));
			outtextxy(0, loop * 12, Line);			//输出每一线画面,xy值取像素位置(实际测试画面每次更新1帧,怀疑与ege设定有关)
		}

	}


	/*善后处理*/
	fclose(fp);
	closegraph();

	return 0;
}
