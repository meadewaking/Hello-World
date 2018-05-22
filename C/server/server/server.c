#include <winsock2.h>
#pragma comment(lib, "WS2_32.lib")
#pragma warning(disable:4996)
#include <stdio.h>
void main()
{
	WORD wVersionRequested;//版本号
	WSADATA wsaData;
	int err;

	wVersionRequested = MAKEWORD(2, 2);//2.2版本的套接字
									   //加载套接字库,如果失败返回
	err = WSAStartup(wVersionRequested, &wsaData);
	if (err != 0)
	{
		return;
	}

	//判断高低字节是不是2,如果不是2.2的版本则退出
	if (LOBYTE(wsaData.wVersion) != 2 ||

		HIBYTE(wsaData.wVersion) != 2)

	{
		return;
	}

	//创建流式套接字,基于TCP(SOCK_STREAM)

	SOCKET socSrv = socket(AF_INET, SOCK_STREAM, 0);

	//Socket地址结构体的创建

	SOCKADDR_IN addrSrv;

	addrSrv.sin_addr.S_un.S_addr = htonl(INADDR_ANY);//转换Unsigned long型为网络字节序格
	addrSrv.sin_family = AF_INET;//指定地址簇
	addrSrv.sin_port = htons(5000);
	//指定端口号,除sin_family参数外,其它参数都是网络字节序,因此需要转换

	//将套接字绑定到一个端口号和本地地址上
	bind(socSrv, (SOCKADDR*)&addrSrv, sizeof(SOCKADDR));//必须用sizeof，strlen不行

	listen(socSrv, 5);

	SOCKADDR_IN addrClient;//字义用来接收客户端Socket的结构体

	int len = sizeof(SOCKADDR);//初始化参数,这个参数必须进行初始化，sizeof

							   //循环等待接受客户端发送请求

	while (1)
	{
		//等待客户请求到来；当请求到来后，接受连接请求，

		//返回一个新的对应于此次连接的套接字（accept）。
		//此时程序在此发生阻塞

		SOCKET sockConn = accept(socSrv, (SOCKADDR*)&addrClient, &len);

		char sendBuf[100];

		sprintf(sendBuf, "请输入你的意见："/*"Welcome %s to meade"*/,inet_ntoa(addrClient.sin_addr));//格式化输出

											//用返回的套接字和客户端进行通信

		send(sockConn, sendBuf, strlen(sendBuf) + 1, 0);//多发送一个字节

														//接收数据

		char recvBuf[100];

		recv(sockConn, recvBuf, 100, 0);

		printf("%s\n", recvBuf);
		closesocket(sockConn);

	}
}