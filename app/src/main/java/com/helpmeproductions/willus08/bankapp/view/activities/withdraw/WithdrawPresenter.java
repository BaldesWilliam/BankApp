package com.helpmeproductions.willus08.bankapp.view.activities.withdraw;


import com.helpmeproductions.willus08.bankapp.model.CommonValues;
import com.helpmeproductions.willus08.bankapp.model.Customer;
import com.helpmeproductions.willus08.bankapp.model.Transaction;

import java.util.Calendar;

public class WithdrawPresenter implements WithdrawContract.Presenter{
    private WithdrawContract.View view;

    @Override
    public void addView(WithdrawContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void makeWithdrawal(float amount, String description) {
        CommonValues commonValues = CommonValues.getInstance();
        Calendar calendar = Calendar.getInstance();
        String s = calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        Customer user = commonValues.getUser();
        Transaction withdraw = new Transaction(
                "Withdraw",
                description,
                -1*amount,
                s
        );
        user.addTransaction(withdraw);
        if(user.getBalance() < 0f){
            Transaction overdraft = new Transaction(
                    "Withdraw",
                    "Overdraft Fee",
                    -32,
                    s
            );
            user.addTransaction(overdraft);
            view.displayMessage("Withdrawal Made. Incurred overdraft");
        }else{
            view.displayMessage("Withdrawal Made");
        }
    }
}
