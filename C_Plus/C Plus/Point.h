#pragma once
class Point
{
private:
	int x, y;
public:

	Point(int, int);
	void Setxy(int a, int b);
	void Move(int a, int b);
	void Display();
	int Getx();
	int Gety();
};

