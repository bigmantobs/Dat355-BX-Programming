package framework;

import java.util.List;

/**
 * A symmetric lens with the usual RForward/Backward 
 * @param <M1> The Left Space
 * @param <M2> The Right Space
 */
public abstract class SymmetricLens<M1,M2> extends Lens<M1, M2> {
	protected abstract M2 propagateForward(M1 a1New, M2 a2Old);
	protected abstract M1 propagateBackward(M1 a1Old, M2 a2New);
/**
 * RForward doing axiom check	
 * @throws Exception 
 */	
	public M2 RForward(M1 a1, M2 a2) throws Exception {
		M2 result = this.propagateForward(a1, a2);
		this.axiomCheckAtForwardPropagation(a1, a2, result);
		return result;
	}
/**
 * RBackward doing axiom check	 
 * @throws Exception 
 */	
	public M1 RBackward(M1 a1, M2 a2) throws Exception {
		M1 result = this.propagateBackward(a1, a2);
		this.axiomCheckAtBackwardPropagation(a1, a2, result);
		return result;
	};
/**	
 * Provides the field names of models in M1
 */
	public abstract List<String> getM1FieldNames();
/**	
 * Provides the field names of models in M2
 */	
	public abstract List<String> getM2FieldNames();
}
