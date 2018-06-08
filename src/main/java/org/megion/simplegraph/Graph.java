package org.megion.simplegraph;

import java.util.List;
import java.util.ArrayList;

/**
 * Simple graph.
 * Thread safe realization
 */
public class Graph<T> {

    private final boolean directed;
    private int edgesCount = 0;

    /**
     * array of vertices
     */
    private final List<Vertex<T>> vertices = new ArrayList<>();

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

    private void insertEdge(Vertex<T> from, int fromIndex, Vertex<T> to,
                            int toIndex, boolean directed) {
        Edge edge = new Edge(fromIndex, toIndex);
        from.addEdge(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(to, toIndex, from, fromIndex, true);
        }
    }

    /**
     * Add edge from -> to
     * Thread-safe method
     */
    public synchronized void addEdge(Vertex<T> from, Vertex<T> to)
    throws VertexNotFoundException {

        int fromIndex = vertices.indexOf(from);
        if (fromIndex < 0) {
            throw new VertexNotFoundException(from);
        }
        int toIndex = vertices.indexOf(to);
        if (toIndex < 0) {
            throw new VertexNotFoundException(to);
        }

        insertEdge(from, fromIndex, to, toIndex, directed);
    }

    /**
     * create traversal verticies set
     * Thread-safe method
     */
    public synchronized List<TraversalVertex<T>> createTraversalVertices() {
        List<TraversalVertex<T>> traversalVertices = new ArrayList<>();

        for (Vertex<T> vert : vertices) {
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
