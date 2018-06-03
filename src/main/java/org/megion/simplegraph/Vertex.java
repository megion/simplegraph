package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex
 */
public class Vertex<T> {
    // node data
    private final T data;
    //private int weight = 0;
    private final List<Edge<T>> edges;

    public Vertex(T data) {
        this.data = data;
        this.edges = new ArrayList<Edge<T>>(0);
    }

    public T getData() {
        return data;
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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex<T> other = (Vertex<T>) obj;
        return other.data.equals(data);
    }
}
