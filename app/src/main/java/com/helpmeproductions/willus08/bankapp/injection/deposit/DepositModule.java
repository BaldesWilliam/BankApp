package com.helpmeproductions.willus08.bankapp.injection.deposit;

import com.helpmeproductions.willus08.bankapp.view.activities.deposit.DepositPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DepositModule {
    @Provides
    DepositPresenter depositPresenterProvider(){
        return new DepositPresenter();
    }
}
