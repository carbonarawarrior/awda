public class SubstitutionCipher implements Cipherable {

	private String key;
	
	public SubstitutionCipher() {
    this.key = "";
		String alpa = "abcdefghijklmnopqrstuvwxyz";
		
		while (alpa.length() > 0) {
			int randIndex = (int) (Math.random() * (alpa.length()));
			this.key += alpa.charAt(randIndex);
			alpa = alpa.substring(0, randIndex) + alpa.substring(randIndex + 1);
		}
	}

	@Override
	public String encode(String plain) {
    String cipher = "";

    for (int i = 0; i < plain.length(); i++) {
      int numericLetter = plain.charAt(i) - 'a';
      cipher += this.key.charAt(numericLetter);
    }
 
		return cipher;
	}

	@Override
	public String decode(String cipher) {
    String plain = "";

    for (int i = 0; i < cipher.length(); i++) {
      char currentChar = cipher.charAt(i);
      int correctIndex = this.key.indexOf(currentChar);

      plain += (char) (correctIndex + 'a');
    }

		return plain;
	}

	@Override
	public String description() {
		return "has a scrambled list of letters, and swaps them";
	}

	
}
