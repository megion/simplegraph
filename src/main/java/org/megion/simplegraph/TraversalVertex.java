package org.megion.simplegraph;

/**
 * store information about visit vertex during graph traversal
 */
public class TraversalVertex<T> {

    /**
     * set to TRUE if vertex traversal is finished 
     */
    private boolean processed = false;
    /**
     * set to TRUE when vertex is visited during graph traversal
     */
    private boolean discovered = false;

    private final Vertex<T> vertex;

    public TraversalVertex(Vertex<T> vertex) {
        // create clone
        this.vertex = new Vertex<>(vertex);
    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        TraversalVertex<T> other = (TraversalVertex<T>) obj;
        return vertex.equals(other.vertex);
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

	public Vertex<T> getVertex() {
		return vertex;
	}
}
