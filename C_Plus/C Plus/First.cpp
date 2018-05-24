#include<iostream>
#include<random>
#include<iomanip>
using namespace std;

enum game_result			//枚举类型，一点也不好用
{
	WIN,LOSE,TIE,CANCEL
};

int add(int a,int b,int c = 3) {		//常规加法函数，可以有默认值
	return a + b + c;
}

int test() {
	int a, b;
	float fl = 12.40;
	cin >> a;					//流运算符
	cin >> b;
	cout << add(a, b) << endl;
	cout << a << '\t' << fl << endl;
	return 0;
}
void dd(int &x, int &y, int z) {
	x = x + z;
	y = y - x;
	z = 10;
	cout << "(2)" << x << '\t' << y << '\t' << z << endl;
}
void test2() {
	int a = 3, b = 4, c = 5;
	for (int i = 0; i < 2; i++)
	{
		dd(a, b, c);
	}
	cout << "(1)" << a << '\t' << b << '\t' << c << endl;
}

void test3() {
	long int a = 0;
	int b = 10;
	for (double i = 0; i < 1e+10; i++)
	{
		a++;
		if (a%b == 0)
		{
			cout << a << endl;
			b = b * 10;
		}
	}
	cout << a << '\t';
}

void test4()
{
	while (1)
	{
		int y;
		cout << "请输入年份：";
		cin >> y;

		if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0))
		{
			cout << "闰年" << endl;
		}
		else
		{
			cout << "平年" << endl;
		}
	}
}

void test5() {
	game_result result;
	game_result omit = CANCEL;
	int count;
	for ( count = WIN; count < CANCEL; count++)
	{
		cout << game_result(count) << endl;
	}
}

template<typename T>		//模板类型

T addE(T a, T b) {
	return a + b;
}

void test6() {
	int a = 2, b = 3;
	float c = 1.3, d = 2.6;

	cout << addE(c, d) << endl;
	cout << addE(a, b) << endl;
}



