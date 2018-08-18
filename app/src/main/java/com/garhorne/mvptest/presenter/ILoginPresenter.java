package com.garhorne.mvptest.presenter;

public interface ILoginPresenter {
    void clear();
    void doLogin(String name,String password);
    void setProgressBarVisiblity(int visiblity);

}
