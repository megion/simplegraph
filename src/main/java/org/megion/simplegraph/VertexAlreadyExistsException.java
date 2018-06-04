package org.megion.simplegraph;

public class VertexAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1864633719888188147L;

    private final VertexKey<?> vertexKey;

    public VertexAlreadyExistsException(VertexKey<?> vertexKey) {
        this.vertexKey = vertexKey;
    }

    public VertexKey<?> getVertexKey() {
        return vertexKey;
    }

}
