package org.megion.simplegraph;

import java.util.Set;
import java.util.HashSet;

/**
 * Simple graph.
 * Thread safe realization
 */
public class Graph<T> {

    private final boolean directed;
    private int edgesCount = 0;
    // vertices
    private final Set<Vertex<T>> vertices = new HashSet<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    /**
     * Add vertex to the graph
     * return new VertexKey
     */
    public synchronized Vertex<T> addVertex(T data)
    throws VertexAlreadyExistsException {
        Vertex<T> vertex = new Vertex<>(data);

        // check if vertex has been added.
        if (vertices.contains(vertex)) {
            throw new VertexAlreadyExistsException(vertex);
        }

        vertices.add(vertex);
        return vertex;
    }

    private void insertEdge(Vertex<T> from, Vertex<T> to, boolean directed) {
        Edge<T> edge = new Edge<>(from, to);
        from.getEdges().add(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(to, from, true);
        }
    }

    /**
     * Add edge from -> to
     */
    public synchronized void addEdge(Vertex<T> from, Vertex<T> to)
    throws VertexNotFoundException {
        if (!vertices.contains(from)) {
            throw new VertexNotFoundException(from);
        }
        if (!vertices.contains(to)) {
            throw new VertexNotFoundException(to);
        }

        insertEdge(from, to, directed);
    }

    public synchronized Set<Vertex<T>> getCloneVertices() {
        Set<Vertex<T>> clone = new HashSet<>(vertices);
        return clone;
    }

    public boolean isDirected() {
        return directed;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}
