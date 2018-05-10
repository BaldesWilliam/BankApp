package com.helpmeproductions.willus08.bankapp.view.activities.summary;


import com.helpmeproductions.willus08.bankapp.BasePresenter;
import com.helpmeproductions.willus08.bankapp.BaseView;
import com.helpmeproductions.willus08.bankapp.model.Customer;

public interface SummaryContract {
    interface View extends BaseView{

        void setupUi();
        void transition(String location);

    }
    interface Presenter extends BasePresenter<View>{
        Customer getCustomer();
        void logoutCustomer();
    }
}
