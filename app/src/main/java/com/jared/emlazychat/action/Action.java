package com.jared.emlazychat.action;

import android.content.Context;

import java.util.Map;

/**
 * Created by jared on 16/3/11.
 */
public abstract class Action {

    public abstract String getAction();

    public abstract void doAction(Context context, Map<String, Object> data);
}
