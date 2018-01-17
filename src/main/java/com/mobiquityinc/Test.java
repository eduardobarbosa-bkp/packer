package com.mobiquityinc;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by eduardo.costa on 30/11/2017.
 */
public class Test {


    public static void main(String[] args) {

        String s = new String("Test");
        s.charAt(0);
        char[] chars = s.toCharArray();
        char c = Character.MIN_VALUE;
        Arrays.sort(chars);

        Stream.of(chars).forEach(System.out::println);

         for(char ct: chars){

         }

        System.out.println(str("aabaaaccc"));


    }


    public static String str(String str){

        StringBuilder builder = new StringBuilder();
        int counter = 1;
        char currentCharacter = Character.MIN_VALUE;

        for(int i= 0; i < str.length(); i++){
            char character = str.charAt(i);
            if(character == currentCharacter){
                counter++;
            }else {
                if(currentCharacter != Character.MIN_VALUE){
                    builder.append(currentCharacter+""+counter);
                    counter = 1;
                }
                currentCharacter = character;
            }
        }
        if(currentCharacter != Character.MIN_VALUE){
            builder.append(currentCharacter+""+counter);
        }

        return builder.toString();
    }


}
