package com.helpmeproductions.willus08.bankapp.injection.withdraw;

import com.helpmeproductions.willus08.bankapp.view.activities.withdraw.WithdrawPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WithdrawModule {
    @Provides
    WithdrawPresenter withdrawPresenterProvider(){
        return new WithdrawPresenter();
    }
}
