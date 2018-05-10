package com.helpmeproductions.willus08.bankapp.injection.login;

import com.helpmeproductions.willus08.bankapp.view.activities.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    @Provides
    LoginPresenter loginPresenterProvider(){
        return new LoginPresenter();
    }
}
