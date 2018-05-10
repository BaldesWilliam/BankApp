package com.helpmeproductions.willus08.bankapp.view.activities.withdraw;


import com.helpmeproductions.willus08.bankapp.BasePresenter;
import com.helpmeproductions.willus08.bankapp.BaseView;

public interface WithdrawContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{
        void makeWithdrawal(float amount, String description);
    }
}
