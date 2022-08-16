package codigografos;

import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import tdas.Graph_AL;
import tdas.Graph_AM;
import tdas.Person;
public class Main {
    
    
    public static void main(String[] args) {
        
//        Graph_AM<Person, Integer> buildGraphOne = Graph_AM.buildGraphOne();
//        System.out.println(buildGraphOne);
//        
//        System.out.println(" Grafo 2");
//        
//        Graph_AL<Person, String> buildGraphTwo = Graph_AL.buildGraphTwo();
//        System.out.println(buildGraphTwo);
//        
//        System.out.println("Grafo copia");
//        
//        Graph_AL<Person, String> copyGrafo = buildGraphTwo.copyInvertGrafo();
//        System.out.println(copyGrafo);

        CompratorPerson cmpPersona = new CompratorPerson();
        ComparatorLazos cmpLazos = new ComparatorLazos();
        
        Graph_AL<Person, String> grafo2 = new Graph_AL(cmpPersona, cmpLazos, true);
        Person alice = new Person("Alice", 32, "Ingeniero",    "Guayaquil" );
        Person bob = new Person("Bob",   28, "Chef",         "Guayaquil" );
        Person carol = new Person("Carol", 27, "Contadora",    "Quito"     );
        Person dave = new Person("Dave",  31, "Investigador", "Cuenca"    );
        grafo2.addVertex(alice);
        grafo2.addVertex(bob);
        grafo2.addVertex(carol);
        grafo2.addVertex(dave);   
        grafo2.connect(alice, dave, 1,   "odia");
        grafo2.connect(alice, bob, 5,   "le gusta");
        grafo2.connect(carol, dave, 5,   "le gusta");
        
        System.out.println("Grafo original");
        System.out.println(grafo2);
        
        System.out.println("Grafo invertido : ");
        Graph_AL copyInvertGrafo = grafo2.copyInvertGrafo();
        System.out.println(copyInvertGrafo);
        
        

    }
  
}
