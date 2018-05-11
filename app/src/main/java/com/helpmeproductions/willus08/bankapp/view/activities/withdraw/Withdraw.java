package com.helpmeproductions.willus08.bankapp.view.activities.withdraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.injection.withdraw.DaggerWithdrawComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Withdraw extends AppCompatActivity implements WithdrawContract.View{

    @Inject
    WithdrawPresenter presenter;

    @BindView(R.id.etWithdrawAmount)
    EditText amount;

    @BindView(R.id.etWithdrawDescription)
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    public void setupDagger() {
        DaggerWithdrawComponent.create().inject(this);
        presenter.addView(this);
    }

    public void withdrawMoney(View view) {
        if(amount.getText() != null) {
            presenter.makeWithdrawal(
                    Float.parseFloat(amount.getText().toString()),
                    description.getText().toString()
            );
            amount.setText("");
            description.setText("");
        }else{
            displayMessage("input an amount");
        }
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void displayMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
