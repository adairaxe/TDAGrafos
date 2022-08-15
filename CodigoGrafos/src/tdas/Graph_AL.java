package tdas;

import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import tdas.Edge;
import tdas.Vertex;

public class Graph_AL<V, E> {

    private LinkedList<Vertex<V, E>> vertices;
    private Comparator<V> cmpVertices;
    private Comparator<E> cmpEdges;
    private boolean isDirected;
    
    
    
    public Graph_AL(LinkedList<Vertex<V, E>> vertices, Comparator<V> cmpVertices, Comparator<E> cmpEdges, boolean isDirected) {
        this.vertices = vertices;
        this.cmpVertices = cmpVertices;
        this.isDirected = isDirected;
        this.cmpEdges = cmpEdges;
    }
    
    
    
    public Graph_AL(Comparator<V> cmpVertices, Comparator<E> cmpEdges, boolean isDirected) {
        this.vertices = new LinkedList();
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        this.isDirected = isDirected;
    }
    
    
    
    public boolean addVertex(V v){
        final boolean listVertexIsNull = (this.vertices == null);
        Vertex<V,E> newVertex = new Vertex(v);
        if(!listVertexIsNull){
            
            for(Vertex<V,E> vertex : this.vertices){
                if(cmpVertices.compare(vertex.getContent(), newVertex.getContent()) == 1)
                    return false;      
            }
        }else
            this.vertices = new LinkedList();
        
        this.vertices.add(newVertex);
        return true;
    }
    
    
    
    public boolean connect(V vertex1, V vertex2, int weight, E data) {
        Vertex<V, E> v1 = this.findVertex(vertex1);
        Vertex<V, E> v2 = this.findVertex(vertex2);
        
        if(v1 != null && v2 != null){
            
            if(v1.getEdges() == null)
                v1.setEdges(    new LinkedList()    ); 
            
            Edge<E, V> newEdge = new Edge(v1, v2, weight, data);
            v1.getEdges().add(newEdge);
            
            if (!this.isDirected) {              
                Edge<E, V> newEdge2 = new Edge(v2, v1, weight, data);      
                if(v2.getEdges() == null)
                    v2.setEdges(    new LinkedList()    );
                
                v2.getEdges().add(newEdge2);
            }
            return true;
        }
        return false;
    }
    
    
    
    public boolean disconnect(V vertex1, V vertex2, int weight, E data) {
        Vertex<V, E> v1 = this.findVertex(vertex1);
        Vertex<V, E> v2 = this.findVertex(vertex2);
        
        if(v1 != null && v2 != null){
            
            if(v1.getEdges() == null)
                return false; 
            
            else{
                LinkedList<Edge<E, V>> edges = v1.getEdges();
                for(Edge<E, V> edge : edges){
                    if(this.cmpVertices.compare(edge.getTarget().getContent(), v2.getContent()) == 1)
                        edge = null;                        
                    
                }
            }
            
            if (!this.isDirected) {              
                if(v2.getEdges() == null)
                return false; 
            
                else{
                    LinkedList<Edge<E, V>> edges = v2.getEdges();
                    for(Edge<E, V> edge : edges){
                        if(this.cmpVertices.compare(edge.getTarget().getContent(), v1.getContent()) == 1)
                            edge = null;                        
                        
                    }
                }            
            }
            return true;
        }
        return false;
    }
    
    
    
    public boolean areVertxValid(V vertex1, V vertex2){
        return vertex1 != null && vertex2!= null;
    }
    
    
    
    private Vertex<V, E> findVertex(V v) {
        for (Vertex<V, E> tmp : vertices) {
            if (cmpVertices.compare(v, tmp.getContent()) == 1) {
                return tmp;
            }
        }
        return null;
    }
    
    
    
    public LinkedList<Vertex<V, E>> getVertices() {
        return vertices;
    }
    
    
    private void resetVertex(LinkedList<V> vToReset){
        for(V vertex: vToReset){
            Vertex<V, E> vertReset = this.findVertex(vertex);
            vertReset.setVisited(false);
        }
    }
    
    
    
    public void resetTotal(){
        LinkedList<Vertex<V, E>> vertices1 = this.getVertices();
        for(Vertex<V, E> vert : vertices1){
            vert.setVisited(false);
        }
    }
    
    
    
    public LinkedList<V> breadTraversal(V start, boolean reset){
        LinkedList<V> listRecorrida = new LinkedList();
        Vertex<V, E> vStart = this.findVertex(start);
        Queue<Vertex<V, E>> cola = new LinkedList();
        cola.offer(vStart);
        
        while(!cola.isEmpty()){
            Vertex<V, E> vTemp = cola.poll();
            vTemp.setVisited(true);
            listRecorrida.add(vTemp.getContent());
            LinkedList<Edge<E, V>> edges = vTemp.getEdges();
            
            if(edges != null){
                
                for(Edge<E, V> vertTempAdj : edges){
                    boolean isVisited = vertTempAdj.getTarget().isVisited();
                    boolean isInQueue = cola.contains(vertTempAdj.getTarget());
                    
                    if(!isVisited && !isInQueue)
                        cola.offer(vertTempAdj.getTarget());
                    }
            }        
        }
        if(reset)
            resetVertex(listRecorrida);
        return listRecorrida;   
    }
    
    
    
    public LinkedList<V> depthTraversal(V start, boolean reset){
        LinkedList<V> listRecorrida = new LinkedList();
        Vertex<V, E> vStart = this.findVertex(start);
        Stack<Vertex<V, E>> pila = new Stack<>();
        pila.push(vStart);
        
        while(!pila.isEmpty()){
            Vertex<V, E> vTemp = pila.pop();
            vTemp.setVisited(true);
            listRecorrida.add(vTemp.getContent());
            LinkedList<Edge<E, V>> edges = vTemp.getEdges();
            
            if(edges != null){
                
                for(Edge<E, V> vertTempAdj : edges){
                    boolean isVisited = vertTempAdj.getTarget().isVisited();
                    boolean isInPila = pila.contains(vertTempAdj.getTarget());
                    
                    if(!isVisited && !isInPila)
                        pila.push(vertTempAdj.getTarget());
                }                 
            }
            
        }
        if(reset)
            resetVertex(listRecorrida);
        return listRecorrida; 
    }
    
    
    
    public LinkedList<LinkedList<Vertex<V, E>>> getConvexGrafoNotDiriged(){
        LinkedList listComponents = new LinkedList();
        while(true){  
            Vertex<V, E> firstVertexNotVisited = this.getFirstVertexNotVisited();
            
            if(firstVertexNotVisited == null)
                break;
            
            LinkedList<V> depthTraversal = this.depthTraversal(firstVertexNotVisited.getContent(), false);
            listComponents.add(depthTraversal);
        }
        
        if(listComponents.size() == 1)
            System.out.println("El grafo es conexo");
        else 
            System.out.println("Las componentes conexas del grafo son: ");
        
        return listComponents;
    }
    
    
    
    public Vertex<V, E> getFirstVertexNotVisited(){
        for(Vertex<V, E> v : this.getVertices()){
            
            if(!v.isVisited())
                return v;
        }
        return null;
    }
    
    
    
    @Override
    public String toString(){
        String s = "";
        for(Vertex<V,E> v : this.vertices){
            s = s + v.getContent() + v.getEdges() + "\n"; 
        }
        return s;
    }
    
    
    
    public void invertDirection (V content){
        Vertex<V, E> findVertex = this.findVertex(content);
        LinkedList<Edge<E, V>> edgesOfVertex = findVertex.getEdges();
        for(Edge<E, V> edge : edgesOfVertex){
            Vertex<V, E> findVertex1 = this.findVertex(edge.getTarget().getContent());
            this.connect(findVertex1.getContent(), content, edge.getWeight(), edge.getMetadata());
            
        }
    }
    
    
    
    public Graph_AL copyGrafo(){
        Graph_AL graph_AL = new Graph_AL(this.vertices,this.cmpVertices, this.cmpEdges, this.isDirected);
        return graph_AL;
    }
    
    
    
    public static Graph_AL<Person, String> buildGraphTwo(){
        CompratorPerson cmpPersona = new CompratorPerson();
        ComparatorLazos cmpLazos = new ComparatorLazos();
        
        Graph_AL<Person, String> grafo2 = new Graph_AL(cmpPersona, cmpLazos, true);
        Person p1 = new Person("Alice", 32, "Ingeniero",    "Guayaquil" );
        Person p2 = new Person("Bob",   28, "Chef",         "Guayaquil" );
        Person p3 = new Person("Carol", 27, "Contadora",    "Quito"     );
        Person p4 = new Person("Dave",  31, "Investigador", "Cuenca"    );
        
        grafo2.addVertex(p1);
        grafo2.addVertex(p2);
        grafo2.addVertex(p3);
        grafo2.addVertex(p4);
        
        grafo2.connect(p1, p4, 1,   "odia");
        grafo2.connect(p4, p3, 2,   "odia");
        grafo2.connect(p3, p2, 1,   "ama");
        grafo2.connect(p2, p1, 3,   "le gusta");
        grafo2.connect(p3, p1, 2,   "ama");
        grafo2.connect(p3, p4, 4,   "ama");
        
        return grafo2;
    }
    
    
    
}
