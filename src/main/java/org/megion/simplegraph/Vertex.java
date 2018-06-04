package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex
 */
public class Vertex<T> {

    //private int weight = 0;
    private final List<Edge<T>> edges = new ArrayList<Edge<T>>(0);

    public Vertex() {
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }
}
