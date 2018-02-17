package com.hashcode.algo;

import com.hashcode.entity.Pizza;
import com.hashcode.entity.PizzaCell;
import com.hashcode.entity.PizzaIngredient;
import com.hashcode.entity.PizzaSlice;

import java.util.ArrayList;
import java.util.List;

import static com.hashcode.entity.PizzaIngredient.MUSHROOM;
import static com.hashcode.entity.PizzaIngredient.TOMATO;

public class SliceAlgorithm {
    private Pizza pizza;
    private PizzaIngredient minCountIngredient;
    List<PizzaSlice> slicesResult = new ArrayList<>();

    public SliceAlgorithm(Pizza pizza) {
        this.pizza = pizza;
    }

    public List<PizzaSlice> cutOffPizza() {
        //Normalization of pizza
        normalizeMatrix();
        System.out.println("Normalised pizza:");
        printPizza(pizza.getPizzaChars());

        // Find min count ingredient
        minCountIngredient = findMinCountIngredient();

        findResultSlices(minCountIngredient);

        return slicesResult;
    }

    private void normalizeMatrix() {
        if (pizza.getRows() < pizza.getCols()) {
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

    private void printPizza(char[][] pizzaChars) {
        for (char[] pizzaChar : pizzaChars) {
            for (char innerPizzaChar : pizzaChar) {
                System.out.print(innerPizzaChar + " ");
            }
            System.out.println();
        }
    }

    private PizzaIngredient findMinCountIngredient() {
        int tomatoCount = 0;
        int mushroomCount = 0;

        for (char[] pizzaChar : pizza.getPizzaChars()) {
            for (char innerPizzaChar : pizzaChar) {
                if (innerPizzaChar == TOMATO.getCharacter()) {
                    tomatoCount++;
                } else if (innerPizzaChar == MUSHROOM.getCharacter()) {
                    mushroomCount++;
                }
            }
        }

        if (tomatoCount >= mushroomCount) {
            return MUSHROOM;
        } else {
            return TOMATO;
        }
    }

    private void findResultSlices(PizzaIngredient minCountIngredient) {
        for (int i = 0; i < pizza.getRows(); i++) {
            for (int j = 0; j < pizza.getCols(); j++) {
                if (pizza.getPizzaChars()[i][j] == minCountIngredient.getCharacter()) {
                    PizzaCell pizzaCell = new PizzaCell(i, j, minCountIngredient);
                    slicesResult.add(findCorrectSliceForCell(pizzaCell));
                }
            }
        }
    }

    private PizzaSlice findCorrectSliceForCell(PizzaCell pizzaCell) {
        PizzaSlice resultSlice = new PizzaSlice();
        List<PizzaSlice> possibleSlices = findAllPossibleSlicesForCell(pizzaCell);

        //We need to take that slice, which contains minimum elements of minIngredient
        if (possibleSlices != null && !possibleSlices.isEmpty()) {
            resultSlice = possibleSlices.get(0);
            for (PizzaSlice slice : possibleSlices) {
                int quantityOfMinCountIngredient = slice.getQuantityOfMinCountIngredient(minCountIngredient);
                if ((quantityOfMinCountIngredient >= pizza.getMinIngredients()) &&
                        (quantityOfMinCountIngredient <= resultSlice
                                .getQuantityOfMinCountIngredient(minCountIngredient)) &&
                        !slice.equals(resultSlice)) {
                    resultSlice = slice;
                }
            }
        }
        return resultSlice;
    }

    private List<PizzaSlice> findAllPossibleSlicesForCell(PizzaCell pizzaCell) {
        PizzaCell leftTopBound = findLeftTopEmptyBound(pizzaCell);
        PizzaCell rightBottomBound = findRightBottomEmptyBound(pizzaCell);
        List<PizzaSlice> result = new ArrayList<>();

        for (int i = 1; i < pizza.getMaxCells(); i++) {
            // Possible rectangle can be a*b size;
            int a = pizza.getMaxCells() / i - 1;
            int b = i - 1;

            findPossibleSlicesLeftSide(pizzaCell, leftTopBound, rightBottomBound, result, a, b);
            findPossibleSlicesTopSide(pizzaCell, leftTopBound, rightBottomBound, result, a, b);
            findPossibleSlicesRightSide(pizzaCell, leftTopBound, rightBottomBound, result, a, b);
            findPossibleSlicesBottomSide(pizzaCell, leftTopBound, rightBottomBound, result, a, b);
        }

        return result;
    }

    private PizzaCell findLeftTopEmptyBound(PizzaCell pizzaCell) {
        int leftBound = 0;
        int topBound = 0;
        for (PizzaSlice pizzaSlice : slicesResult) {
            if (pizzaSlice.getRowBottom() >= pizzaCell.getRow() &&
                    pizzaSlice.getColRight() >= leftBound) {
                leftBound = pizzaSlice.getColRight();
            }
            if (pizzaSlice.getColRight() >= pizzaCell.getCol() &&
                    pizzaSlice.getRowBottom() >= topBound) {
                topBound = pizzaSlice.getRowBottom();
            }
        }

        return new PizzaCell(topBound, leftBound, pizzaCell.getIngredient());
    }

    private PizzaCell findRightBottomEmptyBound(PizzaCell pizzaCell) {
        int rightBound = pizza.getCols() - 1;
        int bottomBound = pizza.getRows() - 1;
        for (PizzaSlice pizzaSlice : slicesResult) {
            if (pizzaSlice.getRowTop() <= pizzaCell.getRow() &&
                    pizzaSlice.getColLeft() <= rightBound) {
                rightBound = pizzaSlice.getColLeft();
            }
            if (pizzaSlice.getColLeft() <= pizzaCell.getCol() &&
                    pizzaSlice.getRowTop() <= bottomBound) {
                bottomBound = pizzaSlice.getRowTop();
            }
        }

        return new PizzaCell(bottomBound, rightBound, pizzaCell.getIngredient());
    }

    private void findPossibleSlicesLeftSide(PizzaCell pizzaCell, PizzaCell leftTopBound, PizzaCell rightBottomBound, List<PizzaSlice> result, int a, int b) {
        if (leftTopBound.getCol() <= pizzaCell.getCol() - a) {
            PizzaSlice.Builder pizzaSliceBuilder = new PizzaSlice.Builder();
            pizzaSliceBuilder
                    .setColLeft(pizzaCell.getCol() - a)
                    .setColRight(pizzaCell.getCol());
            if (leftTopBound.getRow() <= pizzaCell.getRow() - b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setRowTop(pizzaCell.getRow() - b)
                        .setRowBottom(pizzaCell.getRow())
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
            if (rightBottomBound.getRow() >= pizzaCell.getRow() + b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setRowTop(pizzaCell.getRow())
                        .setRowBottom(pizzaCell.getRow() + b)
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
        }
    }

    private void findPossibleSlicesTopSide(PizzaCell pizzaCell, PizzaCell leftTopBound, PizzaCell rightBottomBound, List<PizzaSlice> result, int a, int b) {
        if (leftTopBound.getRow() <= pizzaCell.getRow() - a) {
            PizzaSlice.Builder pizzaSliceBuilder = new PizzaSlice.Builder();
            pizzaSliceBuilder
                    .setRowTop(pizzaCell.getRow() - a)
                    .setRowBottom(pizzaCell.getRow());
            if (leftTopBound.getCol() <= pizzaCell.getCol() - b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setColLeft(pizzaCell.getCol() - b)
                        .setColRight(pizzaCell.getCol())
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
            if (rightBottomBound.getCol() >= pizzaCell.getCol() + b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setColLeft(pizzaCell.getCol())
                        .setColRight(pizzaCell.getCol() + b)
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
        }
    }

    private void findPossibleSlicesRightSide(PizzaCell pizzaCell, PizzaCell leftTopBound, PizzaCell rightBottomBound, List<PizzaSlice> result, int a, int b) {
        if (rightBottomBound.getCol() >= pizzaCell.getCol() + a) {
            PizzaSlice.Builder pizzaSliceBuilder = new PizzaSlice.Builder();
            pizzaSliceBuilder
                    .setColLeft(pizzaCell.getCol())
                    .setColRight(pizzaCell.getCol() + a);
            if (leftTopBound.getRow() <= pizzaCell.getRow() - b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setRowTop(pizzaCell.getRow() - b)
                        .setRowBottom(pizzaCell.getRow())
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
            if (rightBottomBound.getRow() >= pizzaCell.getRow() + b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setRowTop(pizzaCell.getRow())
                        .setRowBottom(pizzaCell.getRow() + b)
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
        }
    }

    private void findPossibleSlicesBottomSide(PizzaCell pizzaCell, PizzaCell leftTopBound, PizzaCell rightBottomBound, List<PizzaSlice> result, int a, int b) {
        if (rightBottomBound.getRow() >= pizzaCell.getRow() + a) {
            PizzaSlice.Builder pizzaSliceBuilder = new PizzaSlice.Builder();
            pizzaSliceBuilder
                    .setRowTop(pizzaCell.getRow())
                    .setRowBottom(pizzaCell.getRow() + a);
            if (leftTopBound.getCol() <= pizzaCell.getCol() - b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setColLeft(pizzaCell.getCol() - b)
                        .setColRight(pizzaCell.getCol())
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
            if (rightBottomBound.getCol() >= pizzaCell.getCol() + b) {
                PizzaSlice pizzaSlice = pizzaSliceBuilder
                        .setColLeft(pizzaCell.getCol())
                        .setColRight(pizzaCell.getCol() + b)
                        .build();
                setPizzaIngredientsBasedOnSliceBounds(pizzaSlice);
                result.add(pizzaSlice);
            }
        }
    }

    private void setPizzaIngredientsBasedOnSliceBounds(PizzaSlice pizzaSlice) {
        List<PizzaCell> cells = new ArrayList<>();
        for (int i = pizzaSlice.getRowTop(); i < pizzaSlice.getRowBottom(); i++) {
            for (int j = pizzaSlice.getColLeft(); j < pizzaSlice.getColRight(); j++) {
                PizzaIngredient ingredient = pizza.getPizzaChars()[i][j] == TOMATO.getCharacter() ?
                        TOMATO : MUSHROOM;
                cells.add(new PizzaCell(i, j, ingredient));
            }
        }
        pizzaSlice.setSliceCells(cells);
    }
}
