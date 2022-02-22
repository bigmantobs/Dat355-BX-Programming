package lensImplementations;

public class IntegerPair {
	private final Integer first; 
	private final Integer second;
	public IntegerPair(Integer first, Integer second) {
		super();
		this.first = first;
		this.second = second;
	}
	public Integer getFirst() {
		return this.first;
	}
	public Integer getSecond() {
		return this.second;
	}
	public String toString() {
		return "(" + this.first.toString() + ", " + this.second.toString() + ")";
	}
	public boolean equals(Object o) {
		if(!(o instanceof IntegerPair)) return false;
		IntegerPair other = (IntegerPair)o;
		return this.first.equals(other.first) && this.second.equals(other.second);
	}
}
