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
	for (i = 0; i < t->length; i++)				//主串中的起始位置
	{
		for (k = 0; k < p->length; k++)		//从起始位置开始逐个比较
		{
			if (t->str[i+k] != p->str[k] )		//发现不等，跳出比较，到下一个起始位置
			{
				break;
			}
		}
		if (k >= p->length)			//最终找到，跳出循环
		{
			break;
		}
	}
	if (i >= t->length)
	{
		return ERROR;			//最终未找到
	}
	for (j = 0; j < i; j++)
	{
		buffer[j] = t->str[j];		//复制从0开始到找到p的字符
	}
	for (j = 0; j < s->length; j++)
	{
		buffer[i + j] = s->str[j];		//将p替换为s
	}
	for (j = 0; j < (t->length - i - p->length); j++)
	{
		buffer[i + s->length + j] = t->str[i + p->length + j];		//复制后半段字符
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
				printf("字符串不等");
			}
			else
			{
				printf("字符串相等");
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
				printf("目标替换内容未找到\n");
			}
			printf("%s", t.str);
		}
	}
	
}