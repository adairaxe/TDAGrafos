package codigografos;

import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import tdas.Graph_AL;
import tdas.Person;
public class Main {

    public static void main(String[] args) {
        
        CompratorPerson cmpPersona = new CompratorPerson();
        ComparatorLazos cmpLazos = new ComparatorLazos();
        
        Graph_AL grafo1 = new Graph_AL(cmpPersona, null, true);
        Person p1 = new Person("Alice", 32, "Ingeniero",    "Guayaquil" );
        Person p2 = new Person("Bob",   28, "Chef",         "Guayaquil" );
        Person p3 = new Person("Carol", 27, "Contadora",    "Quito"     );
        Person p4 = new Person("Dave",  31, "Investigador", "Cuenca"    );
        
        Person p5 = new Person("Adair", 21, "Estudiante",   "Duran" );
        Person p6 = new Person("Samara", 21, "Vendedora",   "Duran" );
        
        grafo1.addVertex(p1);
        grafo1.addVertex(p2);
        grafo1.addVertex(p3);
        grafo1.addVertex(p4);
        
        grafo1.addVertex(p5);
        grafo1.addVertex(p6);
        
        
        grafo1.connect(p1, p4, 3,   "odia");
        grafo1.connect(p4, p3, 2,   "odia");
        grafo1.connect(p3, p2, 1,   "ama");
        grafo1.connect(p2, p1, 3,   "le gusta");
        grafo1.connect(p3, p1, 2,   "ama");
        grafo1.connect(p3, p4, 2,   "ama");
        
        grafo1.connect(p6, p5, 2,   "ama");
        grafo1.connect(p6, p5, 2,   "ama");
        
        System.out.println(grafo1);
        
        
//        System.out.println(grafo1.breadTraversal(p3, true));
//        
//        System.out.println(grafo1.depthTraversal(p3, true));
//        
//        System.out.println(grafo1.getConvexGrafoNotDiriged());
//        Person[] personas = {p1, p2, p3};
//        Graph_AM grafo2 = new Graph_AL(personas, cmpPersona);
    }
    
}
