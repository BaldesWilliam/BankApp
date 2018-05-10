package com.helpmeproductions.willus08.bankapp.injection.history;


import com.helpmeproductions.willus08.bankapp.view.activities.history.HistoryPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HistoryModule {
    @Provides
    HistoryPresenter historyPresenterProvider(){
        return new HistoryPresenter();
    }
}
