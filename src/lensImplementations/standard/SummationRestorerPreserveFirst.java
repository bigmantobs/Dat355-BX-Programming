package lensImplementations.standard;

import lensImplementations.IntegerPair;
public class SummationRestorerPreserveFirst extends SummationRestorer {
/**
 * Preserves the first component of the given Integer Pair <s>	
 */
	public IntegerPair doPut(IntegerPair currentSource, Integer newView) {
		return new IntegerPair(currentSource.getFirst(), newView - currentSource.getFirst());
	}
	public String getRepresentation() {
		return "Sum Consistency: \"put\" preserves first summand";
	}
}
