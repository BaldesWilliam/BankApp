package com.helpmeproductions.willus08.bankapp.injection.summary;

import com.helpmeproductions.willus08.bankapp.view.activities.summary.Summary;

import dagger.Component;

@Component(modules = SummaryModule.class)
public interface SummaryComponent {
    void inject(Summary summary);
}
