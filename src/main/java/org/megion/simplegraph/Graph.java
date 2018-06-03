package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/**
 * Simple graph
 */
public class Graph<T> {

    private final boolean directed;
    private int edgesCount = 0;
    // vertices
    private final Set<Vertex<T>> vertices = new HashSet<Vertex<T>>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    /**
     * Add vertex to the graph
     * return new Vertex
     */
    public Vertex<T> addVertex(T nodeData) throws VertexAlreadyExistsException {
        Vertex<T> node = new Vertex<T>(nodeData);

        // check if vertex has been added.
        if (vertices.contains(node)) {
            throw new VertexAlreadyExistsException(node);
        }

        vertices.add(node);
        return node;
    }

    private void insertEdge(Vertex<T> from, Vertex<T> to, boolean directed) {
        Edge<T> edge = new Edge<T>(from, to);
        from.getEdges().add(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(to, from, true);
        }
    }

    /**
     * Add edge from node1 to node2
     */
    public void addEdge(Vertex<T> node1, Vertex<T> node2)
    throws VertexNotFoundException {
        if (!vertices.contains(node1)) {
            throw new VertexNotFoundException(node1);
        }
        if (!vertices.contains(node2)) {
            throw new VertexNotFoundException(node2);
        }

        insertEdge(node1, node2, directed);
    }

    public Map<TraversalVertex> initializeTraversal() {
        Map<Vertex, TraversalVertex> traversalVertices = new HashMap<>();
        for (Vertex<T> v: vertices) {
            traversalVertices.add(new TraversalVertex());
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
