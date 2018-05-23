package org.megion.simplegraph;

public class VertexAlreadyExistsException extends RuntimeException {

    private final Graph<?>.Vertex vertex;

    public VertexAlreadyExistsException(Graph<?>.Vertex vertex) {
        this.vertex = vertex;
    }

}

