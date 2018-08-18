package com.garhorne.mvptest.view;

public interface ILoginView {
    void onClearText();
    void onLoginResult(Boolean result,int code);
    void onsetProgressBarVisiblity(int visiblity);
}
