package com.helpmeproductions.willus08.bankapp.view.activities.summary;


import com.helpmeproductions.willus08.bankapp.model.CommonValues;
import com.helpmeproductions.willus08.bankapp.model.Customer;

public class SummaryPresenter implements SummaryContract.Presenter{
    private  SummaryContract.View view;

    @Override
    public void addView(SummaryContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public Customer getCustomer() {
        CommonValues commonValues = CommonValues.getInstance();
        return commonValues.getUser();
    }

    @Override
    public void logoutCustomer() {
        CommonValues commonValues = CommonValues.getInstance();
        commonValues.setUser(null);
        view.displayMessage("Than you for using the app.\n Please come Again");
    }
}
