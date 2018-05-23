package org.megion.simplegraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Simple graph
 */
public class Graph<T> {

    /**
     * Edge
     */
    public class Edge {
        private final Vertex vertex;

        public Edge(Vertex v) {
            this.vertex = v;
        }

        public Vertex getVertex() {
            return vertex;
        }
    }

    /**
     * Vertex
     */
    public class Vertex {
        // node data
        private final T data;
        private int weight = 0;
        private final List<Edge> edges;

        public Vertex(T data) {
            this.data = data;
            this.edges = new ArrayList<Edge>(0);
        }

        public T getData() {
            return data;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            return other.data.equals(data);
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private final boolean directed;
    private int edgesCount = 0;
    // vertices
    private final Set<Vertex> vertices;

    public Graph(boolean directed) {
        this.directed = directed;
        vertices = new HashSet<Vertex>();
    }

    /**
     * Add vertex to the graph
     * return new RowEdges with one added edge
     * If return null then vertex with specified nodeData already has been
     * added to the graph
     */
    public Vertex addVertex(T nodeData) throws VertexAlreadyExistsException {
        Vertex node = new Vertex(nodeData);

        // check if vertex has been added.
        if (vertices.contains(node)) {
            throw new VertexAlreadyExistsException(node);
        }

        vertices.add(node);
        return node;
    }

    private void insertEdge(Vertex node1, Vertex node2, boolean directed) {
        Edge edge = new Edge(node2);
        node1.getEdges().add(edge);

        if (directed) {
            edgesCount++;
        } else {
            insertEdge(node1, node2, true);
        }
    }

    /**
     * Add edge from node1 to node2
     */
    public void addEdge(Vertex node1, Vertex node2)
    throws VertexNotFoundException {
        if (!vertices.contains(node1)) {
            throw new VertexNotFoundException(node1);
        }
        if (!vertices.contains(node2)) {
            throw new VertexNotFoundException(node2);
        }

        insertEdge(node1, node2, directed);
    }

    public boolean isDirected() {
        return directed;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}
