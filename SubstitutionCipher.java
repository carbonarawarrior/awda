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
    System.out.println("Key = " + this.key);
	}

  public SubstitutionCipher(String scrambled) {
    if (scrambled.length() != 26) {
      System.out.println("Not 26 characters, generating random key");
      this.key = "";
      String alpa = "abcdefghijklmnopqrstuvwxyz";
      
      while (alpa.length() > 0) {
        int randIndex = (int) (Math.random() * (alpa.length()));
        this.key += alpa.charAt(randIndex);
        alpa = alpa.substring(0, randIndex) + alpa.substring(randIndex + 1);
		  }
      System.out.println("Key = " + this.key);
      //this is a crazy one liner, but checks for duplicates
    } else if (scrambled.length() != scrambled.chars().distinct().count()) {
      System.out.println("duplicate characters, generating random key");
      this.key = "";
      String alpa = "abcdefghijklmnopqrstuvwxyz";
      
      while (alpa.length() > 0) {
        int randIndex = (int) (Math.random() * (alpa.length()));
        this.key += alpa.charAt(randIndex);
        alpa = alpa.substring(0, randIndex) + alpa.substring(randIndex + 1);
      }
      System.out.println("Key = " + this.key);
    } else {
      this.key = scrambled.trim().toLowerCase();
    }
  }

	@Override
	public String encode(String plain) {
    String plainLower = plain.toLowerCase();
    String cipher = "";

    for (int i = 0; i < plain.length(); i++) {
      if (Character.isLetter(plain.charAt(i))) {
        int numericLetter = plainLower.charAt(i) - 'a';
        if (Character.isLowerCase(plain.charAt(i))) {
          cipher += this.key.charAt(numericLetter);
        } else {
          cipher += Character.toUpperCase(this.key.charAt(numericLetter));
        }
      } else {
        cipher += plain.charAt(i);
      }
    }
 
		return cipher;
	}

	@Override
	public String decode(String cipher) {

    String cipherLower = cipher.toLowerCase();
    String plain = "";

    for (int i = 0; i < cipher.length(); i++) {
      if (Character.isLetter(cipher.charAt(i))) {

        char currentChar = cipherLower.charAt(i);
        int correctIndex = this.key.indexOf(currentChar);

        if (Character.isLowerCase(cipher.charAt(i))) {
          plain += (char) (correctIndex + 'a');
        } else {
          plain += Character.toUpperCase((char) (correctIndex + 'a'));
        }
      } else {
        plain += cipher.charAt(i);
      }
    }

		return plain;
	}

	@Override
	public String description() {
		return "has a scrambled list of letters, and swaps them";
	}

	
}
