#include<iostream>
using namespace std;

void main() {
	float a, b, h;

	cout << "输入上底：";
	cin >> a;
	cout << "输入下底：";
	cin >> b;
	cout << "输入高：";
	cin >> h;

	cout << "面积为：" << (a + b)*h / 2 << endl;
}