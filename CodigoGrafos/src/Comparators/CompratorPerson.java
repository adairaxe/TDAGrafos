/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparators;

import java.util.Comparator;
import tdas.Person;

/**
 *
 * @author USER
 */
public class CompratorPerson implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        if(o1 == null || o2 == null)
            return 0;
        if(o1.getNombre().equals(o2.getNombre()))
            return 1;
        else 
            return 0;
    }
    
}
