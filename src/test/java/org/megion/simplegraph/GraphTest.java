package org.megion.simplegraph;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void canAddVertex() {
        Graph<Integer> graph = new Graph<Integer>(true);
        assertEquals(0, graph.getEdgesCount());

        Vertex<Integer> v1 = graph.addVertex(2);
        assertEquals(Integer.valueOf(2), v1.getData());
        assertEquals(0, graph.getEdgesCount());

        Vertex<Integer> v2 = graph.addVertex(1);
        assertEquals(Integer.valueOf(1), v2.getData());
        assertEquals(0, graph.getEdgesCount());
    }

    @Test
    public void canGetPath() {
        /**
         * example graph from Stiven Skiena book
         * see head 5.6
         */
        Graph<Integer> graph = new Graph<Integer>(true);
        Vertex<Integer> v1 = graph.addVertex(1);
        Vertex<Integer> v2 = graph.addVertex(2);
        Vertex<Integer> v3 = graph.addVertex(3);
        Vertex<Integer> v4 = graph.addVertex(4);
        Vertex<Integer> v5 = graph.addVertex(5);
        Vertex<Integer> v6 = graph.addVertex(6);

        graph.addEdge(v1, v2);
        graph.addEdge(v1, v5);
        graph.addEdge(v1, v6);
        graph.addEdge(v2, v3);
        graph.addEdge(v2, v5);
        graph.addEdge(v3, v4);
        graph.addEdge(v5, v4);

        BreadthFirstTraversal<Integer> bfs = new BreadthFirstTraversal<>();
        List<Vertex<Integer>> path = bfs.getPath(graph, v1, v4);
        assertEquals(3, path.size());
        assertEquals(v1, path.get(0));
        assertEquals(v5, path.get(1));
        assertEquals(v4, path.get(2));

        path = bfs.getPath(graph, v2, v6);
        assertNull(path);
    }
}
