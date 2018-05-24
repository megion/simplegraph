package org.megion.simplegraph;

public class VertexNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 138775040495766726L;

	private final Vertex<?> vertex;

    public VertexNotFoundException(Vertex<?> vertex) {
        this.vertex = vertex;
    }

	public Vertex<?> getVertex() {
		return vertex;
	}

}

