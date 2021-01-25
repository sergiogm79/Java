/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testciklum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Sergio González Montoya
 */
public class CodeTest {
    
    private static String [] numbersIn;
    
    public static void main (String[] args) {
        
        numbersIn = args;
        
        // Exercise 1 Reverse Array
        int[] test1 = {1,3,5,7,4,11,12};
        int[] testOutput1 = reverseArray(test1);
        System.out.println(Arrays.toString(testOutput1));
        
        // Exercise 2 String[] to uppercase
        String[] test2 = {"oNce", "upOn", "a", "Time", "ThErE","Was","A ","PrinCesS","In","tHe","Castle"};
        String[] testOutput2 = uppercaseArray(test2);
        System.out.println(Arrays.toString(testOutput2));
        
        // Exercise 3 Word count
        String test3 = "the cat jumped over the mat the mat was on the floor";
        System.out.println("Number of 'mat' "+findWordCount(test3,"MaT"));
        System.out.println("Number of 'the' "+findWordCount(test3,"THE"));
        System.out.println("Number of 'doormat' "+findWordCount(test3,"Doormat"));
        
        // Exercise 4 Compose functions g(f(x))
        Function <Integer,Integer> add1 = x -> x+1;
        Function <Integer,Integer> mult3 = y -> y*3;
        Function <Integer,Integer> h = composeU(add1, mult3);
        
        System.out.println("ComposeU ((*3)(+1)12): " + h.apply(12));

        // Exercise 5 Print the first lines of El quijote:
        writeContentsToConsole();
        
        // Test 6 Handle a nullPointer Exception
        handleInvalidArgument();
        
        // Test 10 Puzzle
        puzzle();
    }
    
    // Iterate the input refering each index with total length-1 ([0..n-1]) , current position using stream
    // Generates another one
    public static int[] reverseArray (int[] input) {
        int[] res = new int[0];
        if (input!=null) {
            res = IntStream.range(0, input.length)
                .map(i -> input[input.length - 1 - i])
                .toArray();
        }
        return res;
    }
    
    // Iterate the input array generating another one with all strings to uppercase using stream
    public static String[] uppercaseArray(String[] input) {
        String[] res = new String[0];
        if (input!=null) {
           
            res = Arrays.asList(input)
                        .stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()).toArray(res);
            
        }           
        return res;
    }
    
    // Split the list by whitespaces, remove all the elements in the list that doesn't match with the word to find
    // using a predicate
    public static int findWordCount(String text, String wordToFind) {
        int count = 0;
        if (text != null) {
            List<String> listInit = new ArrayList<>(Arrays.asList(text.trim().split("\\s+")));
            listInit.removeIf(p -> !p.equalsIgnoreCase(wordToFind));
            count = listInit.size();
        }
        return count;
    }
    
    public static Function<Integer,Integer> composeU(Function<Integer,Integer> f1, Function<Integer,Integer> f2) {
        // It would be simpler use f1.compose(f2), using BinaryOperator object looks more descriptive
        BinaryOperator<Function<Integer,Integer>> c1 = (f,g) -> x -> g.apply(f.apply(x));
        return c1.apply(f1, f2);
    }
    
    // Read a file and print its text content to console 
    public static void writeContentsToConsole() {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/quijote.txt"))) {
            stream.forEach(System.out::println);
        } catch (NoSuchFileException ex) {
            Logger.getLogger(CodeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    // Method that handles a NullPointerException, the method can raise a NullPointerException
    // For testing it, remove "Hello World!" and set the str1 variable to null
    public static void handleInvalidArgument() throws NullPointerException {
        if (numbersIn == null) {
            throw new NullPointerException("arguments are null");
        }
        try {
            int[] numbersValid = IntStream.range(0, numbersIn.length).map(i-> Integer.parseInt(numbersIn[i])).toArray();
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("The input arguments are not numbers.");
        }
    }
    
    // Puzzle method as described in the sentence
    public static void puzzle() {
        
        handleInvalidArgument();
        
        StringBuffer sb = new StringBuffer();
        boolean found = false;
        int i = 0;
        int[] listNumber = new int[0];
        
        // Get the list of numbers from input param
        if (numbersIn!= null) {
            listNumber = IntStream.range(0, numbersIn.length).map(j-> Integer.parseInt(numbersIn[j])).toArray();
        }
        
        // Iterate the list looking for snap (consecutive numbers)
        while (i<listNumber.length && !found) {
            if (i>0) {
                if (listNumber[i] == listNumber[i-1]) {
                    sb.append(",").append(listNumber[i]).append(",snap!");
                    found = true;
                } else {
                    sb.append(",").append(listNumber[i]);
                }
            } else {
                // Print the first element
                sb.append(listNumber[0]);
            }
            i++;
        }
        System.out.println(sb.toString());
    }
    
}
