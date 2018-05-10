package com.helpmeproductions.willus08.bankapp.view.activities.deposit;


import com.helpmeproductions.willus08.bankapp.model.CommonValues;
import com.helpmeproductions.willus08.bankapp.model.Customer;
import com.helpmeproductions.willus08.bankapp.model.Transaction;

import java.util.Calendar;

public class DepositPresenter implements DepositContract.Presenter {
    private DepositContract.View view;

    @Override
    public void addView(DepositContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void makeDeposit(float amount, String description) {
        CommonValues commonValues = CommonValues.getInstance();
        Calendar calendar = Calendar.getInstance();
        String s = calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        Customer customer = commonValues.getUser();
        Transaction transaction = new Transaction(
                "Deposit",
                description,
                amount,
                s);
        customer.addTransaction(transaction);
        commonValues.setUser(customer);
        view.displayMessage("Deposit Made");
    }
}
