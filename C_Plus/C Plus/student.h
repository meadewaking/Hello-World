#include<string>
using namespace std;

class student
{
private:
	string name;
	float math;
	float physics;
	float average;

public:
	student(string a = "meade", float b = 92.5, float c = 92.5, float d = 92.5);
	void Display();
};

