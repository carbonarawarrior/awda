
public class CeasarCipher implements Cipherable {

	char shift;
	
	public CeasarCipher(int s) {
		this.shift = (char) s;
	}
	
	@Override
	public String encode(String plain) {
		
		String cypher = "";
		for (int i = 0; i < plain.length(); i++) {
			char currentChar = (char) (plain.charAt(i) + this.shift);
			if (currentChar > 'z') {
				cypher += (char) ((char) ('a'-1) + (currentChar - 'z'));
			} else {
				cypher += currentChar;
			}
		}
		
		return cypher;
	}
	
	@Override
	public String decode(String cypher) {
		
		String plain = "";
		for (int i = 0; i < cypher.length(); i++) {
			char currentChar = (char) (cypher.charAt(i) - this.shift);
			if (currentChar < 'a') {
				plain += (char) ((char) ('z'+1) + (currentChar - 'a'));
			} else {
				plain += currentChar;
			}
		}
		
		return plain;
	}
	
	@Override
	public String description() {
		return "Shifts letters by a certain amount";
	};

}
