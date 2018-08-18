package com.garhorne.mvptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.garhorne.mvptest.presenter.ILoginPresenter;
import com.garhorne.mvptest.presenter.LoginPresenterCompl;
import com.garhorne.mvptest.view.ILoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ILoginView {

    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private Button btn_clear;
    private ProgressBar progressBar;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_account = (EditText)findViewById(R.id.account);
        et_password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.login);
        btn_clear = (Button)findViewById(R.id.clear);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);;

        btn_login.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.VISIBLE);

        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btn_login.setEnabled(false);
                btn_clear.setEnabled(false);
                loginPresenter.doLogin(et_account.getText().toString(),et_password.getText().toString());
                break;
            case R.id.clear:
                loginPresenter.clear();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClearText() {
        et_account.setText("");
        et_password.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        btn_login.setEnabled(true);
        btn_clear.setEnabled(true);
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,HomeActivity.class));
        } else {
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onsetProgressBarVisiblity(int visiblity) {
        progressBar.setVisibility(visiblity);
    }
}
