package com.hashcode.util;

import com.hashcode.entity.Pizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PizzaFileReader {
    private static final int CONDITIONS_QUANTITY = 4;
    private String folderPath;

    public PizzaFileReader(String folderPath) {
        this.folderPath = folderPath;
    }

    public Pizza readPizzaFromFile(String filename){
        Pizza.Builder resultPizzaBuilder = new Pizza.Builder();

        try {
            processInputFile(filename, resultPizzaBuilder);
        } catch (IOException e){
            System.err.println("Error while read input file " + filename +
                    ". Message : " + e.getMessage());
        }

        return resultPizzaBuilder.build();
    }

    private void processInputFile(String filename, Pizza.Builder pizzaBuilder) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + "\\" + filename));
        String line = bufferedReader.readLine() + ' ';

        int[] conditions = getConditionsArray(line);

        pizzaBuilder.setPizzaChars(getPizzaArray(conditions, bufferedReader));
        pizzaBuilder.setRows(conditions[0]);
        pizzaBuilder.setCols(conditions[1]);
        pizzaBuilder.setMinIngredients(conditions[2]);
        pizzaBuilder.setMaxCells(conditions[3]);
    }

    /**
     * 0 - rows
     * 1 - cols
     * 2 - minIngredients
     * 3 - maxCells
     *
     * @param line
     */
    private int[] getConditionsArray(String line) {
        int[] conditions = new int[CONDITIONS_QUANTITY];
        int whiteSpaceIndex = -1;
        for(int i = 0; i < CONDITIONS_QUANTITY; i++){
            String condition = line.substring(whiteSpaceIndex + 1, line.indexOf(' ', whiteSpaceIndex + 1));
            conditions[i] = Integer.valueOf(condition);
            whiteSpaceIndex = line.indexOf(' ', whiteSpaceIndex + 1);
        }

        return conditions;
    }

    private char[][] getPizzaArray(int[] conditions, BufferedReader bufferedReader) throws IOException {
        String line;
        char[][] pizzaChars = new char[conditions[0]][conditions[1]];

        for(int i = 0; i < conditions[0]; i++){
            line = bufferedReader.readLine().toUpperCase();
            for (int j = 0; j < conditions[1]; j++){
                pizzaChars[i][j] = line.charAt(j);
            }
        }

        return pizzaChars;
    }
}
