Java graph library
==================

Clone project, build and run tests:

    $ git clone https://github.com/megion/simplegraph.git
    $ cd simplegraph
    $ mvn package

Examples:
=========

* Writing csv files:

```java
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

        List<Edge<Integer>> path = graph.getPath(v1, v4);
```
