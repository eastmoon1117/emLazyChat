package com.jared.emlazychat.domain;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jared on 16/2/27.
 */
public class Account implements Parcelable{

    private String account;
    private String name;
    private String icon;
    private int sex;
    private String sign;
    private String area;
    private String token;
    private boolean current;

    public Account() {

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    private Account(Parcel parcel) {
        Bundle val = parcel.readBundle();
        account = val.getString("account");
        name = val.getString("name");
        icon = val.getString("icon");
        sex = val.getInt("sex");
        sign = val.getString("sign");
        area = val.getString("area");
        token = val.getString("token");
        current = val.getBoolean("current");
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle val = new Bundle();
        val.putString("account", account);
        val.putString("name", name);
        val.putString("icon", icon);
        val.putInt("sex", sex);
        val.putString("sign", sign);
        val.putString("area", area);
        val.putString("token", token);
        val.putBoolean("current", current);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    Parcelable.Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel parcel) {
            return new Account(parcel);
        }

        @Override
        public Account[] newArray(int i) {
            return new Account[i];
        }
    };
}
