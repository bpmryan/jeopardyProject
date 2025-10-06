package com.example.jeopardyapp;


public class catVal {
    private String category;
    private int value;

    public catVal(String category, int value) {
        this.category = category;
        this.value = value;
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public int getValue() {
        return value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
