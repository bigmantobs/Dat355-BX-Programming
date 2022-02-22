package lensImplementations.student;

import lensImplementations.IntegerPair;
import lensImplementations.standard.SummationRestorer;

public class SummationRestorerPreserveSecond extends SummationRestorer {
/**
 * Preserves the second component of the given Integer Pair <s>	
 */
	public IntegerPair doPut(IntegerPair currentSource, Integer newView) {
		//return new IntegerPair(0, 0);
		// TODO: Implement correct "put" here 
		return new IntegerPair(currentSource.getSecond(), newView - currentSource.getSecond());
	}
	public String getRepresentation() {
		return "Restorer's \"put\" preserves the second summand";
	}
}
