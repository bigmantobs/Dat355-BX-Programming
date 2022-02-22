package lensImplementations;

public class StringPair {
	private final String first; 
	private final String second;
	public StringPair(String first, String second) {
		super();
		this.first = first;
		this.second = second;
	}
	public String getFirst() {
		return this.first;
	}
	public String getSecond() {
		return this.second;
	}
	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}
	public boolean equals(Object o) {
		if(!(o instanceof StringPair)) return false;
		StringPair other = (StringPair)o;
		return other.first.equals(this.first) && other.second.equals(this.second);
	}
}
