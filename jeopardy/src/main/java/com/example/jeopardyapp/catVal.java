package com.example.jeopardyapp;


public class CatVal {
    private String category;
    private int value;

    public CatVal(String category, int value) {
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
