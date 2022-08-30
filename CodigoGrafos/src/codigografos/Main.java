package codigografos;

import Comparators.ComparatorIIntegerr;
import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import java.util.Comparator;
import java.util.LinkedList;
import tdas.Edge;
import tdas.Graph_AL;
import tdas.Graph_AM;
import tdas.Person;
import tdas.Vertex;
public class Main {
    
    
    public static void main(String[] args) {
        
//        System.out.println("Grafo dirigido");
//        Graph_AL<Person, String> buildGraphTwo = Graph_AL.buildGraphTwo();
//        System.out.println(buildGraphTwo);
//        
//        LinkedList<LinkedList<Person>> componetStrongConvex = buildGraphTwo.getcomponetStrongConvex();
//        System.out.println(componetStrongConvex);
        
 
        CompratorPerson cmpPersona = new CompratorPerson();
        ComparatorLazos cmpLazos = new ComparatorLazos();
        ComparatorIIntegerr cmpString = new ComparatorIIntegerr();
        Graph_AL<Person, String> grafo1 = new Graph_AL(cmpPersona, cmpLazos, false);
        Person Alice    =      new Person("A",      32, "Ingeniero",    "Guayaquil" );
        Person Dave     =      new Person("B",       31, "Investigador", "Cuenca"    );
        Person Carol    =      new Person("C",      27, "Contadora",    "Quito"     );
        Person Bob      =      new Person("D",        28, "Chef",         "Guayaquil" );
//        Person Melanie  =      new Person("F",    31, "Biotecnologa", "Guayaquil" );

        grafo1.addVertex(Alice);
        grafo1.addVertex(Bob);
        grafo1.addVertex(Carol);
        grafo1.addVertex(Dave);
//        grafo1.addVertex(Melanie);
        grafo1.connect(Alice, Dave, 3,   "ama"); //B
        grafo1.connect(Dave, Carol, 3,   "odia"); // S
        grafo1.connect(Carol, Alice, 2,   "le gusta"); // C
        grafo1.connect(Carol, Bob, 1,   "odia"); // H
//        grafo1.connect(Alice, Bob, 1,   "odia"); //F
//        System.out.println(grafo1);
        
//        grafo1.dijkstraShortPath(Alice, Bob);
//        grafo1.connect(Melanie, Dave, 1,   "atrae");
//        grafo1.connect(Melanie, Carol, 1,   "le gusta");
//        buildGraphTwo.dijkstraShortPath(Alice, Melanie);
//        System.out.println("Grafo no dirigido");
//        System.out.println(grafo1);
//        LinkedList<LinkedList<Vertex<Person, String>>> convexGrafo1 = grafo1.getConvexGrafo();
//        System.out.println(convexGrafo1);
//        
//        LinkedList<LinkedList<Person>> componetStrongConvex2 = grafo1.getcomponetStrongConvex();
//        System.out.println(componetStrongConvex2);
        
        Graph_AL<String, Integer> grafo3 = new Graph_AL(cmpLazos, cmpString, false);

        String decent = "decent";
        String recent = "recent";
        String deceit = "deceit";
        String resent = "resent";
        String repent = "repent";
        String repeat = "repeat";
        
        grafo3.addVertex(decent);
        grafo3.addVertex(deceit);
        grafo3.addVertex(recent);
        grafo3.addVertex(resent);
        grafo3.addVertex(repent);
        grafo3.addVertex(repeat);
        
        grafo3.connect(decent, recent, 1, 1);
        grafo3.connect(decent, deceit, 1, 1);
        grafo3.connect(recent, resent, 1, 1);
        grafo3.connect(recent, repent, 1, 1);
        grafo3.connect(resent, repent, 1, 1);
        grafo3.connect(repent, repeat, 1, 1);
        
        System.out.println(grafo3);
        
        grafo3.dijkstraShortPath(decent, repeat);


//        
        

    }
  
}
