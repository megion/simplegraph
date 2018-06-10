package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Breadth-first graph traversal
 */
public class BreadthFirstTraversal<T> {

    public BreadthFirstTraversal() {
    }

    /**
     */
    public TraversalResult<T> traversal(Graph<T> graph, Vertex<T> startVertex) {

        List<TraversalVertex<T>> vertices = graph.createTraversalVertices();
        TraversalResult<T> result = new TraversalResult<T>(vertices);

        Queue<TraversalVertex<T>> childrenQueue = new LinkedList<>();
        TraversalVertex<T> start = new TraversalVertex<>(startVertex);
        start.setDiscovered(true);
        childrenQueue.add(start);

        Iterator<TraversalVertex<T>> iter = childrenQueue.iterator();
        while (iter.hasNext()) {
            TraversalVertex<T> vert = childrenQueue.remove();
            vert.setProcessed(true);
            // 1. processVertexBefore(vert)
            Iterator<Edge> edges = vert.getVertex().getEdgesIterator();
            while (edges.hasNext()) {
                Edge edge = edges.next();
                TraversalVertex<T> child = vertices.get(edge.getTo());
                //if (!child.isProcessed() || directed) {
                //// 2. processEdge(edge)
                //}

                if (!child.isDiscovered()) {
                    childrenQueue.add(child);
                    child.setDiscovered(true);
                    // edge.from is parent vertex for child
                    result.getParents().put(child, edge);
                }
            }
            // 3. processVertexAfter(vert)
        }

        return result;
    }

    public List<Vertex<T>> getPath(Graph<T> graph, Vertex<T> start,
                                   Vertex<T> end) {
        /*
         * for get the shortest path we should do breadth-first graph traversal
         * begin from `start` vertex (root tree)
         */
        TraversalResult<T> result = traversal(graph, start);
        List<TraversalVertex<T>> vertices = result.getVertices();
        List<Vertex<T>> pathEdges = new ArrayList<>();

        TraversalVertex<T> startVertex = new TraversalVertex<>(start);
        TraversalVertex<T> childVertex = new TraversalVertex<>(end);
        pathEdges.add(childVertex.getVertex());

        /*
         * iterate by parents from end to start
         */
        while (true) {
            Edge parentEdge = result.getParents().get(childVertex);
            if (parentEdge == null) {
                // parent not found in parents, so return null
                return null;
            }

            childVertex = vertices.get(parentEdge.getFrom());
            pathEdges.add(childVertex.getVertex());
            if (childVertex.equals(startVertex)) {
                // start vertex is found
                Collections.reverse(pathEdges);
                return pathEdges;
            }
        }
    }


}
