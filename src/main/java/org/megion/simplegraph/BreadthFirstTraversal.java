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
 * Breadth-first graph traversal
 */
public class BreadthFirstTraversal<T> {

    public BreadthFirstTraversal() {
    }

    /**
     */
    public TraversalResult<T> traversal(Graph<T> graph, Vertex<T> start) {

        Map<VertexKey<T>, Vertex<T>> vertices = graph.getCloneVertices();
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
