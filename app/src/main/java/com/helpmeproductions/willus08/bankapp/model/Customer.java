package com.helpmeproductions.willus08.bankapp.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.helpmeproductions.willus08.bankapp.data.CustomerDao;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

@Entity
@TypeConverters(CustomerDao.Converters.class)
public class Customer{

    @PrimaryKey
    @NonNull
    private String accountNumber;
    private String name;
    private float balance;
    private String transactions;

    public Customer(String name, @NonNull String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.transactions = "";
        this.balance = 0.0f;
    }

    protected Customer(Parcel in) {
        name = in.readString();
        accountNumber = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NonNull String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactionList() {
        List<Transaction> transactions = new ArrayList<>();
        Gson gson = new Gson();
        for (String s: CustomerDao.Converters.fromString(this.transactions)) {
            transactions.add(gson.fromJson(s, Transaction.class));
        }
        return transactions;
    }

    public void addTransaction(Transaction t){
        ArrayList<String> transactionList = CustomerDao.Converters.fromString(this.transactions);
        if(transactionList == null){
            transactionList = new ArrayList<>();
        }
        Gson gson = new Gson();
        transactionList.add(gson.toJson(t));
        updateBalance(t);
        this.transactions = CustomerDao.Converters.fromArrayList(transactionList);
    }

    private void updateBalance(Transaction t) {
      balance+= t.getAmount();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getTransactions() {
        return transactions;
    }
}
