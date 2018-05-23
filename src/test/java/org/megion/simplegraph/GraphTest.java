package org.megion.simplegraph;

import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void canAddVertex() {
        Graph<Integer> graph = new Graph<Integer>(true);
        assertEquals(0, graph.getEdgesCount());

        graph.addVertex(2);
        assertEquals(0, graph.getEdgesCount());

        graph.addVertex(1);
        assertEquals(0, graph.getEdgesCount());
    }
}
