package com.example.myappz;

public interface BasePresenter <T extends BaseView>{
    void onAttach(T view);
    void onDetach();
}
