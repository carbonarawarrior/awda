
public class Main {

	public static void main(String[] args) {
		CeasarCipher c = new CeasarCipher(5);
		String cc = c.encode("abcdefghijklmnopqrstuvwxyz");
		System.out.println(cc);
    cc = c.decode(cc);
		System.out.println(cc);

    SubstitutionCipher s = new SubstitutionCipher();  

    cc = s.encode(cc); 
    System.out.println(cc);
    cc = s.decode(cc);
    System.out.println(cc);
	}

}
