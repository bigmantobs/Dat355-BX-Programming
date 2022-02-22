package framework;

import java.util.List;

/**
 * An asymmetric lens with the usual get/put 
 * @param <S> The Source Space
 * @param <V> The View Space
 */
public abstract class AsymmetricLens<S,V> extends Lens<S, V> {
/**
 * The pure implementations (without axiom check)	
 */
	protected abstract V doGet(S s);
	protected abstract S doPut(S currentSource, V newView);
/**
 * The consistency relation	
 */
	protected final boolean isConsistent(S s, V v) {
		return this.get(s).equals(v);
	}
/**	
 * The actual methods with axiom checks
 */
	public final V get(S s) {
		return this.doGet(s);
	}
	public final S put(S s, V v) throws Exception {
		S result = this.doPut(s, v);
		this.axiomCheckAtBackwardPropagation(s, v, result);
		return result;
	}
	public abstract List<String> getSourceFieldNames();
	public abstract List<String> getViewFieldNames();
}
