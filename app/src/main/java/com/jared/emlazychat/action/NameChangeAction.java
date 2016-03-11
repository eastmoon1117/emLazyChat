package com.jared.emlazychat.action;

import android.content.Context;

import java.util.Map;

/**
 * Created by jared on 16/3/11.
 */
public class NameChangeAction extends Action {

    @Override
    public String getAction() {
        return "nameChange";
    }

    @Override
    public void doAction(Context context, Map<String, Object> data) {
        if (data == null) {
            return;
        }

        String receiver = data.get("receiver").toString();
        String sender = data.get("sender").toString();
        String name = data.get("name").toString();

    }
}
