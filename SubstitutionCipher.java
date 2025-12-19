import java.util.Arrays;
import java.util.List;
public class SubstitutionCipher implements Cipherable {

	private String key;
	
	public SubstitutionCipher() {
		List alpa = Arrays.asList("abcdefghijklmnopqrstuvwxyz".toCharArray());
		
		while (alpa.size() > 0) {
			int randIndex = (int) (Math.random() * alpa.length);
			this.key += alpa.get(randIndex);
			alpa.remove(randIndex);
		}
	}

	@Override
	public String encode(String plain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decode(String cypher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
