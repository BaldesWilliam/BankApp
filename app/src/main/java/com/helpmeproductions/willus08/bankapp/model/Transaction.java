package com.helpmeproductions.willus08.bankapp.model;


public class Transaction {
    private String type; // deposit or withdrawal
    private String description;
    private float amount;
    private String date;

    public Transaction(String type, String description, float amount, String date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }
    
}
