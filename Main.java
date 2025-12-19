
public class Main {

	public static void main(String[] args) {
		CeasarCipher c = new CeasarCipher(5);
		String cc = c.encode("abcdefghijklmnopqrstuvwxyz");
		System.out.println(cc);
		System.out.println(c.decode(cc));

	}

}
