#include <stdio.h>
#include "winsock2.h"
#pragma warning(disable:4996)
#pragma comment(lib, "ws2_32.lib")

char * getname(char site[]) {
	WSADATA wsaData;
	WSAStartup(MAKEWORD(2, 2), &wsaData);
	struct hostent *host = gethostbyname(site);
	if (!host) {
		puts("Get IP address error!");
		system("pause");
		exit(0);
	}

	//别名
	for (int i = 0; host->h_aliases[i]; i++) {
		//printf("Aliases %d: %s\n", i + 1, host->h_aliases[i]);
	}

	//地址类型
	//printf("Address type: %s\n", (host->h_addrtype == AF_INET) ? "AF_INET" : "AF_INET6");

	//IP地址
	/*
	for (int i = 0; host->h_addr_list[i]; i++) {
	printf("IP addr %d: %s\n", i + 1, inet_ntoa(*(struct in_addr*)host->h_addr_list[i]));
	}
	*/
	
	for (int i = 0; host->h_addr_list[i]; i++) {
		return inet_ntoa(*(struct in_addr*)host->h_addr_list[i]);
	}
	system("pause");
}
char * buffer(char site[],char bufsite[])
{
	char a[30] = "Host:";
	char b[30] = "\r\n\r\n";
	strcpy(bufsite, a);
	strcat(bufsite, site);
	strcat(bufsite, b);
	return bufsite;
}
int main()
{
	SOCKET      sSocket = INVALID_SOCKET;
	SOCKADDR_IN stSvrAddrIn = { 0 }; /* 服务器端地址 */
	char        sndBuf[2048] = { 0 };
	char        rcvBuf[600000] = { 0 };
	char       *pRcv = rcvBuf;
	int         num = 0;
	int         nRet = SOCKET_ERROR;
	char * point;
	char site[128];
	char bufsite[128];
	printf("请输入你想要获取网页的网址：");
	scanf("%s", site);
	WSADATA     wsaData;
	buffer(site, bufsite);
	/* HTTP 消息构造开始，这是程序的关键之处 */
	sprintf(sndBuf,"GET / HTTP/1.1\n");
	strcat(sndBuf, bufsite);//(sndBuf, "Host:www.hao123.com\r\n\r\n");
	//printf("%s", sndBuf);
	/* HTTP 消息构造结束 */

	/* socket DLL初始化 */
	WSAStartup(MAKEWORD(2, 0), &wsaData);

	stSvrAddrIn.sin_family = AF_INET;
	stSvrAddrIn.sin_port = htons(80);
	point = getname(site);
	//printf("%s", point);
	stSvrAddrIn.sin_addr.s_addr = inet_addr(point);

	sSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	/* 连接 */
	nRet = connect(sSocket, (SOCKADDR*)&stSvrAddrIn, sizeof(SOCKADDR));
	if (SOCKET_ERROR == nRet)
	{
		printf("connect fail!/n");
		return -1;
	}

	/* 发送HTTP请求消息 */
	send(sSocket, (char*)sndBuf, sizeof(sndBuf), 0);

	/* 接收HTTP响应消息 */
	while (1)
	{
		num = recv(sSocket, pRcv, 2048, 0);

		pRcv += num;

		if ((0 == num) || (-1 == num))
		{
			break;
		}
	}

	/* 打印响应消息 */
	printf("%s\n", rcvBuf);
	FILE*fp = fopen("D:\\test.html", "wb");
	int i;
	if (fp == NULL)
	{
		printf("无法保存！\n");
		return;
	}

	fprintf(fp,"%s\n", rcvBuf);
	fclose(fp);
	printf("保存成功！\n");
	return 0;
}
