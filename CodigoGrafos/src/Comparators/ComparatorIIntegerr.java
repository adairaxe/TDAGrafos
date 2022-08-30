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
public class ComparatorIIntegerr implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 - o2 == 0)
            return 0;
        else 
            return 1;
    }
    
}
