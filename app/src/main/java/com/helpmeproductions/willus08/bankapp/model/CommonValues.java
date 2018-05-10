package com.helpmeproductions.willus08.bankapp.model;



public class CommonValues {
    private static volatile CommonValues commonValues = new CommonValues();
    private Customer user;

    private CommonValues() {
    }

    public static CommonValues getInstance(){
        return commonValues;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
       this.user = user;
    }
}
