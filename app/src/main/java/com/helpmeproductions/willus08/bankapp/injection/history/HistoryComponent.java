package com.helpmeproductions.willus08.bankapp.injection.history;

import com.helpmeproductions.willus08.bankapp.view.activities.history.History;

import dagger.Component;

@Component(modules = HistoryModule.class)
public interface HistoryComponent {
    void inject(History history);
}
