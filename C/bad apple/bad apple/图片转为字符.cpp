/**************************************************************
** 程序名称：图片2字符图片
** 程序功能：图片根据灰度转换为ASCII字符图片
** 编译环境：VC++6.0 EGE 12.06
** 作者信息：Geodesic <Geodesicwl-cpro@yahoo.cn> 
** 最后修改：2012-07-19
**************************************************************/
#include <graphicse.h>
#include <stdio.h>


/*
 * Ascii字符和灰度
 * */
const struct AsciiPix
{
	char ascii[32];
	int gray[32];
}ascpix = {' ','`','.','^',',',':','~','"','<','!','c','t','+','{','i','7','?',
		'u','3','0','p','w','4','A','8','D','X','%','#','H','W','M',
		70,68,66,63,61,59,55,53,51,49,47,45,43,41,39,37,35,33,31,29,27,25,23,21,19,17,15,13,9,7,5,0};

/*
 * 搜寻灰度gray的替代字符
 * gray待替换的灰度
 * ascpix用于替换字符和对应灰度结构体
 * */
static SearchAsc(struct AsciiPix ascpix, int gray)
{
	int loop;

	loop = 0;
	while(gray < ascpix.gray[loop] && loop < 32 - 1)
		loop++;
	return ascpix.ascii[loop];
}

/*
 * 图片转为字符，并保存到AscPic.txt中
 * start第一张图片的序号
 * end最后一张图片的序号
 * 默认图像尺寸160×120像素
 * 默认扫描块为宽1高2的矩形（线段）
 * */
void Pic2Asc(int start, int end)
{
	int row, col;
	int gray;
	int loop;
	char filename[64];

	/*打开文件*/
	FILE *fp;
	if((fp = fopen("AscPic.txt", "w")) == NULL)
	{
		outtextxy(0, 0, "文件打开失败");
		Sleep(3000);
		return;
	}

	/*开始转换*/
	PIMAGE img;
	for(img = newimage(), loop = start; loop <= end; loop++)
	{
		sprintf(filename, "BAD_APPLE%08d.jpg", loop);
		outtextxy(0, 0, filename);

		getimage(img, filename, 0, 0);
		for(row = 0; row < 120; row += 2)
		{
			for(col = 0; col < 160; col++)	
			{	
				gray = (GetRValue(getpixel(col, row, img)) + GetRValue(getpixel(col, row + 1, img))) * 9 / 51;
				fputc(SearchAsc(ascpix, gray), fp);
			}
			fputc('\n', fp);
		}
		fputc('\n', fp);
	}

	/*善后处理*/
	delimage(img);
	fclose(fp);
}

/*
 * 主函数
 * */
int main(void)
{	
	initgraph(640, 480);
   
	Pic2Asc(6529, 13009);
	getch();

    closegraph();
	return 0;
}


