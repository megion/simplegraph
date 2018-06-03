package org.megion.simplegraph;

/**
 * store information about visit vertex during graph traversal
 */
public class TraversalVertex {

    private boolean processed = false;
    private boolean discovered = false;

    public TraversalVertex() {
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

}
