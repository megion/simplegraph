package org.megion.simplegraph;

import java.util.Map;
import java.util.HashMap;

/**
 * Simple graph.
 * Thread safe realization
 */
public class Graph<T> {

    private final boolean directed;
    private int edgesCount = 0;
    // vertices
    private final Map<VertexKey<T>, Vertex<T>> vertices = new HashMap<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    /**
     * Add vertex to the graph
     * return new VertexKey
     */
    public synchronized VertexKey<T> addVertex(T data)
    throws VertexAlreadyExistsException {
        VertexKey<T> key = new VertexKey<>(data);

        // check if vertex has been added.
        if (vertices.containsKey(key)) {
            throw new VertexAlreadyExistsException(key);
        }

        Vertex<T> vertex = new Vertex<>();
        vertices.put(key, vertex);
        return key;
    }

    private void insertEdge(VertexKey<T> keyFrom, Vertex<T> vertexFrom,
                            VertexKey<T> keyTo, Vertex<T> vertexTo,
                            boolean directed) {
        Edge<T> edge = new Edge<>(keyFrom, keyTo);
        vertexFrom.getEdges().add(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(keyTo, vertexTo, keyFrom, vertexFrom, true);
        }
    }

    /**
     * Add edge from -> to
     */
    public synchronized void addEdge(VertexKey<T> from, VertexKey<T> to)
    throws VertexNotFoundException {
        Vertex<T> vertexFrom = vertices.get(from);
        if (vertexFrom == null) {
            throw new VertexNotFoundException(vertexFrom);
        }
        Vertex<T> vertexTo = vertices.get(to);
        if (vertexTo == null) {
            throw new VertexNotFoundException(vertexTo);
        }

        insertEdge(from, vertexFrom, to, vertexTo, directed);
    }

    public synchronized Map<VertexKey<T>, Vertex<T>> getCloneVertices() {
        Map<VertexKey<T>, Vertex<T>> clone = new HashMap<>(vertices);
        return clone;
    }

    public boolean isDirected() {
        return directed;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}
