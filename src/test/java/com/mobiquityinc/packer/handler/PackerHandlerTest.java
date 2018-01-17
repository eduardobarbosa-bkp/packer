package com.mobiquityinc.packer.handler;

import com.mobiquityinc.packer.domain.Item;
import com.mobiquityinc.packer.domain.Pack;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PackerHandlerTest {


    private PackerHandler handler = new PackerHandler();

    private final List<Pack> PACKS_EMPTY = Collections.EMPTY_LIST;

    private final List<Pack> PACKS_SAMPLE = Arrays.asList(
            Pack.builder().weight(81.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(53.38).cost(45.).build()
                            , Item.builder().index(2).weight(88.62).cost(98.).build()
                            , Item.builder().index(3).weight(78.48).cost(3.).build()
                            , Item.builder().index(4).weight(72.30).cost(76.).build()
                            , Item.builder().index(5).weight(30.18).cost(9.).build()
                            , Item.builder().index(6).weight(46.34).cost(48.).build()
                    )
            ).build()
            ,Pack.builder().weight(8.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(15.3).cost(34.).build()
                    )
            ).build()
            ,Pack.builder().weight(75.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(85.31).cost(29.).build()
                            , Item.builder().index(2).weight(14.55).cost(74.).build()
                            , Item.builder().index(3).weight(3.98).cost(16.).build()
                            , Item.builder().index(4).weight(26.24).cost(55.).build()
                            , Item.builder().index(5).weight(63.69).cost(52.).build()
                            , Item.builder().index(6).weight(76.25).cost(75.).build()
                            , Item.builder().index(7).weight(60.02).cost(74.).build()
                            , Item.builder().index(8).weight(93.18).cost(35.).build()
                            , Item.builder().index(9).weight(89.95).cost(78.).build()
                    )
            ).build()
            ,Pack.builder().weight(56.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(90.72).cost(13.).build()
                            , Item.builder().index(2).weight(33.80).cost(40.).build()
                            , Item.builder().index(3).weight(43.15).cost(10.).build()
                            , Item.builder().index(4).weight(37.97).cost(16.).build()
                            , Item.builder().index(5).weight(46.81).cost(36.).build()
                            , Item.builder().index(6).weight(48.77).cost(79.).build()
                            , Item.builder().index(7).weight(81.80).cost(45.).build()
                            , Item.builder().index(8).weight(19.36).cost(79.).build()
                            , Item.builder().index(9).weight(6.76).cost(64.).build()
                    )
            ).build()
    );


    private final List<Pack> PACKS_SAMPLE_RESULT = Arrays.asList(
            Pack.builder().weight(81.0).items(
                    Arrays.asList(
                            Item.builder().index(4).weight(72.30).cost(76.).build()
                    )
            ).build()
            , Pack.builder().weight(8.0).build()
            , Pack.builder().weight(75.0).items(
                    Arrays.asList(
                             Item.builder().index(2).weight(14.55).cost(74.).build()
                            , Item.builder().index(7).weight(60.02).cost(74.).build()
                    )
            ).build()
            , Pack.builder().weight(56.0).items(
                    Arrays.asList(
                             Item.builder().index(8).weight(19.36).cost(79.).build()
                            , Item.builder().index(9).weight(6.76).cost(64.).build()
                    )
            ).build()
    );


    private final List<Pack> PACKS_SAME_COST = Arrays.asList(
            Pack.builder().weight(80.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(41.).cost(76.).build()
                            , Item.builder().index(2).weight(40.).cost(76.).build()
                    )
            ).build()
    );

    private final List<Pack> PACKS_SAME_COST_RESULT = Arrays.asList(
            Pack.builder().weight(80.0).items(
                    Arrays.asList(
                            Item.builder().index(2).weight(40.).cost(76.).build()
                    )
            ).build()
    );

    private final List<Pack> PACKS_SAME_WEIGHT = Arrays.asList(
            Pack.builder().weight(80.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(50.).cost(100.).build()
                            , Item.builder().index(2).weight(50.).cost(76.).build()
                    )
            ).build()
    );

    private final List<Pack> PACKS_SAME_WEIGHT_RESULT = Arrays.asList(
            Pack.builder().weight(80.0).items(
                    Arrays.asList(
                            Item.builder().index(1).weight(50.).cost(100.).build()
                    )
            ).build()
    );


    @Test
    public void testProcessEmptyPackages(){
        List<Pack> results = handler.process(PACKS_EMPTY);
        assertThat(results, notNullValue());
        assertThat(results.size(), equalTo(0));
    }


    @Test
    public void testProcessProblemSample(){
        List<Pack> results = handler.process(PACKS_SAMPLE);
        assertThat(results, notNullValue());
        assertThat(results.size(), equalTo(PACKS_SAMPLE.size()));
        assertResult(results, PACKS_SAMPLE_RESULT);
    }

    @Test
    public void testProcessPackagesSameCost(){
        List<Pack> results = handler.process(PACKS_SAME_COST);
        assertThat(results, notNullValue());
        assertThat(results.size(), equalTo(PACKS_SAME_COST_RESULT.size()));
        assertResult(results, PACKS_SAME_COST_RESULT);
    }

    @Test
    public void testProcessPackagesSameWeight(){
        List<Pack> results = handler.process(PACKS_SAME_WEIGHT);
        assertThat(results, notNullValue());
        assertThat(results.size(), equalTo(PACKS_SAME_WEIGHT.size()));
        assertResult(results, PACKS_SAME_WEIGHT_RESULT);
    }


    private void assertResult(List<Pack> actual, List<Pack> expected){
        String actualStr = actual.stream()
                .map(pack -> pack.getItems().stream()
                        .map(item -> item.getIndex()).sorted().collect(Collectors.toList())
                        .stream().map(idx -> idx.toString()).collect(Collectors.joining(",")))
                .map(str -> str.trim().length() == 0 ? "-" : str)
                .collect(Collectors.joining("\n"));
        String expectedStr = expected.stream()
                .map(pack -> pack.getItems().stream()
                        .map(item -> item.getIndex()).sorted().collect(Collectors.toList())
                        .stream().map(idx -> idx.toString()).collect(Collectors.joining(",")))
                .map(str -> str.trim().length() == 0 ? "-" : str)
                .collect(Collectors.joining("\n"));
        assertThat(actualStr, equalTo(expectedStr));
    }

}
