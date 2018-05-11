package com.helpmeproductions.willus08.bankapp.view.activities.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.injection.login.DaggerLoginComponent;
import com.helpmeproductions.willus08.bankapp.view.activities.summary.Summary;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = "Login";
    @Inject
    LoginPresenter presenter;

    @BindView(R.id.etLoginName)
    EditText name;

    @BindView(R.id.etLoginPassword)
    EditText password;

    @BindView(R.id.etLoginNamePasswordConfirmation)
    EditText passwordConfirm;

    @BindView(R.id.tvSwitch)
    TextView switchState;

    @BindView(R.id.btnSignIn)
    Button loginButton;

    String state = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupDagger();
    }

    @Override
    protected void onStart() {
        super.onStart();
        state = "Login";
        name.setText("");
        password.setText("");
        passwordConfirm.setText("");
        passwordConfirm.setVisibility(View.GONE);
        switchState.setText(getResources().getString(R.string.switch_to_create));
        loginButton.setText(getResources().getString(R.string.login_button));

    }

    @Override
    public void displayMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerLoginComponent.create().inject(this);
        presenter.addView(this);
    }

    @Override
    public void signIn() {

        Intent intent = new Intent(this,Summary.class);
        startActivity(intent);

    }

    @Override
    public Context getContext() {
        return this;
    }

    public void completeAction(View view) {
        if(state.equals("Login")){
            String lName = name.getText().toString();
            String lPassword = password.getText().toString();
            Log.d(TAG, "completeAction: trying to log on ");
            if(presenter.checkCredentials(lName,lPassword)){
                Log.d(TAG, "completeAction: Logging on");
                signIn();
            }else{
                displayMessage("Wrong username or password");
            }

        }else{
            String cName = name.getText().toString();
            String cPassword = password.getText().toString();
            String cConfirmPassword = passwordConfirm.getText().toString();
            Log.d(TAG, "completeAction: trying to create account ");
            if(presenter.createNewUser(cName,cPassword,cConfirmPassword)){
                Log.d(TAG, "completeAction: account created");
                signIn();
            }else{
                displayMessage("Could not create an account");
            }
        }
    }

    public void switchState(View view) {
        if(state.equals("Login")){
            state = "Create";
            passwordConfirm.setVisibility(View.VISIBLE);
            switchState.setText(getResources().getString(R.string.switch_to_login));
            loginButton.setText(getResources().getString(R.string.create_button));

        }else {
            state = "Create";
            passwordConfirm.setVisibility(View.GONE);
            switchState.setText(getResources().getString(R.string.switch_to_create));
            loginButton.setText(getResources().getString(R.string.login_button));

        }
    }
}
