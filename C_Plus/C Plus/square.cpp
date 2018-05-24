#include "square.h"
#include<iostream>
using namespace std;

int x = 1, y = 1;

square::square(int x = 2,int y = 2)
{
	a = x;
	b = y;
}

void square::size() {
	s = a*b;
}

void square::show() {
	cout << "Ãæ»ýÎª£º" << s << endl;
}

void main() {
	square c;
	c.size();
	c.show();
}
