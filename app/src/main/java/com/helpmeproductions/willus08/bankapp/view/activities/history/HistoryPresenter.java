package com.helpmeproductions.willus08.bankapp.view.activities.history;


import com.helpmeproductions.willus08.bankapp.model.CommonValues;
import com.helpmeproductions.willus08.bankapp.model.Transaction;

import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;

    @Override
    public void addView(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public List<Transaction> getTransactions() {
        CommonValues commonValues = CommonValues.getInstance();
        return commonValues.getUser().getTransactionList();
    }

    @Override
    public boolean checkTransactions() {
        CommonValues commonValues = CommonValues.getInstance();
        return commonValues.getUser().getTransactionList().size() > 0;
    }
}
