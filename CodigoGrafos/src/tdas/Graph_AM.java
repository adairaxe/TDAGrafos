package tdas;
// edge

import Comparators.CompratorPerson;
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
    
    
    
    public Graph_AM(Comparator<V> cmpVertices, Comparator<E> cmpEdges, boolean directed) {
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        this.isDirected = directed;
        this.capacity = 100;
        this.vertices = (V[]) (new Object[capacity]);
        this.adjacencyMatrix = new int[capacity][capacity];
        this.edgesMetadata = (E[][]) (new Object[capacity][capacity]);
        
    }
    
    
    
    public boolean addVertex(V content){
        if (content == null) {
            return false;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        
        vertices[effectiveSize]=content;
        effectiveSize++;
        return true;
    }
    
    
    
    private int findVertex(V vertex1) {
        for (int i = 0; i < this.effectiveSize; i++) {
            if (cmpVertices.compare(vertex1, vertices[i]) == 1)
                return i;
        }
        return -1;
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
    
    
    
    public boolean disconnect(V vertex1, V vertex2) {
        int idx1 = findVertex(vertex1);
        int idx2 = findVertex(vertex2);
        if (idx1 != -1 && idx2 != -1) {
            this.adjacencyMatrix[idx1][idx2] = 0;
            this.edgesMetadata[idx1][idx2] = null;
            if (!this.isDirected) {
                this.adjacencyMatrix[idx2][idx1] = 0;
                this.edgesMetadata[idx2][idx1] = null;
            }
            return true;
        }
        return false;
    }
    
        
    private void addCapacity() {
        V[] copy =          (V[]) (new Object[capacity*2]);
        int[][] adjTemp =   new int[capacity*2][capacity*2];
        E[][] edgTemp =     (E[][]) (new Object[capacity*2][capacity*2]);
        
        for (int i = 0; i < this.capacity; i++) {
            copy[i] = this.vertices[i];
            
            for(int j = 0; j < capacity; j++){
                adjTemp[i][j] = this.adjacencyMatrix[i][j];
                edgTemp[i][j] = this.edgesMetadata[i][j];
            }
        }
        vertices = copy;
        adjacencyMatrix = adjTemp;
        edgesMetadata = edgTemp;
        capacity = capacity * 2;
    }
    
    
    
    @Override
    public String toString() {
        String strAdayance = "";
        String strMetadata = "";
        for(int i = 0 ; i < this.effectiveSize ; i++){
            for(int j = 0 ; j < this.effectiveSize ; j++){
                strAdayance = strAdayance + this.adjacencyMatrix[i][j] + " ";
                strMetadata = strMetadata + this.edgesMetadata[i][j] + " ";
            }
            strAdayance = strAdayance + "\n";
            strMetadata = strMetadata + "\n";
            
        }
        return "Matriz de adyacencia" + "\n" + strAdayance + "\n"
               + "Matriz de metadata"+ "\n" + strMetadata;
    }   
    
    
     public static Graph_AM<Person,Integer> buildGraphOne(){
        CompratorPerson cmpPersona = new CompratorPerson();
        Graph_AM<Person, Integer> grafo1 = new Graph_AM<>(cmpPersona, (x,y) -> {return x-y;} , true);
        
        Person Alice = new Person("Alice", 32, "Ingeniero",    "Guayaquil" );
        Person Bob = new Person("Bob",   28, "Chef",         "Guayaquil" );
        Person Carol = new Person("Carol", 27, "Contadora",    "Quito"     );
        Person Dave = new Person("Dave",  31, "Investigador", "Cuenca"    );
        
        grafo1.addVertex(Alice);
        grafo1.addVertex(Bob);
        grafo1.addVertex(Carol);
        grafo1.addVertex(Dave);
        
        grafo1.connect(Alice, Dave, 3,   null);
        grafo1.connect(Dave, Carol, 2,   null);
        grafo1.connect(Carol, Bob, 1,   null);
        grafo1.connect(Bob, Alice, 3,   null);
        grafo1.connect(Carol, Alice, 2,   null);
        grafo1.connect(Carol, Dave, 4,   null);
        
        return grafo1;
    }
    
    
}
