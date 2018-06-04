package org.megion.simplegraph;

/**
 * Vertex key.
 * Store vertex data and used as idenficator.
 */
public class VertexKey<T> {
    // vertex data
    private final T data;

    public VertexKey(T data) {
        this.data = data;
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
        VertexKey<T> other = (VertexKey<T>) obj;
        return data.equals(other.data);
    }
}
