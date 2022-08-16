package tdas;

import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
            for (Edge<E, V> edge : v1.getEdges()){
                if(this.cmpEdges.compare(edge.getMetadata(), newEdge.getMetadata()) == 1 && this.cmpVertices.compare(vertex1, vertex2) == 1)
                    return false;   
            }
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
    
    
    
    public boolean disconnect(V vertex1, V vertex2) {
        Vertex<V, E> v1 = this.findVertex(vertex1);
        Vertex<V, E> v2 = this.findVertex(vertex2);
        if(v1 != null && v2 != null){
            if(v1.getEdges() == null)
                return false; 
            else{
                LinkedList<Edge<E, V>> edges = v1.getEdges();
                for(Edge<E, V> edge : edges){
                    if(this.cmpVertices.compare(edge.getTarget().getContent(), v2.getContent()) == 1)
                        edges.remove(edge);      
                }
            }    
            if (!this.isDirected) 
                this.disconnect(vertex2, vertex1);            
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
        
    
    
    public Graph_AL copyInvertGrafo(){
        if(this.isDirected){
            Graph_AL<V, E> graph_AL = new Graph_AL(this.cmpVertices, this.cmpEdges, this.isDirected);
            
            for(Vertex<V, E> v : this.vertices){
                graph_AL.addVertex(v.getContent());
                LinkedList<Edge<E, V>> edges = v.getEdges();
                if(edges != null){
                    for(Edge<E, V> edge : edges){
                        graph_AL.addVertex(edge.getTarget().getContent());
                        graph_AL.connect(edge.getTarget().getContent(), v.getContent(), edge.getWeight(), edge.getMetadata());
                    }
                }   
            }
            return graph_AL;
        }
        else
            return null;
    }
    
    
    
    public LinkedList<LinkedList<V>> getcomponetStrongConvex(){
        if(!this.isDirected){
            System.out.println("El grano es dirigido");
            return null;
        }
        LinkedList<LinkedList<V>> listTotalComponents = new LinkedList();
        Graph_AL copyInvertGrafo = this.copyInvertGrafo();
        
        LinkedList<V> listComp = new LinkedList();
   
        LinkedList<V> depthTraversalOrigin = this.breadTraversal(this.getVertices().getFirst().getContent(), false);
        LinkedList depthTraversal1Invert = copyInvertGrafo.breadTraversal(this.getVertices().getFirst().getContent(), false);

        Set<V> set = new HashSet();
        set.addAll(depthTraversalOrigin);
        set.addAll(depthTraversal1Invert);
        Iterator<V> iterator = set.iterator();
        while(iterator.hasNext())
            listComp.add(iterator.next());
        listTotalComponents.add(listComp);
        
        Queue<Vertex<V, E>> cola = new LinkedList();
        for(Vertex<V, E> v : this.getVertices()){
            if(!v.isVisited())
                cola.offer(v);
        }

        while(!cola.isEmpty()){
            LinkedList<V> listComp2 = new LinkedList();
            this.resetTotal();
            LinkedList<V> depthTraversalOrigin2 = this.breadTraversal(cola.peek().getContent(), false);
            LinkedList depthTraversal1Invert2 = copyInvertGrafo.breadTraversal(cola.poll().getContent(), false);
            Set<V> set2 = new HashSet();
            set2.addAll(depthTraversalOrigin2);
            set2.addAll(depthTraversal1Invert2);
            Iterator<V> iterator2 = set2.iterator();
            while(iterator2.hasNext())
                listComp2.add(iterator2.next());
                listTotalComponents.add(listComp2);
            }
        return listTotalComponents;  
    }
    
    
    
    public static Graph_AL<Person, String> buildGraphTwo(){
        CompratorPerson cmpPersona = new CompratorPerson();
        ComparatorLazos cmpLazos = new ComparatorLazos();
        
//        Graph_AL<Person, String> grafo1 = new Graph_AL(cmpPersona, cmpLazos, true);
//        Person Alice    =      new Person("Alice",      32, "Ingeniero",    "Guayaquil" );
//        Person Dave     =      new Person("Dave",       31, "Investigador", "Cuenca"    );
//        Person Carol    =      new Person("Carol",      27, "Contadora",    "Quito"     );
//        
//        Person Bob      =      new Person("Bob",        28, "Chef",         "Guayaquil" );
//        Person Melanie  =      new Person("Melanie",    31, "Biotecnologa", "Guayaquil" );
        
        Graph_AL<Person, String> grafo1 = new Graph_AL(cmpPersona, cmpLazos, true);
        Person Alice    =      new Person("B",      32, "Ingeniero",    "Guayaquil" );
        Person Dave     =      new Person("S",       31, "Investigador", "Cuenca"    );
        Person Carol    =      new Person("C",      27, "Contadora",    "Quito"     );
        
        Person Bob      =      new Person("H",        28, "Chef",         "Guayaquil" );
        Person Melanie  =      new Person("F",    31, "Biotecnologa", "Guayaquil" );

        grafo1.addVertex(Alice);
        grafo1.addVertex(Bob);
        grafo1.addVertex(Carol);
        grafo1.addVertex(Dave);
        grafo1.addVertex(Melanie);
        
//        grafo1.connect(Alice, Dave, 3,   "ama");
//        grafo1.connect(Alice, Carol, 3,   "odia");
//        grafo1.connect(Dave, Carol, 2,   "le gusta");
//        grafo1.connect(Bob, Dave, 1,   "odia"); // 
//        grafo1.connect(Bob, Carol, 1,   "odia");
        
        grafo1.connect(Alice, Dave, 3,   "ama"); //B
        grafo1.connect(Dave, Carol, 3,   "odia"); // S
        grafo1.connect(Carol, Alice, 2,   "le gusta"); // C
        
        grafo1.connect(Bob, Alice, 1,   "odia"); // H
        grafo1.connect(Bob, Melanie, 1,   "odia"); //F
        
        grafo1.connect(Melanie, Dave, 1,   "atrae");
        grafo1.connect(Melanie, Carol, 1,   "le gusta");
        
        return grafo1;
    }
    
    
    
}
