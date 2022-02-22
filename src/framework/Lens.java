package framework;
/**
 * The abstract type comprising all types of (binary) lenses
 * keeping in sync elements of model spaces M1 and M2
 */
public abstract class Lens<M1,M2> {	
	protected abstract boolean isConsistent(M1 a1, M2 a2);	
	public abstract String getRepresentation(); 
	protected void axiomCheckAtBackwardPropagation(M1 a1, M2 a2, M1 result) throws Exception{
		if(!this.isConsistent(result, a2)) throw new Exception(this.correctnessViolated(result, a2));
		if(this.isConsistent(a1, a2) && !result.equals(a1)) throw new Exception(this.hippocraticnessViolated(result, a2));
	}
	protected void axiomCheckAtForwardPropagation(M1 a1, M2 a2, M2 result) throws Exception {
		if(!this.isConsistent(a1, result)) throw new Exception(this.correctnessViolated(a1, result));
		if(this.isConsistent(a1, a2) && !result.equals(a2)) throw new Exception(this.hippocraticnessViolated(a1, result));
	}
	
// =================== Private Part =============================
	private static final String hippocraticness = "Hippocraticness";
	private static final String correctness = "Correctness";
	protected String correctnessViolated(M1 a1, M2 a2) {
		return correctness + " " + this.axiomViolatedText(a1, a2);
	}
	protected String hippocraticnessViolated(M1 a1, M2 a2) {
		return hippocraticness + " " + this.axiomViolatedText(a1, a2);
	}
	protected String axiomViolatedText(M1 a1, M2 a2) {
		return "Axiom violated: (A1,A2) = (" + a1.toString() + ", " + a2.toString() + ")!";
	}
}
