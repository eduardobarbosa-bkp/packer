package com.mobiquityinc.packer.domain;

import com.mobiquityinc.packer.util.Messages;

import static com.mobiquityinc.packer.validation.Preconditions.checkArgument;
import static com.mobiquityinc.packer.validation.Preconditions.checkNotNull;

/**
 * This class is responsible to provide a representation of a package item
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class Item {

    private final Integer index;

    private final Double weight;

    private final Double cost;

    private Item(Integer index, Double weight, Double cost) {
        checkNotNull(index);
        checkNotNull(weight);
        checkNotNull(cost);
        checkArgument(weight <= 100d, Messages.ERROR_WEIGHT_KEY, 100);
        checkArgument(cost <= 100d, Messages.ERROR_COST_KEY, 100);
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }

    /**
     * The class implement builder pattern to the class
     */
    public static class ItemBuilder {

        private Integer index = 0;

        private Double weight = 0.d;

        private Double cost = 0.d;

        public ItemBuilder index(Integer index){
            this.index = index;
            return this;
        }

        public ItemBuilder weight(Double weight){
            this.weight = weight;
            return this;
        }

        public ItemBuilder cost(Double cost){
            this.cost = cost;
            return this;
        }

        public Item build(){
            return new Item(index, weight, cost);
        }
    }
}
