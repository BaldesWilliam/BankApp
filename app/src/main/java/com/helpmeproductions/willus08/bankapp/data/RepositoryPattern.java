package com.helpmeproductions.willus08.bankapp.data;


public interface RepositoryPattern<T>{

    void add(T item);

    void remove(T item);

}
