package com.mobiquityinc.packer.domain;

import com.mobiquityinc.packer.util.Messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mobiquityinc.packer.validation.Preconditions.*;

/**
 * This class is responsible to provide a representation of a package
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class Pack {

    private final Double weight;

    public final List<Item> items;

    private Pack(Double weight, List<Item> items) {
        checkNotNull(weight);
        checkNotNull(items);
        checkArgument(weight <= 100d, Messages.ERROR_WEIGHT_KEY, 100);
        checkArgument(items.size() <= 15, Messages.ERROR_ITEMS_SIZE_KEY, 15);
        this.weight = weight;
        this.items = Collections.unmodifiableList(items);
    }

    public Double getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * The class implement builder pattern to the class
     */
    public static PackBuilder builder(){
        return new PackBuilder();
    }

    public static class PackBuilder {

        private Double weight = 0d;

        private List<Item> items = new ArrayList<>();

        public PackBuilder weight(Double weight){
            this.weight = weight;
            return this;
        }

        public PackBuilder items(List<Item> items){
            this.items = items;
            return this;
        }

        public Pack build(){
            return new Pack(weight, items);
        }
    }
}
