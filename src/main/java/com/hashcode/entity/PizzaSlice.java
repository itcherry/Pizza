package com.hashcode.entity;

import java.util.List;
import java.util.Objects;

import static com.hashcode.entity.PizzaIngredient.MUSHROOM;
import static com.hashcode.entity.PizzaIngredient.TOMATO;

public class PizzaSlice {
    private int rowUpper;
    private int rowDown;
    private int colLeft;
    private int colRight;
    private List<PizzaCell> sliceCells;

    private PizzaSlice(int rowUpper, int rowDown, int colLeft, int colRight, List<PizzaCell> sliceCells) {
        this.rowUpper = rowUpper;
        this.rowDown = rowDown;
        this.colLeft = colLeft;
        this.colRight = colRight;
        this.sliceCells = sliceCells;
    }

    public int getRowUpper() {
        return rowUpper;
    }

    public int getRowDown() {
        return rowDown;
    }

    public int getColLeft() {
        return colLeft;
    }

    public int getColRight() {
        return colRight;
    }

    public List<PizzaCell> getSliceCells() {
        return sliceCells;
    }

    public int getLeastIngredientQuantity(PizzaIngredient leastIngredient){
        switch (leastIngredient) {
            case TOMATO:
                return getTomatoQuantity();
            case MUSHROOM:
                return getMushroomQuantity();
        }
        return 0;
    }

    private int getTomatoQuantity(){
        Objects.requireNonNull(sliceCells);
        int quantity = 0;
        for (PizzaCell cell: sliceCells) {
            if(cell.getIngredient() == TOMATO.getCharacter()){
                quantity ++;
            }
        }
        return quantity;
    }

    private int getMushroomQuantity(){
        Objects.requireNonNull(sliceCells);
        int quantity = 0;
        for (PizzaCell cell: sliceCells) {
            if(cell.getIngredient() == MUSHROOM.getCharacter()){
                quantity++;
            }
        }
        return quantity;
    }

    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for (PizzaCell cell: sliceCells) {
            result.append(cell.getIngredient() + ", ");
        }
        result.append("]");
        return result.toString();
    }

    public static class Builder {
        private int rowUpper;
        private int rowDown;
        private int colLeft;
        private int colRight;
        private List<PizzaCell> sliceCells;

        public Builder setRowUpper(int rowUpper) {
            this.rowUpper = rowUpper;
            return this;
        }

        public Builder setRowDown(int rowDown) {
            this.rowDown = rowDown;
            return this;
        }

        public Builder setColLeft(int colLeft) {
            this.colLeft = colLeft;
            return this;
        }

        public Builder setColRight(int colRight) {
            this.colRight = colRight;
            return this;
        }

        public Builder setSliceCharacters(List<PizzaCell> sliceCells) {
            this.sliceCells = sliceCells;
            return this;
        }

        public PizzaSlice build() {
            return new PizzaSlice(rowUpper, rowDown, colLeft, colRight, sliceCells);
        }
    }
}
