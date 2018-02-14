package com.hashcode.algo;

import com.hashcode.entity.Pizza;
import com.hashcode.entity.PizzaSlice;

import java.util.ArrayList;
import java.util.List;

import static com.hashcode.entity.PizzaIngredient.MUSHROOM;
import static com.hashcode.entity.PizzaIngredient.TOMATO;

public class SliceAlgorithm {
    private Pizza pizza;
    private char minCountIngredient;

    public SliceAlgorithm(Pizza pizza) {
        this.pizza = pizza;
    }

    public List<PizzaSlice> cutOffPizza(){
        List<PizzaSlice> result = new ArrayList<>();

        //Normalization of pizza
        normalizeMatrix();
        System.out.println("Normalised pizza:");
        printPizza(pizza.getPizzaChars());

        // Find min count ingredient
        minCountIngredient = findMinCountIngredient();

        findSlice(minCountIngredient);

        return result;
    }

    private void findSlice(char minCountIngredient) {
        for(int i = 0; i < pizza.getRows(); i++){
            for (int j = 0; j < pizza.getCols(); j++) {
                if(pizza.getPizzaChars()[i][j] == minCountIngredient){
                }
            }
        }
    }

    private void normalizeMatrix(){
        if(pizza.getRows() < pizza.getCols()){
            char[][] normalizedChars = new char[pizza.getCols()][pizza.getRows()];
            char[][] oldPizzaChars = pizza.getPizzaChars();
            for (int i = 0; i < oldPizzaChars.length; i++) {
                for (int j = 0; j < oldPizzaChars[i].length; j++) {
                    normalizedChars[j][i] = oldPizzaChars[i][j];
                }
            }
            pizza.setPizzaChars(normalizedChars);
            int temp = pizza.getRows();
            pizza.setRows(pizza.getCols());
            pizza.setCols(temp);
        }
    }

    private void printPizza(char[][] pizzaChars){
        for (char[] pizzaChar : pizzaChars) {
            for (char innerPizzaChar : pizzaChar) {
                System.out.print(innerPizzaChar + " ");
            }
            System.out.println();
        }
    }

    private char findMinCountIngredient(){
        int tomatoCount = 0;
        int mushroomCount = 0;

        for (char[] pizzaChar : pizza.getPizzaChars()) {
            for (char innerPizzaChar : pizzaChar) {
                if(innerPizzaChar == TOMATO.getCharacter()){
                    tomatoCount++;
                } else if(innerPizzaChar == MUSHROOM.getCharacter()){
                    mushroomCount++;
                }
            }
        }

        if(tomatoCount >= mushroomCount){
            return MUSHROOM.getCharacter();
        } else {
            return TOMATO.getCharacter();
        }
    }
}
