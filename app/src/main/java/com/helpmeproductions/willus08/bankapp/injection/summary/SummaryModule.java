package com.helpmeproductions.willus08.bankapp.injection.summary;

import com.helpmeproductions.willus08.bankapp.view.activities.summary.SummaryPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SummaryModule {
    @Provides
    SummaryPresenter summaryPresenterProvider(){
        return new SummaryPresenter();
    }
}
