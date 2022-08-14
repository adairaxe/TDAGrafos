package codigografos;

import Comparators.ComparatorLazos;
import Comparators.CompratorPerson;
import tdas.Graph_AL;
import tdas.Graph_AM;
import tdas.Person;
public class Main {
    
    
    public static void main(String[] args) {
        
        Graph_AM<Person, Integer> buildGraphOne = Graph_AM.buildGraphOne();
        System.out.println(buildGraphOne);
        
        System.out.println(" Grafo 2");
        
        Graph_AL<Person, String> buildGraphTwo = Graph_AL.buildGraphTwo();
        System.out.println(buildGraphTwo);

    }
  
}
