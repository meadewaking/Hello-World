#include "MB.h"
#include<iostream>
using namespace std;

template <typename T>
MB<T>::MB(T x, T y):x(x),y(y){

}

template <typename T>
T MB<T>::add(T x, T y)
{
	return x + y;
}

template <typename T>
T MB<T>::sub(T x, T y)
{
	return x - y;
}

template <typename T>

T MB<T>::show() {
	cout << sub(x, y) << endl;
	cout << add(x, y) << endl;
	return 0;
}

void main() {
	MB<int> mb1(5, 3);
	MB<float> mb2(5.2, 3.1);
	MB<double> mb3(5.25, 3.34);

	mb1.show();
	mb2.show();
	mb3.show();
	
}
