package com.helpmeproductions.willus08.bankapp.injection.withdraw;

import com.helpmeproductions.willus08.bankapp.view.activities.withdraw.Withdraw;

import dagger.Component;

@Component(modules = WithdrawModule.class)
public interface WithdrawComponent {
    void inject(Withdraw withdraw);
}
