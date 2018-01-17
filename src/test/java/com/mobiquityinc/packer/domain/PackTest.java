package com.mobiquityinc.packer.domain;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PackTest {


    @Test(expected = IllegalArgumentException.class)
    public void testWeightGreaterThan100(){
        Pack.builder().weight(101.).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testItemsGreaterThan15(){
        Pack.builder().items(
                Stream.generate(() -> Item.builder().build()).limit(16)
                .collect(Collectors.toList())
        ).build();
    }

    @Test
    public void testBuild(){
        Pack pack = Pack.builder().weight(100.).items(
                Stream.generate(() -> Item.builder().build()).limit(15)
                        .collect(Collectors.toList())
        ).build();
        assertThat(pack, notNullValue());
        assertThat(pack.getWeight(), equalTo(100.));
        assertThat(pack.getItems(), notNullValue());
        assertThat(pack.getItems().size(), equalTo(15));
    }


}
