import matplotlib.pyplot as plt
import numpy as np

n1 = 460.0
a = 0.19
b = 0.1
L1 = 0.13
L2 = 0.42
L3 = 0.44
L4 = 0.4
Lce = 0.22
m3 = 12
m4 = 13
m5 = 13
Lds3 = 0.08
Les4 = 0.2
Js3 = 0.03
Js4 = 3
Pr = 2.2       #变量声明
theta1 = 60

def displacement(theta1):
    A = 2 * b * L2 - 2 * L1 * L2 * np.sin(theta1)
    B = -2 * a * L2 - 2 * L1 * L2 * np.cos(theta1)
    C = L1 ** 2 + 2 * a * L1 * np.cos(theta1) - 2 * b * L1 * np.sin(theta1) + a ** 2 + b ** 2 - L3 ** 2 + L2 ** 2
    theta2 = 2 * np.arctan((A - np.sqrt(A ** 2 + B ** 2 - C ** 2))/(B - C))
    theta3 = np.arccos((L1 * np.cos(theta1) + a - L2 * np.cos(theta2))/L3)
    theta4 = np.arcsin((Lce * np.sin(theta3) - b)/L4)
    x5 = L4 * np.cos(theta4) + Lce * np.cos(theta3)

    return x5

def velocity(theta1):
    A = 2 * b * L2 - 2 * L1 * L2 * np.sin(theta1)
    B = -2 * a * L2 - 2 * L1 * L2 * np.cos(theta1)
    C = L1 ** 2 + 2 * a * L1 * np.cos(theta1) - 2 * b * L1 * np.sin(theta1) + a ** 2 + b ** 2 - L3 ** 2 + L2 ** 2
    theta2 = 2 * np.arctan((A - np.sqrt(A ** 2 + B ** 2 - C ** 2))/(B - C))
    theta3 = np.arccos((L1 * np.cos(theta1) + a - L2 * np.cos(theta2))/L3)
    theta4 = np.arcsin((Lce * np.sin(theta3) - b)/L4)
    omega1 = (2 * np.pi * n1)/60
    omega3 = (L1 * np.sin(theta1-theta2) * omega1)/(L3 * np.sin(theta2 - theta3))
    omega2 = (L1 * np.sin(theta1) * omega1 - L3 * np.sin(theta3) * omega3)/(L2 * np.sin(theta2))
    omega4 = (Lce * np.cos(theta3))/(L4 * np.cos(theta4))
    v5 = -L4 * np.sin(theta4) * omega4 - Lce * np.sin(theta3) * omega3

    return v5

def accelerate(theta1):
    A = 2 * b * L2 - 2 * L1 * L2 * np.sin(theta1)
    B = -2 * a * L2 - 2 * L1 * L2 * np.cos(theta1)
    C = L1 ** 2 + 2 * a * L1 * np.cos(theta1) - 2 * b * L1 * np.sin(theta1) + a ** 2 + b ** 2 - L3 ** 2 + L2 ** 2
    theta2 = 2 * np.arctan((A - np.sqrt(A ** 2 + B ** 2 - C ** 2))/(B - C))
    theta3 = np.arccos((L1 * np.cos(theta1) + a - L2 * np.cos(theta2))/L3)
    theta4 = np.arcsin((Lce * np.sin(theta3) - b)/L4)
    omega1 = (2 * np.pi * n1)/60
    omega3 = (L1 * np.sin(theta1-theta2) * omega1)/(L3 * np.sin(theta2 - theta3))
    omega2 = (L1 * np.sin(theta1) * omega1 - L3 * np.sin(theta3) * omega3)/(L2 * np.sin(theta2))
    omega4 = (Lce * np.cos(theta3))/(L4 * np.cos(theta4))
    epsilon3 = (-L1 * (omega1 ** 2) * np.cos(theta1 - theta2) + b * (omega2 ** 2) - L3 * (omega3 ** 2) * np.cos(theta2 - theta3))/(L3 * np.cos(theta2 - theta3))
    epsilon4 = (-L3 * np.sin(theta3) * (omega3 ** 2) + L3 * np.cos(theta3) * epsilon3 + L4 * np.sin(theta4) * (omega4 ** 2))/(L4 * np.cos(theta4))
    a5 = -L4 * np.cos(theta4) * (omega4 ** 2) - L4 * np.sin(theta4) * epsilon4 - Lce * np.cos(theta3) * (omega3 ** 2) - Lce * np.sin(theta3) * epsilon3

    return a5


theta1 = np.arange(0.0, 6.0, 0.1)
plt.figure(1)
plt.plot(theta1,displacement(theta1))
plt.figure(2)
plt.plot(theta1,velocity(theta1))
plt.figure(3)
plt.plot(theta1,accelerate(theta1))
plt.show()