package com.helpmeproductions.willus08.bankapp.injection.deposit;

import com.helpmeproductions.willus08.bankapp.view.activities.deposit.Deposit;

import dagger.Component;

@Component(modules = DepositModule.class)
public interface DepositComponent {
    void inject(Deposit deposit);
}
