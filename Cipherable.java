
public interface Cipherable {
	public String encode(String plain);
	public String decode(String cypher);

	public String description();
}
