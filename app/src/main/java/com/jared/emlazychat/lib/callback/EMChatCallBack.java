package com.jared.emlazychat.lib.callback;

/**
 * Created by jared on 16/3/4.
 */
public interface EMChatCallBack {
    void onSuccess();

    void onProgress(int progress);

    void onError(int error, String msg);
}
