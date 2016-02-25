package com.jared.emlazychat.base;

import android.app.IntentService;

import com.jared.emlazychat.ChatApplication;

/**
 * Created by jared on 16/2/22.
 */
public abstract class BaseIntentService extends IntentService {
    public BaseIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ((ChatApplication)getApplication()).addService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ChatApplication)getApplication()).removeService(this);
    }
}
