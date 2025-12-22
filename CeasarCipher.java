public class CeasarCipher implements Cipherable {
  private int shiftAmt;

  public CeasarCipher(int shift) {
    if (shift > 0) {
      this.shiftAmt = shift;
    } else {
      System.out.println("Invalid input, generating key");
      this.shiftAmt = (int) (Math.random() * 27);
      System.out.println("Key = " + this.shiftAmt);
    }
  }

  public CeasarCipher() {
    this.shiftAmt = (int) (Math.random() * 27);
    System.out.println("Key = " + this.shiftAmt);
  }

  @Override
  public String encode(String in) {
    String upper = in.toUpperCase();
    String answer = "";
    for (int i = 0; i < in.length(); i++) {
      if (Character.isLetter(upper.charAt(i))) {
        char ch = (char) (upper.charAt(i) + shiftAmt);
        if (ch > 'Z') {
          ch = (char) (ch - 26);
        }
        if (Character.isLowerCase(in.charAt(i))) {
          ch = Character.toLowerCase(ch);
        }
        answer += ch;
      } else {
        answer += upper.charAt(i);
      }
    }
    return answer;
  }


  @Override
  public String decode(String in) {
    shiftAmt = 26 - shiftAmt;
    String answer = this.encode(in);
    shiftAmt = 26 - shiftAmt;
    return answer;
  }

  @Override
  public String description() {
    return "shifts all letters by a certain amount";
  }
}
