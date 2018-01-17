package com.mobiquityinc.packer.handler;

import com.mobiquityinc.packer.domain.Item;
import com.mobiquityinc.packer.domain.Pack;
import com.mobiquityinc.packer.util.CombinationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to provide the handler to process packages
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PackerHandler {


    /**
     * Perform the analysis of the packs based on the cost (maximizing)  and weight (less than or equal to the package limit) as criteria,
     * as result provide the list of packs with the items that must be send.
     * @param packages list of {@link Pack} with the items to be analysed
     * @return the list of {@link Pack} with the items remain after the analysis
     */
    public List<Pack> process(List<Pack> packages){
        List<Pack> list = new ArrayList<>();
        packages.stream().forEach(pk -> list.add(process(pk)));
        return list;
    }

    private Pack process(Pack pack){
        //get the possible combinations of the items
        List<List<Item>> packSubSet = CombinationUtils.powerSetIt(pack.getItems());
        Double maxWeight = 0.0;
        Double maxCost = 0.0;
        List<Item> solution =  new ArrayList<>();
        for(List<Item> subSet: packSubSet){
            Double totalWeight = subSet.stream().mapToDouble(Item::getWeight).sum();
            Double totalCost = subSet.stream().mapToDouble(Item::getCost).sum();
            //avoid the set with overweight
            if(totalWeight >  pack.getWeight()){
                continue;
            }
            //the solution is the maximizing the cost and minimizing the weight
            if((totalCost > maxCost)
                || (totalCost.equals(maxCost) && totalWeight < maxWeight)){
               solution = subSet;
               maxWeight = totalWeight;
               maxCost = totalCost;
            }
        }
        return Pack.builder().weight(pack.getWeight()).items(solution).build();
    }
}
