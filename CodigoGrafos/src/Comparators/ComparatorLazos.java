/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparators;

import java.util.Comparator;

/**
 *
 * @author USER
 */
public class ComparatorLazos implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        if (o1.equals(o2))
            return 1;
        return 0;
    }

    
}
