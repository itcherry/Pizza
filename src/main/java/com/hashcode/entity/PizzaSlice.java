package com.hashcode.entity;

import java.util.List;
import java.util.Objects;

import static com.hashcode.entity.PizzaIngredient.MUSHROOM;
import static com.hashcode.entity.PizzaIngredient.TOMATO;

public class PizzaSlice {
    private int rowLeft;
    private int rowRight;
    private int colUpper;
    private int colDown;
    private List<PizzaCell> sliceCells;

    private PizzaSlice(int rowLeft, int rowRight, int colUpper, int colDown, List<PizzaCell> sliceCells) {
        this.rowLeft = rowLeft;
        this.rowRight = rowRight;
        this.colUpper = colUpper;
        this.colDown = colDown;
        this.sliceCells = sliceCells;
    }

    public int getRowLeft() {
        return rowLeft;
    }

    public int getRowRight() {
        return rowRight;
    }

    public int getColUpper() {
        return colUpper;
    }

    public int getColDown() {
        return colDown;
    }

    public List<PizzaCell> getSliceCells() {
        return sliceCells;
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

    public static class Builder {
        private int rowLeft;
        private int rowRight;
        private int colUpper;
        private int colDown;
        private List<PizzaCell> sliceCells;

        public Builder setRowLeft(int rowLeft) {
            this.rowLeft = rowLeft;
            return this;
        }

        public Builder setRowRight(int rowRight) {
            this.rowRight = rowRight;
            return this;
        }

        public Builder setColUpper(int colUpper) {
            this.colUpper = colUpper;
            return this;
        }

        public Builder setColDown(int colDown) {
            this.colDown = colDown;
            return this;
        }

        public Builder setSliceCharacters(List<PizzaCell> sliceCells) {
            this.sliceCells = sliceCells;
            return this;
        }

        public PizzaSlice build() {
            return new PizzaSlice(rowLeft, rowRight, colUpper, colDown, sliceCells);
        }
    }
}
