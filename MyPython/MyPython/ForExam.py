s = input()
o = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ")
v = "n o p q r s t u v w x y z a b c d e f g h i j k l m".split(" ")
d = {}
x = 0

for i in o:
    d[i] = v[x]
    x += 1
x = 0
for i in o:
    d[i.upper()] = v[x].upper()
    x += 1
t = str()
for l in s:
    if l in d:
        t += d[l]
    else:
        t += l
print(t)
        