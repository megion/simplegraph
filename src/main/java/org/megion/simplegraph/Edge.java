package org.megion.simplegraph;

/**
 * Edge
 */
public class Edge<T> {
    private final VertexKey<T> from;
    private final VertexKey<T> to;

    public Edge(VertexKey<T> from, VertexKey<T> to) {
        this.from = from;
        this.to = to;
    }

    public VertexKey<T> getFrom() {
        return from;
    }

    public VertexKey<T> getTo() {
        return to;
    }

}
