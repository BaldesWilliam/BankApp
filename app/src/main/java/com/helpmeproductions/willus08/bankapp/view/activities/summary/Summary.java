package com.helpmeproductions.willus08.bankapp.view.activities.summary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.injection.summary.DaggerSummaryComponent;
import com.helpmeproductions.willus08.bankapp.model.Customer;
import com.helpmeproductions.willus08.bankapp.view.activities.deposit.Deposit;
import com.helpmeproductions.willus08.bankapp.view.activities.history.History;
import com.helpmeproductions.willus08.bankapp.view.activities.withdraw.Withdraw;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Summary extends AppCompatActivity implements SummaryContract.View {

    @Inject
    SummaryPresenter presenter;

    @BindView(R.id.tvWelcome)
    TextView welcome;

    @BindView(R.id.tvBalance)
    TextView balanceInfo;

    @BindView(R.id.tvAccountNum)
    TextView accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ButterKnife.bind(this);
        setupDagger();
        setupUi();
    }

    @Override
    public void displayMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerSummaryComponent.create().inject(this);
        presenter.addView(this);
    }

    @Override
    public void setupUi() {
        Customer user = presenter.getCustomer();
        String welcomeMessage = "Welcome to the Bank App:\n " + user.getName();
        String accountNumber = "in account # ****-****-"+ user.getAccountNumber().substring(10);
        String balanceMessage = String.format("$ %.2f" , user.getBalance());

        if(user.getBalance()>=0f){
            balanceInfo.setTextColor(Color.BLACK);
        }else {
            balanceInfo.setTextColor(Color.RED);
        }

        welcome.setText(welcomeMessage);
        balanceInfo.setText(balanceMessage);
        accountInfo.setText(accountNumber);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setupUi();
    }

    @Override
    public void transition(String location) {
        Intent intent;
        switch (location){
            case"History":
                intent = new Intent(this, History.class);
                startActivity(intent);
                break;
            case"Deposit":
                intent = new Intent(this, Deposit.class);
                startActivity(intent);
                break;
            case"Withdraw":
                intent = new Intent(this, Withdraw.class);
                startActivity(intent);
                break;
            case"Logout":
                presenter.logoutCustomer();
                finish();
                break;
            default:
                displayMessage("Error occurred prosing button pressed");

        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    public void changeScreen(View view) {
        switch (view.getId()){
            case R.id.btnDeposit:
                transition("Deposit");
                break;
            case R.id.btnHistory:
                transition("History");
                break;
            case R.id.btnWithdraw:
                transition("Withdraw");
                break;
            case R.id.btnLogout:
                transition("Logout");
                break;
            default:
                displayMessage("Error in button select");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
