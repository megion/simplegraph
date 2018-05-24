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
    private final Set<Vertex<T>> vertices;

    public Graph(boolean directed) {
        this.directed = directed;
        vertices = new HashSet<Vertex<T>>();
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

    /**
     * Breadth-first traversal for graph
     */
    public TraversalResult<T> bfsTraversal(Vertex<T> start) {

        initializeTraversal();
        TraversalResult<T> result = new TraversalResult<T>();

        Queue<Vertex<T>> childrenQueue = new LinkedList<Vertex<T>>();
        childrenQueue.add(start);
        Iterator<Vertex<T>> iter = childrenQueue.iterator();
        while (iter.hasNext()) {
            Vertex<T> vert = childrenQueue.remove();
            // 1. processVertexBefore(vert)
            for (Edge<T> edge : vert.getEdges()) {
                Vertex<T> child = edge.getTo();
                //if (!child.isProcessed() || directed) {
                    //// 2. processEdge(edge)
                //}
                
                if(!child.isDiscovered()) {
                    childrenQueue.add(child);
                    child.setDiscovered(true);
                    result.getParents().put(child, edge);
                }
            }
            // 2. processVertexAfter(vert)

        }

        return result;
    }

    public List<Edge<T>> getPath(Vertex<T> start, Vertex<T> end) {
        TraversalResult<T> result = bfsTraversal(start);
        List<Edge<T>> pathEdges = new ArrayList<Edge<T>>();

        Vertex<T> childVertex = end;

        while(true) {
            Edge<T> parentEdge = result.getParents().get(childVertex);
            if(parentEdge ==  null) {
                // finish but start vertex not found in parents for end, so
                // return null
                return null;
            }

            pathEdges.add(parentEdge);
            childVertex = parentEdge.getFrom();
            if(childVertex.equals(start)) {
                // start vertex is found
                Collections.reverse(pathEdges);
                return pathEdges;
            }
        }
    }

    private void initializeTraversal() {
        for (Vertex<T> v : vertices) {
            v.setProcessed(false);
            v.setDiscovered(false);
        }
    }

    public boolean isDirected() {
        return directed;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}
