#pragma once
template <typename T>
class MB
{
private:
	T x, y;
public:
	MB(T, T);
	T add(T, T);
	T sub(T, T);
	T show();
};

