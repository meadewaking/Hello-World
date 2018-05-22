#include<stdio.h>
typedef int ElemType;

typedef struct BTNode
{
	ElemType data;
	struct BTNode  *Lchild, *Rchild;
}BTNode;

void  InorderTraverse(BTNode  *T)
{
	if (T != NULL)
	{
		InorderTraverse(T->Lchild);
		visit(T->data);       /*   ���ʸ����   */
		InorderTraverse(T->Rchild);
	}
	else
	{
		return;
	}
}

void visit(ElemType data)
{
	printf("%d", data);
}

void main()
{

}