#include <graphicse.h>		//ege ��ͼ��
#include <stdio.h>
#include <ege/fps.h>		//ege��
#include <time.h>

int main(void)
{
	char Line[162];			//�߻��滺��
	FILE *fp;
	int loop;


	/*��ʼ��*/
	initgraph(960, 720, ege::INIT_WITHLOGO);	//��ʼ����ͼ��������������Ϊ����logo
	setbkcolor(EGERGB(0xF5, 0xF5, 0xDC));		//���ñ�����ɫΪ��ɫ


	/*���ļ�*/
	if((fp = fopen("D:\\BadApple.txt", "r")) == NULL)
	{
		outtextxy(0, 0, "�ļ���ʧ��");		//��ͼ���������
		Sleep(2000);			//����2s
		exit(EXIT_FAILURE);			//�Զ��˳�
	}

	/*�������*/
	mciSendString(TEXT("open D:\BadApple.mp3 alias BadApple"), NULL, 0, NULL);		//���ָ������
	mciSendString(TEXT("play BadApple"), NULL, 0, NULL);			//����


	/*������ʾ*/
	fps f;				//fpsΪһ����,ֻ�������侲̬����,��;λ��,�粻��������Ť������
	setcolor(EGERGB(0xFF, 0x0, 0x0));			//������ɫ
    for ( ; !feof(fp) && is_run(); delay_fps(30))		//ѭ������ÿһ֡����,ֱ������λ��,ÿ1/30��ִ��һ��
	{

		for(loop = 0; loop < 61; loop++)		//ѭ����ȡÿһ�߻���
		{
			if(fgets(Line, 162, fp) == NULL)
				break;
			setcolor(EGERGB(0x0, 0xFF, 0x0));
			outtextxy(0, loop * 12, Line);			//���ÿһ�߻���,xyֵȡ����λ��(ʵ�ʲ��Ի���ÿ�θ���1֡,������ege�趨�й�)
		}

	}


	/*�ƺ���*/
	fclose(fp);
	closegraph();

	return 0;
}
