package com.hashcode.entity;

public class PizzaCell {
    private int row;
    private int col;
    private char ingredient;

    public PizzaCell(int row, int col, char ingredient) {
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

    public char getIngredient() {
        return ingredient;
    }

    public void setIngredient(char ingredient) {
        this.ingredient = ingredient;
    }
}
