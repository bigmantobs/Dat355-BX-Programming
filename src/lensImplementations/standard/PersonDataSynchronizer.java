package lensImplementations.standard;

import java.util.ArrayList;
import java.util.List;

import framework.SymmetricLens;
import lensImplementations.StringPair;

public class PersonDataSynchronizer extends SymmetricLens<StringPair, StringPair> {
	protected StringPair propagateForward(StringPair a1New, StringPair a2Old) {
		return new StringPair(a1New.getFirst(), a2Old.getSecond());
	}
	public StringPair propagateBackward(StringPair a1Old, StringPair a2New) {
		return new StringPair(a2New.getFirst(), a1Old.getSecond());
	}
	protected boolean isConsistent(StringPair a1, StringPair a2) {
		boolean value = true;
		value = value && // Checking whether a Soviet citizen has birthdate between 1919 and 1990
				(
						a1.getSecond().isBlank()
				||
						a1.getSecond().matches("[0-9]+") && (!a2.getSecond().equals("Soviet Union")|| (Integer.parseInt(a1.getSecond()) < 1992 && Integer.parseInt(a1.getSecond()) > 1921))
				);
		value = value && a1.getFirst().equals(a2.getFirst());
		return value;
	}
	public String getRepresentation() {
		return "Person Data Synchronizer";
	}
	public String additionalTextOnConsistencyViolation() {
		return "Requires equal names and consistent birthdate / birthplace combination";
	}
	
	public List<String> getM1FieldNames() {
		List<String> result = new ArrayList<>();
		result.add("Name");
		result.add("YearOfBirth");
		return result;
	}
	public List<String> getM2FieldNames() {
		List<String> result = new ArrayList<>();
		result.add("Name");
		result.add("PlaceOfBirth");
		return result;
	}
}
