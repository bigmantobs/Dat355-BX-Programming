package lensImplementations;
/**
 * A factory class which instantiates 
 * - an asymmetric lens with source = Int x Int and View = Int
 * - a symmetric lens with M1 = String x String  
 */

import framework.AsymmetricLens;
import framework.SymmetricLens;
import lensImplementations.standard.PersonDataSynchronizer;
import lensImplementations.standard.SummationRestorerPreserveFirst;
import lensImplementations.student.SummationRestorerPreserveSecond;

public class LensFactory {
	public AsymmetricLens<IntegerPair, Integer> createAsymmetricLens(){
		//return new SummationRestorerPreserveFirst();
		return new SummationRestorerPreserveSecond(); 
		// Exercise 1b): return new SummationRestorerPreserveSecond(); 
	}
	
	// M1 consists of String Pairs (a, b) of title and author of books
	// M2 has string pairs (c, d) of complete book description and book type. 
	// We assume the existence of special authors "A", "B", and "C" as well as book types "X", "Y", and "Z"
	// ((a,b),(c,d)) are consistent if the following two conditions are satisfied
	// 1. if b not empty, then c = b + ":" + a, otherwise c = a.
	// 2. Author "A" only writes books of types "X" and "Y", author "B" only writes books of type "Z",
	// and author "C" only writes books of type "X" and "Z"
	public SymmetricLens<StringPair, StringPair> createSymmetricLens(){
		return new PersonDataSynchronizer();
		// Exercise 1c): Instantiate and return your new class instead of the current returned object
		// return new SymmetricLensX;
	}
	
	public class SymmetricLensX {
		
		//Move from M1 to M2
		// (a, b) --> (c, d)
		//if b not empty then c = b + ":" + a, otherwise c = a.
		// Author "A" --> "X" and "Y"
		// Author "B" --> "Z"
		// Author "C" --> "X" and "Z"
		public StringPair propagateForward(StringPair s1) {
			//return null;
			String c = "Description";
			String d = "BookType Unknown";
			if ((s1.getSecond()) != null) {
				c = (s1.getSecond() + ":" + s1.getFirst());
			}
			else {
				c = s1.getFirst();
			}
			return new StringPair(c, d);
		}
		
		//Move from M2 to M1
		public StringPair propagateBackward(StringPair s1) {
			//return null;
			String a = "Title";
			String b = "Author Unknown";
			String c = s1.getFirst();
			String d = s1.getSecond();
			
			if (c.contains(":")) {
				String[] result = c.split(":");
				return new StringPair(result[1], result[0]);
			}
			else {
				return new StringPair(c, b);
			}
		}
		
		//Check consistency
		public boolean isConsistent(StringPair s1, StringPair s2) {
			if (s1.getSecond() == "A") {
				if ((s2.getSecond() == "X") || (s2.getSecond() == "Y")) {
					return true;
				}
				else {
					// author inconsistency
					return false;
				}
			}
			if (s1.getSecond() == "B") {
				if (s2.getSecond() == "Z") {
					return true;
				}
				else {
					// author inconsistency
					return false;
				}
		}
			if (s1.getSecond() == "C") {
				if ((s2.getSecond() == "X") || (s2.getSecond() == "Z")) {
					return true;
				}
				else {
					// author inconsistency
					return false;
				}
			}
			else {
				// no special author case
				return true; 
			}
	}
}
}
