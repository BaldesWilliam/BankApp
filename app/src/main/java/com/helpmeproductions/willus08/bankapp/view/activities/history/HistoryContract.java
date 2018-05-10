package com.helpmeproductions.willus08.bankapp.view.activities.history;


import com.helpmeproductions.willus08.bankapp.BasePresenter;
import com.helpmeproductions.willus08.bankapp.BaseView;
import com.helpmeproductions.willus08.bankapp.model.Transaction;

import java.util.List;

public interface HistoryContract {
    interface View extends BaseView{
        void setupUI();
    }
    interface Presenter extends BasePresenter<View>{
        List<Transaction> getTransactions();
        boolean checkTransactions();
    }
}
