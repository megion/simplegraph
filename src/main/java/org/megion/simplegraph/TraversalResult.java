package org.megion.simplegraph;

import java.util.Map;
import java.util.HashMap;

/**
 * store result graph traversal
 */
public class TraversalResult<T> {

    private final Map<Vertex<T>, Edge<T>> parents =
        new HashMap<Vertex<T>, Edge<T>>();

    public TraversalResult() {
    }

	public Map<Vertex<T>, Edge<T>> getParents() {
		return parents;
	}

}
