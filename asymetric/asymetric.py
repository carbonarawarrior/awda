import math
import random

p = 17
q = 23

n = p * q

phiN = ((p - 1) * (q - 1))
isCoprime = False

while isCoprime is False:
    e = random.randint(2, 9999)
    if abs(math.gcd(e, phiN)) == 1:
        isCoprime = True;

d = pow(e, -1, phiN)
print(d)
