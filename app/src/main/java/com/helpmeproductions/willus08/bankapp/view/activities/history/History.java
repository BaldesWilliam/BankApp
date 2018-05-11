package com.helpmeproductions.willus08.bankapp.view.activities.history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.injection.history.DaggerHistoryComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class History extends AppCompatActivity implements HistoryContract.View{

    @Inject
    HistoryPresenter presenter;

    @BindView(R.id.tvHistoryWarning)TextView warning;

    @BindView(R.id.rvHistoryItems)RecyclerView transactions;

    RecyclerView.LayoutManager layoutManager;
    HistoryAdapter adapter;
    DefaultItemAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        setupDagger();
        setupUI();
    }

    public void leaveHistory(View view) {
        finish();
    }

    @Override
    public void displayMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerHistoryComponent.create().inject(this);
        presenter.addView(this);

    }

    @Override
    public void setupUI() {
        if(presenter.checkTransactions()){
            warning.setVisibility(View.GONE);

            adapter = new HistoryAdapter(presenter.getTransactions());
            layoutManager = new LinearLayoutManager(this);
            animator = new DefaultItemAnimator();

            transactions.setAdapter(adapter);
            transactions.setLayoutManager(layoutManager);
            transactions.setItemAnimator(animator);
            transactions.setVisibility(View.VISIBLE);

        }else {
            transactions.setVisibility(View.GONE);
            warning.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
