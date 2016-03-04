package com.jared.emlazychat.lib;

import android.content.Context;

/**
 * Created by jared on 16/3/3.
 */
public class EMChat {
    private static EMChat instance;
    private static Context context;

    public static EMChat getInstance() {
        if (instance == null) {
            synchronized (EMChat.class) {
                if (instance == null) {
                    instance = new EMChat();
                }
            }
        }
        return instance;
    }

    protected static Context getContext() {
        if (EMChat.context == null) {
            throw new RuntimeException(
                    "请在Application的onCreate方法中调用" +
                            "HMChat.getInstance().init(context)初始化聊天引擎.");
        }
        return EMChat.context;
    }

    public void init(Context context) {
        EMChat.context = context;
    }
}
