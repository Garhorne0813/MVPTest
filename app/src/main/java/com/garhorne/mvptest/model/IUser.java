package com.garhorne.mvptest.model;

public interface IUser {
    String getName();
    String getPassword();
    int checkUservalidity(String name,String password);
}
