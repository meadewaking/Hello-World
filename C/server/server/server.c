#include <winsock2.h>
#pragma comment(lib, "WS2_32.lib")
#pragma warning(disable:4996)
#include <stdio.h>
void main()
{
	WORD wVersionRequested;//�汾��
	WSADATA wsaData;
	int err;

	wVersionRequested = MAKEWORD(2, 2);//2.2�汾���׽���
									   //�����׽��ֿ�,���ʧ�ܷ���
	err = WSAStartup(wVersionRequested, &wsaData);
	if (err != 0)
	{
		return;
	}

	//�жϸߵ��ֽ��ǲ���2,�������2.2�İ汾���˳�
	if (LOBYTE(wsaData.wVersion) != 2 ||

		HIBYTE(wsaData.wVersion) != 2)

	{
		return;
	}

	//������ʽ�׽���,����TCP(SOCK_STREAM)

	SOCKET socSrv = socket(AF_INET, SOCK_STREAM, 0);

	//Socket��ַ�ṹ��Ĵ���

	SOCKADDR_IN addrSrv;

	addrSrv.sin_addr.S_un.S_addr = htonl(INADDR_ANY);//ת��Unsigned long��Ϊ�����ֽ����
	addrSrv.sin_family = AF_INET;//ָ����ַ��
	addrSrv.sin_port = htons(5000);
	//ָ���˿ں�,��sin_family������,�����������������ֽ���,�����Ҫת��

	//���׽��ְ󶨵�һ���˿ںźͱ��ص�ַ��
	bind(socSrv, (SOCKADDR*)&addrSrv, sizeof(SOCKADDR));//������sizeof��strlen����

	listen(socSrv, 5);

	SOCKADDR_IN addrClient;//�����������տͻ���Socket�Ľṹ��

	int len = sizeof(SOCKADDR);//��ʼ������,�������������г�ʼ����sizeof

							   //ѭ���ȴ����ܿͻ��˷�������

	while (1)
	{
		//�ȴ��ͻ������������������󣬽�����������

		//����һ���µĶ�Ӧ�ڴ˴����ӵ��׽��֣�accept����
		//��ʱ�����ڴ˷�������

		SOCKET sockConn = accept(socSrv, (SOCKADDR*)&addrClient, &len);

		char sendBuf[100];

		sprintf(sendBuf, "��������������"/*"Welcome %s to meade"*/,inet_ntoa(addrClient.sin_addr));//��ʽ�����

											//�÷��ص��׽��ֺͿͻ��˽���ͨ��

		send(sockConn, sendBuf, strlen(sendBuf) + 1, 0);//�෢��һ���ֽ�

														//��������

		char recvBuf[100];

		recv(sockConn, recvBuf, 100, 0);

		printf("%s\n", recvBuf);
		closesocket(sockConn);

	}
}