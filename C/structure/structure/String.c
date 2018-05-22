#include<stdio.h>
#include<malloc.h>
#pragma warning(disable:4996)
#define MAX_STRLEN  256
#define ERROR 0;
#define OK 1;

typedef  struct
{
	char  str[MAX_STRLEN];
	int  length;

} StringType;

int compare(StringType a, StringType b)
{
	if (a.length != b.length)
	{
		return ERROR;
	}
	else
	{
		int i = 0;
		while (a.str[i] == b.str[i])
		{
			i++;
		}
		if (i >= a.length)
		{
			return OK;
		}
		else
		{
			return ERROR;
		}
	}
}

int StrRelace(StringType *t, StringType *p, StringType *s)
{
	if (p->length > t->length)
	{
		return ERROR;
	}
	int i = 0;
	int k = 0;
	int j = 0;
	char * buffer;
	buffer = (char *)malloc(sizeof(t->str));
	for (i = 0; i < t->length; i++)				//�����е���ʼλ��
	{
		for (k = 0; k < p->length; k++)		//����ʼλ�ÿ�ʼ����Ƚ�
		{
			if (t->str[i+k] != p->str[k] )		//���ֲ��ȣ������Ƚϣ�����һ����ʼλ��
			{
				break;
			}
		}
		if (k >= p->length)			//�����ҵ�������ѭ��
		{
			break;
		}
	}
	if (i >= t->length)
	{
		return ERROR;			//����δ�ҵ�
	}
	for (j = 0; j < i; j++)
	{
		buffer[j] = t->str[j];		//���ƴ�0��ʼ���ҵ�p���ַ�
	}
	for (j = 0; j < s->length; j++)
	{
		buffer[i + j] = s->str[j];		//��p�滻Ϊs
	}
	for (j = 0; j < (t->length - i - p->length); j++)
	{
		buffer[i + s->length + j] = t->str[i + p->length + j];		//���ƺ����ַ�
	}
	buffer[i + s->length + j] = '\0';
	for (j = 0; j < strlen(buffer); j++)
	{
		t->str[j] = buffer[j];
	}
	t->str[j] = '\0';
	t->length = strlen(t->str);
}

void main()
{
	int ch = 0;
	scanf("%d", &ch);
	switch (ch)
	{
	case 1:
		{
			int c = 0;
			StringType a;
			scanf("%s", a.str);
			a.length = strlen(a.str);
			StringType b;
			scanf("%s", b.str);
			b.length = strlen(b.str);
			c = compare(a, b);
			if (c == 0)
			{
				printf("�ַ�������");
			}
			else
			{
				printf("�ַ������");
			}
			break;
		}
	case 2:
		{
			StringType t;
			scanf("%s", t.str);
			t.length = strlen(t.str);
			StringType p;
			scanf("%s", p.str);
			p.length = strlen(p.str);
			StringType s;
			scanf("%s", s.str);
			s.length = strlen(s.str);
			if (!StrRelace(&t, &p, &s))
			{
				printf("Ŀ���滻����δ�ҵ�\n");
			}
			printf("%s", t.str);
		}
	}
	
}