
package tdas;

import java.util.LinkedList;

public class Vertex<V,E> {
    private V content;
    private LinkedList<Edge<E,V>> edges;
    private boolean visited;

    public Vertex (V content) {
        this.content = content;
        this.edges = null;
        this.visited = false;
    }
        
    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public LinkedList<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge<E, V>> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean isVisited) {
        this.visited = isVisited;
    }
    

    @Override
    public String toString() {
        return "" + content ;
    }
    
    
}
