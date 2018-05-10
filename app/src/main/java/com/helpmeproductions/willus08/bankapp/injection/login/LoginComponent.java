package com.helpmeproductions.willus08.bankapp.injection.login;

import com.helpmeproductions.willus08.bankapp.view.activities.login.Login;

import dagger.Component;

@Component(modules = LoginModule.class)
public interface LoginComponent {
    void inject(Login login);
}
