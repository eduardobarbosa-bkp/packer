package com.mobiquityinc.packer.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class ItemTest {


    @Test(expected = IllegalArgumentException.class)
    public void testWeightGreaterThan100(){
        Item.builder().weight(101.).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCostGreaterThan100(){
        Item.builder().cost(101.).build();
    }

    @Test
    public void testBuild(){
        Item item = Item.builder().index(1).weight(100.).cost(100.).build();
        assertThat(item, notNullValue());
        assertThat(item.getIndex(), equalTo(1));
        assertThat(item.getWeight(), equalTo(100.));
        assertThat(item.getCost(), equalTo(100.));
    }


}
