package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * Breadth-first graph traversal
 */
public class BreadthFirstTraversal<T> {

    public BreadthFirstTraversal() {
    }

    /**
     * get shortes path start -> end
     */
    public List<Vertex<T>> getPath(Graph<T> graph,
                                   Vertex<T> start,
                                   Vertex<T> end) {
        /*
         * for get the shortest path we should do breadth-first graph traversal
         * begin from `start` vertex (root tree)
         */
        List<TraversalVertex<T>> vertices = graph.createTraversalVertices();
        TraversalResult<T> result = traversal(vertices, start);
        Map<TraversalVertex<T>, Edge> parents = result.getParents();
        List<Vertex<T>> pathEdges = new ArrayList<>();

        TraversalVertex<T> startVertex = new TraversalVertex<>(start);
        TraversalVertex<T> childVertex = new TraversalVertex<>(end);
        pathEdges.add(childVertex.getVertex());

        /*
         * iterate by parents from end to start
         */
        while (true) {
            Edge parentEdge = parents.get(childVertex);
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

    /**
     * search connected componets
     */
    public int connectedComponents(Graph<T> graph) {
        // count connected componenets
        return 0;
    }

    /**
     * realization breadth-first traversal
     */
    private TraversalResult<T> traversal(List<TraversalVertex<T>> vertices,
                                        Vertex<T> startVertex) {

        Queue<TraversalVertex<T>> childrenQueue = new LinkedList<>();
        TraversalVertex<T> start = new TraversalVertex<>(startVertex);
        start.setDiscovered(true);
        childrenQueue.add(start);

        Map<TraversalVertex<T>, Edge> parents = new HashMap<>();

        Iterator<TraversalVertex<T>> iter = childrenQueue.iterator();
        while (iter.hasNext()) {
            TraversalVertex<T> vert = childrenQueue.remove();
            // 1. process_vertex_early(vert)
            vert.setProcessed(true);
            Iterator<Edge> edges = vert.getVertex().getEdgesIterator();
            while (edges.hasNext()) {
                Edge edge = edges.next();
                TraversalVertex<T> child = vertices.get(edge.getTo());
                //if (!child.isProcessed() || directed) {
                //// 2. process_edge(vert, edge)
                //}

                if (!child.isDiscovered()) {
                    childrenQueue.add(child);
                    child.setDiscovered(true);
                    // edge.from is parent vertex for child
                    parents.put(child, edge);
                }
            }
            // 3. process_vertex_late(vert)
        }

        TraversalResult<T> result = new TraversalResult<>(parents);
        return result;
    }


}
