package tdas;
// edge

import java.util.Comparator;

public class Graph_AM<V, E> {

    private V[] vertices;
    private int[][] adjacencyMatrix;
    private E[][] edgesMetadata;
    private Comparator<V> cmpVertices;
    private Comparator<E> cmpEdges;
    private int effectiveSize;
    private int capacity;
    private boolean isDirected;

    public Graph_AM(Comparator<V> cmpVertices, Comparator<E> cmpEdges) {
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        
//        this.vertices = 100;
//        this.adjacencyMatrix = new int[10][10];
//        this.edgesMetadata = new E[10][10];
    }
    
    
    public boolean addVertex(V v1){
        for(int i = 0 ; i < effectiveSize; i ++){
            this.adjacencyMatrix[effectiveSize][i] = 0;
            this.edgesMetadata[effectiveSize][i] = null;
        }
        this.effectiveSize++;
        return true;
    }
    
    
    
    public boolean connect(V vertex1, V vertex2, int weight, E data) {
        int idx1 = findVertex(vertex1);
        int idx2 = findVertex(vertex2);
        if (idx1 != -1 && idx2 != -1) {
            this.adjacencyMatrix[idx1][idx2] = weight;
            this.edgesMetadata[idx1][idx2] = data;
            if (!this.isDirected) {
                this.adjacencyMatrix[idx2][idx1] = weight;
                this.edgesMetadata[idx2][idx1] = data;
            }
            return true;
        }
        return false;
    }
    
    
    
    private int findVertex(V vertex1) {
        for (int i = 0; i < this.effectiveSize; i++) {
            if (cmpVertices.compare(vertex1, vertices[i]) == 0)
                return i;
        }
        return -1;
    }
}
