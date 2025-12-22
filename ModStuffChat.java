//this file jst a container for these two guys, really intesting imo
public class ModStuffChat {
  private static int[] extendedGCD(int a, int b) {
    if (b == 0) {
        // gcd(a, 0) = a
        return new int[] { a, 1, 0 };
    }

    int[] result = extendedGCD(b, a % b);

    int gcd = result[0];
    int x1 = result[1];
    int y1 = result[2];

    // Back-substitute
    int x = y1;
    int y = x1 - (a / b) * y1;

    return new int[] { gcd, x, y };
  }

  private static int modInverse(int e, int phi) {
    int[] result = extendedGCD(e, phi);

    int gcd = result[0];
    int x = result[1];   // coefficient of e

    if (gcd != 1) {
        throw new ArithmeticException("Inverse does not exist");
    }

    // Make positive modulo phi
    return (x % phi + phi) % phi;
  }
}
