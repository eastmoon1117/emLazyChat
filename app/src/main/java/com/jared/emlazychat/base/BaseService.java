package com.jared.emlazychat.base;

import android.app.Service;

import com.jared.emlazychat.ChatApplication;

/**
 * Created by jared on 16/2/22.
 */
public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        ((ChatApplication) getApplication()).addService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ChatApplication) getApplication()).removeService(this);
    }
}