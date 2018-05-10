package com.helpmeproductions.willus08.bankapp.view.activities.deposit;


import com.helpmeproductions.willus08.bankapp.BasePresenter;
import com.helpmeproductions.willus08.bankapp.BaseView;

public interface DepositContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

        void makeDeposit(float amount, String description);

    }
}
