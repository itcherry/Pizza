package com.hashcode;

import com.hashcode.algo.SliceAlgorithm;
import com.hashcode.entity.Pizza;
import com.hashcode.entity.PizzaSlice;
import com.hashcode.util.PizzaFileReader;

import java.util.List;

public class Main {
    private static final String FILENAME_EXAMPLE = "example.in";
    private static final String FILENAME_BIG = "big.in";
    private static final String FILENAME_MEDIUM = "medium.in";
    private static final String FILENAME_SMALL = "small.in";

    public static void main(String[] args) {
        System.out.println("Start program!");

        String currentFile = FILENAME_SMALL;

        PizzaFileReader reader = new PizzaFileReader("D:\\Studying\\java\\Pizza\\src\\assets\\input");
        Pizza pizza = reader.readPizzaFromFile(currentFile);

        System.out.println("Pizza from file " + currentFile + ":");
        printFile(pizza.getPizzaChars());

        SliceAlgorithm algorithm = new SliceAlgorithm(pizza);
        List<PizzaSlice> slices = algorithm.cutOffPizza();

        System.out.println("End program!");
    }

    private static void printFile(char[][] pizzaChars){
        for (char[] pizzaChar : pizzaChars) {
            for (char innerPizzaChar : pizzaChar) {
                System.out.print(innerPizzaChar + " ");
            }
            System.out.println();
        }
    }
}
