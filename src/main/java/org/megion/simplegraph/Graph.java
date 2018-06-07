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
     * Thread-safe method
     *
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
        from.addEdge(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(to, from, true);
        }
    }

    /**
     * Add edge from -> to
     * Thread-safe method
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

    /**
     * create traversal verticies set.
     * Thread-safe method
     */
    public synchronized Set<TraversalVertex<T>> createTraversalVertices(
            Graph<T> graph) {
        Set<TraversalVertex<T>> traversalVertices = new HashSet<>(); 

        for(Vertex<T> vert: vertices) {
            traversalVertices.add(new TraversalVertex<T>(vert));
        }
        return traversalVertices;
    }

    public boolean isDirected() {
        return directed;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}
