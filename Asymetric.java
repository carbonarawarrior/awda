//note all the constants are stored in ints
//if we had larger primes in the no args constructor, or were given larger primes, then this could cause overflows
//Bigint is prolly more appropriate but i dont know it and this is only for understanding so
public class Asymetric implements Cipherable {
  
  private int e;//this is part pub
  private int d;//this is part private 
  private int n;

  private static int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }

    return Math.abs(a);
  }

  //thanks to chat for this function, but ill put comments to try to understand it
  private static int inverseMod(int e, int phi) {
    //t is coefficient for phi, newT coefficient for e
    int t = 0;
    int newT = 1;
    //r is current remainder, newR is next
    int r = phi;
    int newR = e;

    int quotient;
    int temp;
    while (newR != 0) {
      //this is the standard part of the euclidian algo
      quotient = r / newR;

      temp = t;
      t = newT;
      newT = temp - quotient * newT;

      temp = r;
      r = newR;
      newR = temp - quotient * newR;
    }
/*      this is a check included by chat, im pretty sure i take care of it with the preconditions though
    if (r > 1) {
      throw new ArithmeticException("e is not invertible");
    }
*/
    //just enforces a positive result
    if (t < 0) {
      t += phi;
    }

    return t;
  }
  
  public Asymetric() {

    //not secure lol
    int[] primes = {43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    int p = primes[(int) (Math.random() * primes.length)];
    int q = primes[(int) (Math.random() * primes.length)];
    boolean f = true;

    while (f) {
      if (q != p) {
        f = false;
        break;
      }
      q = primes[(int) (Math.random() * primes.length)];
    }

    this.n = p * q;
    int phiN = (p-1)*(q-1);

    boolean coPrime = true;
    while (coPrime) {
      //this is terrible efficency, we could jst use e = 65537
      this.e = (int) ((Math.random()+2)*99999);
      if ((e != 1) && (Math.abs(gcd(e, phiN)) == 1)) {
        coPrime = false;
      }
    }

    this.d = inverseMod(e, phiN); 

    System.out.println("n = " + this.n);
    System.out.println("e = " + this.e);
    System.out.println("d = " + this.d);
  }

  
  public Asymetric(int E, int D, int N) {
    boolean failed = false;
    int[] checks = {5, 8, 12, 72, 101, 1231, 6767};
    int c;
    for (int i = 0; i < checks.length; i++) {
      if (checks[i] >= N) {break;}
      c = modPow(checks[i], E, N);
      int p = modPow(c, D, N);

      if (checks[i] != p) {
        failed = true;
        break;
      }
    }

    if (E == 1 || D == 1 || N == 1) {
      failed = true;
    }
    if (failed) {
      System.out.println("Invalid input, setting to error values");
      this.e = -1;
      this.d = -1;
      this.n = -1;
    } else {
      this.e = E;
      this.d = D;
      this.n = N;
    }
  }
  
  private static int modPow(int base, int exponent, int modulus) {
    int result = 1;
    base = base % modulus;

    while (exponent > 0) {
        if ((exponent & 1) == 1) {
            result = (result * base) % modulus;
        }
        base = (base * base) % modulus;
        exponent >>= 1;
    }
    return result;
  }

  public int getE() {
    return this.e;
  }

  public int getN() {
    return this.n;
  }

  @Override
  public String encode(String plain) {
    int[] plainEncoded = new int[plain.length()];

    for (int i = 0; i < plain.length(); i++) {
      plainEncoded[i] = (int) plain.charAt(i);
    }

    int[] cypherEncoded = new int[plainEncoded.length];

    for (int i = 0; i < cypherEncoded.length; i++) {
      cypherEncoded[i] = modPow(plainEncoded[i],this.e, this.n);
    }

    StringBuilder cypher = new StringBuilder();

    for (int num : cypherEncoded) {
      cypher.append(num);
      cypher.append(" ");
    }

    return cypher.toString();

  }

  @Override
  public String decode(String cypher) {

    String[] cypherSplit = cypher.trim().split("\\s+");
    int[] cypherEncoded = new int[cypherSplit.length];
    for (int i = 0; i < cypherEncoded.length; i++) {
      cypherEncoded[i] = Integer.parseInt(cypherSplit[i]);
    }

    int[] plainEncoded = new int[cypherEncoded.length];

    for (int i = 0; i < plainEncoded.length; i++) {
      plainEncoded[i] = modPow(cypherEncoded[i], this.d, this.n);
    }

    StringBuilder plain = new StringBuilder();

    for (int num : plainEncoded) {
      plain.append((char) num);
    }

    return plain.toString();

  }

  @Override
  public String description() {
    return "uses bad version of rsa encryption";
  }
  
  
}
