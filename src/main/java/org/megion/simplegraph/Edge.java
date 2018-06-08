package org.megion.simplegraph;

/**
 * Edge
 */
public class Edge {
    /**
     * from vertex index into vertices array 
     */
    private final int from;

    /**
     * to vertex index into vertices array 
     */
    private final int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

}
