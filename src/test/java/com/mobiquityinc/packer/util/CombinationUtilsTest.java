package com.mobiquityinc.packer.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class CombinationUtilsTest {

    public static final int SMALL_SIZE = 15;
    public static final int BIG_SIZE = 20;
    public static final List<Integer> LIST_0_1 = Arrays.asList(0, 1);
    public static final List<List<Integer>> EXPECTED_LIST_0_1 = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(0),
            Arrays.asList(1),
            Arrays.asList(1, 0)
    );

    @Test
    public void testPerformancePowerSetRec(){
        long start = System.currentTimeMillis();
        List<Integer> small = getIntValues(SMALL_SIZE);
        CombinationUtils.powerSetRec(small);
        long end = System.currentTimeMillis();
        System.out.println("testPerformancePowerSetRec Small: took " + (end - start) + " MilliSeconds");
        start = System.currentTimeMillis();
        List<Integer> big = getIntValues(BIG_SIZE);
        CombinationUtils.powerSetRec(big);
        end = System.currentTimeMillis();
        System.out.println("testPerformancePowerSetRec Big: took " + (end - start) + " MilliSeconds");
    }

    @Test
    public void testPerformancePowerSetIt(){
        long start = System.currentTimeMillis();
        List<Integer> small = getIntValues(SMALL_SIZE);
        CombinationUtils.powerSetIt(small);
        long end = System.currentTimeMillis();
        System.out.println("testPerformancePowerSetIt Small: took " + (end - start) + " MilliSeconds");
        start = System.currentTimeMillis();
        List<Integer> big = getIntValues(BIG_SIZE);
        CombinationUtils.powerSetIt(big);
        end = System.currentTimeMillis();
        System.out.println("testPerformancePowerSetIt Big: took " + (end - start) + " MilliSeconds");
    }


    @Test
    public void testPowerSetRecSize(){
        for(int i = 0; i < SMALL_SIZE; i++){
            int size = Double.valueOf(Math.pow(2, i)).intValue();
            List<Integer> integers = getIntValues(i);
            List<List<Integer>> result = CombinationUtils.powerSetRec(integers);
            assertThat(result.size(), equalTo(size));
        }
    }

    @Test
    public void testPowerSetRecIt(){
        for(int i = 0; i < SMALL_SIZE; i++){
            int size = Double.valueOf(Math.pow(2, i)).intValue();
            List<Integer> integers = getIntValues(i);
            List<List<Integer>> result = CombinationUtils.powerSetIt(integers);
            assertThat(result.size(), equalTo(size));
        }
    }


    @Test
    public void testPowerSetRecBasic(){
        List<Integer> integers = LIST_0_1;
        int size = Double.valueOf(Math.pow(2, integers.size())).intValue();
        List<List<Integer>> result = CombinationUtils.powerSetRec(integers);
        assertThat(result.size(), equalTo(size));
        assertResult(result, EXPECTED_LIST_0_1);
    }

    @Test
    public void testPowerSetItBasic(){
        List<Integer> integers = LIST_0_1;
        int size = Double.valueOf(Math.pow(2, integers.size())).intValue();
        List<List<Integer>> result = CombinationUtils.powerSetIt(integers);
        assertThat(result.size(), equalTo(size));
        assertResult(result, EXPECTED_LIST_0_1);
    }

    private void assertResult(List<List<Integer>> actual, List<List<Integer>> expected){
         List<Integer> actualList = new ArrayList<>();
         actual.forEach(actualList::addAll);
         List<Integer> expectedList = new ArrayList<>();
         expected.forEach(expectedList::addAll);
         Collections.sort(actualList);
         Collections.sort(expectedList);
         assertThat(actualList, equalTo(expectedList));
    }

    private List<Integer> getIntValues(Integer size) {
        return Stream.iterate(0, it -> it + 1).limit(size).collect(Collectors.toList());
    }
}
