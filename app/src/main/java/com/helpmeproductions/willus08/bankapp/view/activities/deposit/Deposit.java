package com.helpmeproductions.willus08.bankapp.view.activities.deposit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.injection.deposit.DaggerDepositComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Deposit extends AppCompatActivity implements DepositContract.View{

    @Inject
    DepositPresenter presenter;

    @BindView(R.id.etDepositAmount)
    EditText amount;

    @BindView(R.id.etDepositDescription)
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    public void displayMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerDepositComponent.create().inject(this);
        presenter.addView(this);
    }

    public void makeDeposit(View view) {
        if(amount.getText() != null) {
            presenter.makeDeposit(
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
