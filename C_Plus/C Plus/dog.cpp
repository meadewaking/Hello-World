#include "dog.h"
#include <iostream>
using namespace std;

int dog::dogs = 20;

dog::dog()
{

}

void dog::setdogs(int a) {
	dogs = a;
}

int dog::getdogs() {
	return dogs;
}

void main() {
	dog b;
	b.setdogs(25);
	cout << b.getdogs() << endl;
}