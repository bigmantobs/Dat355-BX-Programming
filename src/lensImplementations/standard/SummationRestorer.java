package lensImplementations.standard;

import java.util.ArrayList;
import java.util.List;

import framework.AsymmetricLens;
import lensImplementations.IntegerPair;
/**
 * The simplest incarnation of the view-update-scenario
 * Put is determined in implementing classes
 */
public abstract class SummationRestorer extends AsymmetricLens<IntegerPair, Integer> {
	public Integer doGet(IntegerPair s) {
		return s.getFirst() + s.getSecond();
	}
	public String additionalTextOnConsistencyViolation() {
		return "Sum must be exact";
	}
	public List<String> getSourceFieldNames() {
		List<String> result = new ArrayList<>();
		result.add("First");
		result.add("Second");
		return result;
	}
	public List<String> getViewFieldNames() {
		List<String> result = new ArrayList<>();
		result.add("Sum");
		return result;
	}
}

