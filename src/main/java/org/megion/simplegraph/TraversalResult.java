package org.megion.simplegraph;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

/**
 * store result graph traversal
 */
public class TraversalResult<T> {

    /**
     * store association child(vertex key) -> edge value
     * where edge.from is parent for child
     */
    private final Map<TraversalVertex<T>, Edge> parents =
        new HashMap<TraversalVertex<T>, Edge>();

    private final List<TraversalVertex<T>> vertices;

    public TraversalResult(List<TraversalVertex<T>> vertices) {
        this.vertices = vertices;
    }

    public Map<TraversalVertex<T>, Edge> getParents() {
        return parents;
    }

	public List<TraversalVertex<T>> getVertices() {
		return vertices;
	}

}
