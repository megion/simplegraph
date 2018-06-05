package org.megion.simplegraph;

public class VertexAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1864633719888188147L;

    private final Vertex<?> vertex;

    public VertexAlreadyExistsException(Vertex<?> vertex) {
        this.vertex = vertex;
    }

    public Vertex<?> getVertex() {
        return vertex;
    }

}
