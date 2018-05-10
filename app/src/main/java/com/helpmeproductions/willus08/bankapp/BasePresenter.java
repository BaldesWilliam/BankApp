package com.helpmeproductions.willus08.bankapp;



public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
