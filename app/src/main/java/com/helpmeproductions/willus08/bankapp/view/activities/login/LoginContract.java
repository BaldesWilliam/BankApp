package com.helpmeproductions.willus08.bankapp.view.activities.login;

import android.content.Context;

import com.helpmeproductions.willus08.bankapp.BasePresenter;
import com.helpmeproductions.willus08.bankapp.BaseView;

public interface LoginContract {
    interface View extends BaseView{
        void signIn();
        Context getContext();
    }
    interface Presenter extends BasePresenter<View>{
        boolean checkCredentials(String UserName, String Password);
        boolean createNewUser(String userName, String Password, String PasswordConfirmation);

    }
}
