#include<iostream>
using namespace std;

int max(int a, int b, int c, int d = 1) {
	int max = 0;
	if (a >= b)
	{
		max = a;
	}
	else if (b >= c)
	{
		max = b;
	}
	else if (c >= d)
	{
		max = c;
	}
	else
	{
		max = d;
	}
	return max;
}

void main() {
	int a, b, c;

	cin >> a;
	cin >> b;
	cin >> c;
	cout << "×î´óÖµ:" << max(a, b, c) << endl;
}