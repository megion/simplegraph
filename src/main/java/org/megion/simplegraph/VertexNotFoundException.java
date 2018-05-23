package org.megion.simplegraph;

public class VertexNotFoundException extends RuntimeException {

    private final Graph<?>.Vertex vertex;

    public VertexNotFoundException(Graph<?>.Vertex vertex) {
        this.vertex = vertex;
    }

}

