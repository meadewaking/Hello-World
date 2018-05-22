#include <stdio.h>
#include <string.h>
#include <winsock2.h>
#pragma comment(lib, "Ws2_32.lib")
#pragma warning(disable:4996)

void main()
{
	while (1)
	{
		
		WORD wVersionRequested;
		WSADATA wsaData;
		int err;
		int i = 0, n = 0;

		wVersionRequested = MAKEWORD(2, 2);

		err = WSAStartup(wVersionRequested, &wsaData);
		if (err != 0) {

			return;
		}


		if (LOBYTE(wsaData.wVersion) != 2 ||
			HIBYTE(wsaData.wVersion) != 2)
		{

			WSACleanup();
			return;
		}
			SOCKET socketClient = socket(AF_INET, SOCK_STREAM, 0);
			SOCKADDR_IN addrSrv;
			addrSrv.sin_family = AF_INET;
			addrSrv.sin_port = htons(5000);
			addrSrv.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//服务器端的IP地址

			connect(socketClient, (SOCKADDR *)&addrSrv, sizeof(SOCKADDR));

			char recvBuf[256];
			char sendBuf[256];
			for ( n = 0; n < 2; n++)
			{
				recv(socketClient, recvBuf, 256, 0);
				printf("%s\n", recvBuf);
				if (i==0)
				{
					break;
				}
			}
			printf("请输入需要发送的消息:");
			scanf("%s", &sendBuf);
			send(socketClient, sendBuf, strlen(sendBuf) + 1, 0);
			closesocket(socketClient);
			WSACleanup();
			i++;
	}

}