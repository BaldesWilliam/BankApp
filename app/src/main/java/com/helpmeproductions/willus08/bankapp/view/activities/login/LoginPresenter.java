package com.helpmeproductions.willus08.bankapp.view.activities.login;


import android.util.Log;

import com.helpmeproductions.willus08.bankapp.data.AppDatabase;
import com.helpmeproductions.willus08.bankapp.data.DataStorage;
import com.helpmeproductions.willus08.bankapp.data.Encryption;
import com.helpmeproductions.willus08.bankapp.model.CommonValues;
import com.helpmeproductions.willus08.bankapp.model.Customer;
import java.util.Random;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;
    private AppDatabase db;
    private Encryption encryption = new Encryption();

    @Override
    public void addView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public boolean checkCredentials(String UserName, String Password) {
        db = AppDatabase.getInMemoryDatabase(view.getContext());
        DataStorage data = db.dataModel().getWithPassword(Password);
        if (data != null){
            String encryptedAccountNumber = data.getEncryptedAccountNumber();

             String decryptedNumber =  encryption.decrypt(
                  encryptedAccountNumber,
                  data.getDecryptingCipher(),
                  Password);
             if(!decryptedNumber.equals("")){
                 Customer customer = db.customerModel().loadCustomerByAcountNumber(decryptedNumber);
                 if(customer.getName().equals(UserName)){
                     CommonValues commonValues = CommonValues.getInstance();
                     commonValues.setUser(customer);
                     return true;
                 }else {
                     view.displayMessage("Incorrect name or password");
                     return false;
                 }
             }else {
                 view.displayMessage("Incorrect name or password");
                 return false;
             }

        }else{
            view.displayMessage("Incorrect name or password");
            return false;
        }
    }

    @Override
    public boolean createNewUser(String userName, String Password, String PasswordConfirmation) {
        if(Password.equals(PasswordConfirmation)){
            db = AppDatabase.getInMemoryDatabase(view.getContext());

            CommonValues commonValues = CommonValues.getInstance();
            Customer user = new Customer(userName,generateAccountNumber());
            commonValues.setUser(user);


            db.customerModel().addCustomer(user);
            db.dataModel().addData(encryption.encryptAcountNumber(user.getAccountNumber(),Password));
            return true;

        }else{
            return false;
        }
    }

    private String generateAccountNumber() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= 12 ; i++) {
            s.append(new Random().nextInt(10));
            if(i+1 % 5 == 0){
                s.append("-");
            }
        }
        Log.d("Test", "generateAccountNumber: " + s);
        return s.toString();
    }
}
