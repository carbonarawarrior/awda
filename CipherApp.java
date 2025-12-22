
import java.util.Scanner;
public class CipherApp {

	public static void main(String[] args) {
    boolean inMenu = true;
    Scanner userIn = new Scanner(System.in);
    System.out.println("Pick an encryption:");
    System.out.println("1. Ceasar");
    System.out.println("2. Substitution");
    System.out.println("3. Asymetric");

    Cipherable algo = null;
    do {
      int userPick = userIn.nextInt();
      userIn.nextLine();

      boolean nonvalid = false;
      if (userPick == 1) {
        int pick = 1;
        while (!nonvalid) {
          System.out.println("How big should the shift be? Or enter -1 for random generation");
          pick = userIn.nextInt();
          userIn.nextLine();
          nonvalid = true;
        }
          if (pick == -1) {
            algo = new CeasarCipher();
          } else {
            algo = new CeasarCipher(pick);
          }
        inMenu = false;
      } else if (userPick == 2) {
        while (!nonvalid) {
          System.out.println("enter valid key, or leave blank for random");
          String pick = userIn.nextLine();
          if (pick.trim().isEmpty()) {
            algo = new SubstitutionCipher();
          } else {
            algo = new SubstitutionCipher(pick);
          }
          nonvalid = true;
        }
        inMenu = false;
      } else if (userPick == 3) {
        System.out.println("Enter Valid values for n, e, and d. Or input -1 to any to generate a key.");

        int n = -1;
        int d = -1;
        int e = -1;
        do {
          System.out.print("n: ");
          n = userIn.nextInt();
          userIn.nextLine();
          if (n == -1) break;
          System.out.print("e: ");
          e = userIn.nextInt();
          userIn.nextLine();
          if (e == -1) break;
          System.out.print("d: ");
          d = userIn.nextInt();
          userIn.nextLine();
          if (d == -1) break;
        } while (false);

        if (n == -1 || e == -1 || d == -1) {
          algo = new Asymetric();
        } else {
          algo = new Asymetric(e, d, n);
        }
        inMenu = false;
      } else {
        System.out.println("Not valid input");
      }
    } while (inMenu);
    System.out.println("What do you want to do?");
    System.out.println("1. Encrypt");
    System.out.println("2. Decrypt");
    System.out.println("3. Get Description");

    inMenu = true;
    do {
      int userPick = userIn.nextInt();
      userIn.nextLine();

      if (userPick == 1) {
        System.out.println("What do you want to encrypt?");
        String text = userIn.nextLine();
        System.out.println(algo.encode(text));
        inMenu = false;
      } else if (userPick == 2) {
        System.out.println("What do you want to decrypt?");
        String text = userIn.nextLine();
        System.out.println(algo.decode(text));
        inMenu = false;
      } else if (userPick == 3) {
        String text = userIn.nextLine();
        System.out.println(algo.description());
        inMenu = false;
      } else {
        System.out.println("Not valid input");
      }
    } while (inMenu);
  }
		
}
