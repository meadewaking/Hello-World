#include "Point.h"
#include<iostream>
using namespace std;

int a = 1, b = 1;



void Point::Setxy(int a, int b)
{
	x = a;
	y = b;
}


Point::Point(int a = 0, int b = 0)
{
	x = a;
	y = b;
}

void Point::Move(int a, int b)
{
	x = x + a;
	y = y + b;
}

int Point::Gety()
{
	return y;
}

int Point::Getx()
{
	return x;
}

void Point::Display()
{
	cout << x << "," << y << endl;
}

void main() {
	Point a(10, 20);
	Point b;
	a.Display();
	a.Setxy(20, 30);
	a.Display();
	a.Move(10, 10);
	a.Display();
	b.Display();
}
