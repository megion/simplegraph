package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex
 */
public class Vertex<T> {

    // vertex data
    private final T data;

    //private int weight = 0;
    private final List<Edge<T>> edges = new ArrayList<Edge<T>>(0);

    public Vertex(T data) {
        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        Vertex<T> other = (Vertex<T>) obj;
        return data.equals(other.data);
    }
}
