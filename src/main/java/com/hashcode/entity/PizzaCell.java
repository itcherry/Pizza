package com.hashcode.entity;

public class PizzaCell {
    private int row;
    private int col;
    private PizzaIngredient ingredient;

    public PizzaCell(int row, int col, PizzaIngredient ingredient) {
        this.row = row;
        this.col = col;
        this.ingredient = ingredient;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public PizzaIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(PizzaIngredient ingredient) {
        this.ingredient = ingredient;
    }
}
