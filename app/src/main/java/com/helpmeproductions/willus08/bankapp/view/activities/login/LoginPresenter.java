package com.helpmeproductions.willus08.bankapp.view.activities.login;


import android.arch.persistence.room.Room;
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
    private String TAG = "Login Presenter";

    @Override
    public void addView(LoginContract.View view) {
        this.view = view;
        db = AppDatabase.getPersistantDatabase(view.getContext());
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public boolean checkCredentials(String UserName, String Password) {
        DataStorage data = db.dataModel().getWithPassword(Password);
        if (data != null){
            Log.d(TAG, "checkCredentials: found credentials ");
            byte[] encryptedAccountNumber = data.getEncryptedAccountNumber();

             String decryptedNumber =  encryption.decrypt(
                  encryptedAccountNumber,
                  data.getDecryptingCipher(),
                  Password);
             if(!decryptedNumber.equals("")){
                 Log.d(TAG, "checkCredentials: decrypted account number");
                 Customer customer = db.customerModel().loadCustomerByAcountNumber(decryptedNumber);
                 if(customer.getName().equals(UserName)){
                     Log.d(TAG, "checkCredentials: account matches user");
                     CommonValues commonValues = CommonValues.getInstance();
                     commonValues.setUser(customer);
                     return true;
                 }else {
                     Log.d(TAG, "checkCredentials: account found but wrong user");
                     view.displayMessage("Incorrect name or password");
                     return false;
                 }
             }else {
                 Log.d(TAG, "checkCredentials: can not decrypt acount number");
                 view.displayMessage("Incorrect name or password");
                 return false;
             }

        }else{
            Log.d(TAG, "checkCredentials: acount does not exist");
            view.displayMessage("Incorrect name or password");
            return false;
        }
    }

    @Override
    public boolean createNewUser(String userName, String Password, String PasswordConfirmation) {
        if(Password.equals(PasswordConfirmation)){
            CommonValues commonValues = CommonValues.getInstance();
            String accountNumber = generateAccountNumber();

            Customer user = new Customer(userName,accountNumber);
            commonValues.setUser(user);


            db.customerModel().addCustomer(user);
            db.dataModel().addData(encryption.encryptAcountNumber(Password,accountNumber));
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
        Log.d(TAG, "generateAccountNumber: " + s);
        return s.toString();
    }
}
