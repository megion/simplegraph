package org.megion.simplegraph;

import java.util.Map;

/**
 * store result graph traversal
 */
public class TraversalResult<T> {

    /**
     * store association child(vertex key) -> edge value
     * where edge.from is parent for child
     */
    private final Map<TraversalVertex<T>, Edge> parents;

    public TraversalResult(Map<TraversalVertex<T>, Edge> parents) {
        this.parents = parents;
    }

    public Map<TraversalVertex<T>, Edge> getParents() {
        return parents;
    }

}
