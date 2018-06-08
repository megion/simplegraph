package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Vertex
 */
public class Vertex<T> {

    // vertex data
    private final T data;

    //private int weight = 0;
    private final List<Edge> edges;

    public Vertex(T data) {
        this.data = data;
        this.edges = new ArrayList<Edge>(0);
    }

    /**
     * Copy constuctor
     */
    public Vertex(Vertex<T> other) {
        this.data = other.data;
        this.edges = new ArrayList<Edge>(other.edges);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Iterator<Edge> getEdgesIterator() {
        return edges.iterator();
    }

    public T getData() {
        return data;
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
