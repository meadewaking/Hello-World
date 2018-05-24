#include<iostream>
using namespace std;

template<typename T>
void sort(T n[]) {
	int min;
	for (int i = 0; i < 5 - 1; i++)
	{
		min = i;
		int j;
		for (j = i + 1; j < 5; j++)
		{
			if (n[min] > n[j])
			{
				min = j;
			}
		}

		if (n[min] != n[i])
		{
			int temp;
			temp = n[i];
			n[i] = n[min];
			n[min] = temp;
		}
	}
}

void main() {
	int n[5];
	for (int i = 0; i < 5; i++)
	{
		cin >> n[i];
	}
	sort(n);
	for (int i = 0; i < 5; i++)
	{
		cout << n[i] << " ";
	}
}