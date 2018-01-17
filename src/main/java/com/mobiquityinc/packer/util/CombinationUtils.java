package com.mobiquityinc.packer.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is responsible to provide the combination utilities
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class CombinationUtils {

    /**
     * Perform the power set recursive https://en.wikipedia.org/wiki/Power_set of a given collection
     * @param list the list with the elements to be combined
     * @param <T> the generic representation of an object
     * @return the list of <T> combined using the power set
     */
    public static <T> List<List<T>> powerSetRec(List<T> list){
        return powerSetRec(list.size(), list);
    }

    private static <T> List<List<T>> powerSetRec(int k, List<T> list) {
        List<List<T>> combinations = new ArrayList<>();
        if (k == 0) {
            combinations.add(new ArrayList<T>());
            return combinations;
        }
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            List<T> rest = getSubList(list, i + 1);
            combinations.add(rest);
            for (List<T> previous : powerSetRec(k-1, rest)) {
                previous.add(element);
                combinations.add(previous);
            }
        }
        return combinations;
    }

    private static <T> List<T> getSubList(List<T> list, int i) {
        List<T> subList = new ArrayList<>();
        for (int j = i; j < list.size(); j++) {
            subList.add(list.get(j));
        }
        return subList;
    }

    /**
     * Perform the power set iterative https://en.wikipedia.org/wiki/Power_set of a given collection
     * @param list the list with the elements to be combined
     * @param <T> the generic representation of an object
     * @return the list of <T> combined using the power set
     */
    public static <T> List<List<T>> powerSetIt(Collection<T> list){
          List<List<T>> ps = new ArrayList<>();
          ps.add(new ArrayList<T>());
          for(T item: list){
              List<List<T>> newPs = new ArrayList<>();
            for (List<T> subset: ps){
                newPs.add(subset);
                List<T> newSubset = new ArrayList<>(subset);
                newSubset.add(item);
                newPs.add(newSubset);
            }
           ps = newPs;
        }
        return ps;
    }

}
