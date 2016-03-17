package com.jared.emlazychat.utils;

import com.jared.emlazychat.domain.NetTask;

import java.util.HashMap;

/**
 * Created by jared on 16/3/16.
 */
public class BackTaskFactory {
    public static NetTask userNameChangeTask(String name) {
        NetTask task = new NetTask();
        task.setMethod("POST");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);

        task.setParams(params);
        task.setPath("/user/name");
        task.setProtocol("HTTP");
        return task;
    }
}
