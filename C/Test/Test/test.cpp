#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define M_PI       3.14159265358979323846   // pi

//随机函数，返回一个在[0,1]区间的浮点数
double drand48() {
	return (double)rand() / (double)RAND_MAX;
}

struct Vector
{
	double _x, _y, _z;

	Vector(double x = 0, double y = 0, double z = 0) :_x(x), _y(y), _z(z) {}

	Vector operator+(const Vector &b)const {
		return Vector(_x + b._x, _y + b._y, _z + b._z);
	}
	Vector operator-(const Vector &b)const {
		return Vector(_x - b._x, _y - b._y, _z - b._z);
	}
	Vector operator*(double b) const {
		return Vector(_x * b, _y * b, _z * b);
	}
	Vector mult(const Vector &b)const {
		return Vector(_x * b._x, _y * b._y, _z * b._z);
	}
	// 向量标准化
	Vector& normalize() {
		double t = sqrt(_x * _x + _y * _y + _z * _z);//向量的模
		if (t == 0) return *this;
		*this = this->operator*(1.0 / t);
		return *this;
	}
	//点乘
	double dot(const Vector &b)const {
		return _x * b._x + _y * b._y + _z * b._z;
	}
	//叉乘
	Vector cross(const Vector &b)const {
		return Vector(_y*b._z - _z*b._y, _z*b._x - _x*b._z, _x*b._y - _y*b._x);
	}
};

struct Ray {
	Vector _origin, _direct;
	Ray(Vector origin, Vector direct) :_origin(origin), _direct(direct) {}
};

//材质类型:漫反射 镜面 折射
enum class Reflect { Diffuse, Specular, Refract };

struct Sphere {
	double _radius;
	Vector _position, _emission, _color;
	Reflect _reflect;

	Sphere(double radius, Vector position, Vector emission, Vector color, Reflect reflect) :
		_radius(radius), _position(position), _emission(emission), _color(color), _reflect(reflect) {}
	double intersect(const Ray &ray) const {
		Vector op = _position - ray._origin;
		double epsilon = 1e-4;
		double half_b = op.dot(ray._direct);
		double delta_1_4 = half_b * half_b - op.dot(op) + _radius * _radius;
		if (delta_1_4 < 0)
			return 0;
		else {
			double sqrt_delta_1_4 = sqrt(delta_1_4);
			double temp = half_b - sqrt_delta_1_4;
			if (temp > epsilon) return temp;
			temp = half_b + sqrt_delta_1_4;
			if (temp > epsilon) return temp;
			return 0;
		}
	}
};

Sphere spheres[] = {//Scene: radius, position, emission, color, material
	Sphere(1e5, Vector(1e5 + 1,40.8,81.6),  Vector(),Vector(.75,.25,.25),Reflect::Diffuse),//Left
	Sphere(1e5, Vector(-1e5 + 99,40.8,81.6),Vector(),Vector(.25,.25,.75),Reflect::Diffuse),//Rght
	Sphere(1e5, Vector(50,40.8, 1e5),       Vector(),Vector(.75,.75,.75),Reflect::Diffuse),//Back
	Sphere(1e5, Vector(50,40.8,-1e5 + 170), Vector(),Vector(),           Reflect::Diffuse),//Frnt
	Sphere(1e5, Vector(50, 1e5, 81.6),      Vector(),Vector(.75,.75,.75),Reflect::Diffuse),//Botm
	Sphere(1e5, Vector(50,-1e5 + 81.6,81.6),Vector(),Vector(.75,.75,.75),Reflect::Diffuse),//Top
	Sphere(16.5,Vector(27,16.5,47),         Vector(),Vector(1,1,1)*.999, Reflect::Specular),//Mirr
	Sphere(16.5,Vector(73,16.5,78),         Vector(),Vector(1,1,1)*.999, Reflect::Refract),//Glas
	Sphere(600, Vector(50,681.6 - .27,81.6),Vector(12,12,12),  Vector(), Reflect::Diffuse) //Lite
};

inline double clamp(double x) {
	return x < 0 ? 0 : (x > 1 ? 1 : x);
}
inline int gamma(double x) {
	return int(pow(clamp(x), 1 / 2.2) * 255 + 0.5);
}
inline bool intersect(const Ray &ray, double &distance, int &id) {
	int size = sizeof(spheres) / sizeof(Sphere);
	double d;
	double inf = distance = 1e20;
	for (int i = 0; i < size; i++) {
		d = spheres[i].intersect(ray);
		if (d != 0 && d < distance) {
			distance = d; id = i;
		}
	}
	return distance < inf;
}

Vector radiance(const Ray &ray, int depth) {
	depth++;
	double distance;
	int id = 0;
	if (!intersect(ray, distance, id))
		return Vector();
	const Sphere &obj = spheres[id];
	Vector hit_point = ray._origin + ray._direct * distance;
	Vector normal = (hit_point - obj._position).normalize();
	Vector normal_real = normal.dot(ray._direct) < 0 ? normal : normal * -1;
	Vector f = obj._color;
	double p = fmax(fmax(f._x, f._y), f._z);
	if (depth > 5) {
		if (drand48() < p) f = f * (1 / p);
		else return obj._emission;
	}
	if (depth > 100) return obj._emission;

	switch (obj._reflect)
	{
	case Reflect::Diffuse: {
		double r1 = 2 * M_PI * drand48();
		double r2 = drand48();
		double r2_sqrt = sqrt(r2);
		Vector w = normal_real;
		Vector u = (fabs(w._x) > 0.1 ? w.cross(Vector(0, 1)) : w.cross(Vector(1))).normalize();
		Vector v = w.cross(u);
		Vector direct = (u*cos(r1)*r2_sqrt + v*sin(r1)*r2_sqrt + w*sqrt(1 - r2));
		return obj._emission + f.mult(radiance(Ray(hit_point, direct), depth));
	}   break;
	case Reflect::Specular: {
		Vector direct_refl = ray._direct - normal * 2 * normal.dot(ray._direct);
		return obj._emission + radiance(Ray(hit_point, direct_refl), depth);
	}   break;
	case Reflect::Refract: {
		//反射光线
		Ray ray_refl(hit_point, ray._direct - normal * 2 * normal.dot(ray._direct));
		bool into = normal.dot(normal_real) > 0;
		double nc = 1;//真空
		double nt = 1.5;//玻璃
		double nnt = into ? nc / nt : nt / nc;
		double ddn = ray._direct.dot(normal_real) * -1;//入射角余弦

		double sin_2_t = (1 - ddn * ddn) * nnt * nnt;
		if (sin_2_t > 1.0) ////全内反射 sin^2(t)
			return obj._emission + f.mult(radiance(ray_refl, depth));
		double sin_t = sqrt(sin_2_t);
		double cos_t = sqrt(1 - sin_2_t);
		Vector refr_direct = normal_real * (-1 * cos_t)
			+ (ray._direct + normal_real * ddn).normalize() * sin_t;
		Ray ray_refr(hit_point, refr_direct);

		double F0 = (nc - nt) * (nc - nt) / ((nc + nt) * (nc + nt));
		//double c = 1 - ddn;
		//double c = 1 + refr_direct.dot(normal_real);
		//按折射率比较小的介质计算
		double c = 1 - (into ? ddn : refr_direct.dot(normal_real) * -1);
		double Fe = F0 + (1 - F0) * c * c * c * c * c;//菲涅尔反射
		double Fr = 1 - Fe;//菲涅尔折射
		if (depth > 2) {
			double P = 0.25 + 0.5 * Fe;
			if (drand48() < P)
				return radiance(ray_refl, depth) * (Fe / P);
			else
				return radiance(ray_refr, depth) * (Fr / (1 - P));
		}
		else {
			return radiance(ray_refl, depth) * Fe
				+ radiance(ray_refr, depth) * Fr;
		}
	}   break;
	default:
		break;
	}
}

int main() {
	int width = 256;
	int height = 256;
	int samples = 1;
	Ray camera(Vector(50, 52, 295.6), Vector(0, -0.042612, -1).normalize());
	Vector cx(0.5135 * width / height);
	Vector cy = cx.cross(camera._direct).normalize() * 0.5135;
	Vector *content = new Vector[width * height];
	auto clock_start = clock();
#pragma omp parallel for schedule(dynamic, 1)       // OpenMP
	for (int y = 0; y < height; y++) {//遍历行
		double time_consumed = (double)(clock() - clock_start) / CLOCKS_PER_SEC;
		fprintf(stderr, "\rRendering (%d spp) %5.2f%% %5.0f seconds remaining", samples * 4, 100.0 * y / height, time_consumed * (1 / ((y + 1.0) / height) - 1));
		for (int x = 0; x < width; x++) {//遍历列
			for (int sy = 0; sy < 2; sy++) {
				for (int sx = 0; sx < 2; sx++) {
					Vector rad;
					for (int i = 0; i < samples; i++) {
						double r1 = 2 * drand48();
						double dx = r1 < 1 ? sqrt(r1) - 1 : 1 - sqrt(2 - r1);
						double r2 = 2 * drand48();
						double dy = r2 < 1 ? sqrt(r2) - 1 : 1 - sqrt(2 - r2);
						Vector sample_direct = cx*(((dx + 0.5 + sx) / 2 + x) / width - 0.5)
							+ cy*(((dy + 0.5 + sy) / 2 + y) / height - 0.5) + camera._direct;
						rad = rad + radiance(Ray(camera._origin + sample_direct * 140, sample_direct.normalize()), 0)*(1.0 / samples);
					}
					int i = (height - y - 1) *width + x;
					content[i] = content[i] + Vector(clamp(rad._x), clamp(rad._y), clamp(rad._z)) * 0.25;
				}
			}
		}
	}

	printf("\n%f sec", (double)(clock() - clock_start) / CLOCKS_PER_SEC);
	FILE *f;
	auto err = fopen_s(&f, "d:\\image.ppm", "w");         // Write image to PPM file.
	fprintf(f, "P3\n%d %d\n%d\n", width, height, 255);
	for (int i = 0; i < width * height; i++)
		fprintf(f, "%d %d %d ", gamma(content[i]._x), gamma(content[i]._y), gamma(content[i]._z));
	fclose(f);
	system("pause");
	return 0;
}