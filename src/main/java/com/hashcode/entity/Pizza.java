package com.hashcode.entity;

public class Pizza {
    private char[][] pizzaChars;
    private int rows;
    private int cols;
    private int minIngredients;
    private int maxCells;

    private Pizza(char[][] pizzaChars, int rows, int cols, int minIngredients, int maxCells) {
        this.pizzaChars = pizzaChars;
        this.rows = rows;
        this.cols = cols;
        this.minIngredients = minIngredients;
        this.maxCells = maxCells;
    }

    public char[][] getPizzaChars() {
        return pizzaChars;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMinIngredients() {
        return minIngredients;
    }

    public int getMaxCells() {
        return maxCells;
    }

    public void setPizzaChars(char[][] pizzaChars) {
        this.pizzaChars = pizzaChars;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setMinIngredients(int minIngredients) {
        this.minIngredients = minIngredients;
    }

    public void setMaxCells(int maxCells) {
        this.maxCells = maxCells;
    }

    public static class Builder {
        private char[][] pizzaChars;
        private int rows;
        private int cols;
        private int minIngredients;
        private int maxCells;

        public Builder setPizzaChars(char[][] pizzaChars) {
            this.pizzaChars = pizzaChars;
            return this;
        }

        public Builder setRows(int rows) {
            this.rows = rows;
            return this;
        }

        public Builder setCols(int cols) {
            this.cols = cols;
            return this;
        }

        public Builder setMinIngredients(int minIngredients) {
            this.minIngredients = minIngredients;
            return this;
        }

        public Builder setMaxCells(int maxCells) {
            this.maxCells = maxCells;
            return this;
        }

        public Pizza build() {
            return new Pizza(pizzaChars, rows, cols, minIngredients, maxCells);
        }
    }
}
