package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

/**
 * Breadth-first graph traversal
 */
public class BreadthFirstTraversal<T> {

    public BreadthFirstTraversal() {
    }

    private Set<TraversalVertex<T>> createTraversalVertices(
            Graph<T> graph) {
        Set<TraversalVertex<T>> traversalVertices = new HashSet<>(); 
        Set<Vertex<T>> vertices = graph.getCloneVertices();

        for(Vertex<T> vert: vertices) {
            traversalVertices.add(new TraversalVertex<T>(vert));
        }
        return traversalVertices;
    }

    /**
     */
    public TraversalResult<T> traversal(Graph<T> graph, Vertex<T> startVertex) {

        Set<TraversalVertex<T>> vertices = createTraversalVertices(graph);
        TraversalResult<T> result = new TraversalResult<T>();

        Queue<TraversalVertex<T>> childrenQueue = new LinkedList<>();
        TraversalVertex<T> start = new TraversalVertex<>(startVertex);
        start.setDiscovered(true);
        childrenQueue.add(start);

        Iterator<TraversalVertex<T>> iter = childrenQueue.iterator();
        while (iter.hasNext()) {
            TraversalVertex<T> vert = childrenQueue.remove();
            vert.setProcessed(true);
            // 1. processVertexBefore(vert)
            Iterator<Edge<T>> edges = vert.getVertex().getEdgesIterator();
            while (edges.hasNext()) {
                Edge<T> edge = edges.next(); 
                Vertex<T> child = edge.getTo();
                //if (!child.isProcessed() || directed) {
                //// 2. processEdge(edge)
                //}

                if (!child.isDiscovered()) {
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

        while (true) {
            Edge<T> parentEdge = result.getParents().get(childVertex);
            if (parentEdge ==  null) {
                // finish but start vertex not found in parents for end, so
                // return null
                return null;
            }

            pathEdges.add(parentEdge);
            childVertex = parentEdge.getFrom();
            if (childVertex.equals(start)) {
                // start vertex is found
                Collections.reverse(pathEdges);
                return pathEdges;
            }
        }
    }

    
}
