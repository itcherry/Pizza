package com.hashcode.entity;

public enum PizzaIngredient {
    TOMATO('T'),
    MUSHROOM('M');

    char character;

    PizzaIngredient(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
