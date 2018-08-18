package com.garhorne.mvptest.presenter;

import android.os.Handler;
import android.os.Looper;

import com.garhorne.mvptest.model.IUser;
import com.garhorne.mvptest.model.UserModel;
import com.garhorne.mvptest.view.ILoginView;

public class LoginPresenterCompl implements ILoginPresenter{

    ILoginView loginView;
    IUser iUser;
    Handler handler;

    public LoginPresenterCompl(ILoginView loginView) {
        this.loginView = loginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    private void initUser() {
        iUser = new UserModel("mvp","mvp");
    }

    @Override
    public void clear() {
        loginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        Boolean isLoginSuccess = true;
        final int code = iUser.checkUservalidity(name,password);
        if (code != 0){
            isLoginSuccess = false;
        }
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.onLoginResult(result,code);
            }
        },3000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        loginView.onsetProgressBarVisiblity(visiblity);
    }
}
