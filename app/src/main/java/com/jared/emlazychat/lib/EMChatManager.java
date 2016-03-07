package com.jared.emlazychat.lib;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.jared.emlazychat.lib.callback.EMObjectCallBack;
import com.jared.emlazychat.lib.future.HttpFuture;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jared on 16/3/3.
 */
public class EMChatManager {

    private static final String TAG = "EMChatManager";

    private static EMChatManager instance;
    private Context context;
    private Map<String, String> heads = new HashMap<String, String>();

    private String authSequeue;

    private Thread mainThread;
    private Handler handler = new Handler();

    public static EMChatManager getInstance() {
        if (instance == null) {
            synchronized (EMChatManager.class) {
                if (instance == null) {
                    instance = new EMChatManager();
                }
            }
        }
        return instance;
    }

    private EMChatManager() {
        context = EMChat.getContext();
        mainThread = Thread.currentThread();
    }

    public void initAccount(String account, String token) {
        heads.put("account", account);
        heads.put("token", token);
    }

    /**
     * login
     */
    public EMFuture login(String account, String password,
                          final EMObjectCallBack callBack) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30 * 1000);
        client.setMaxRetriesAndTimeout(5, 30 * 1000);
        client.setResponseTimeout(30 * 1000);
        String url = EMURL.URL_HTTP_LOGIN;
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("password", password);
        Log.d(TAG, "login");
        return new HttpFuture(client.post(context, url, params,
                newObjectResponseHandler(callBack)));
    }

    /**
     * register
     */
    public EMFuture register(String account, String password,
                             final EMObjectCallBack callBack) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30 * 1000);
        client.setMaxRetriesAndTimeout(5, 30 * 1000);
        client.setResponseTimeout(30 * 1000);
        String url = EMURL.URL_HTTP_REGISTER;
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("password", password);
        Log.d(TAG, "register");
        return new HttpFuture(client.post(context, url, params,
                newObjectResponseHandler(callBack)));
    }

    private TextHttpResponseHandler newObjectResponseHandler(
            final EMObjectCallBack callBack) {
        return new TextHttpResponseHandler("utf-8") {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String s) {
                Log.d(TAG, "newObjectResponseHandler" + s);

                if (statusCode == 200) {
                    JsonParser parser = new JsonParser();
                    JsonObject root = parser.parse(s).getAsJsonObject();
                    if (null == root) {
                        if (callBack != null) {
                            callBack.onError(EMError.ERROR_SERVER, "服务器异常");
                        }
                    } else {
                        if (callBack != null) {
                            JsonPrimitive flagObj = root.getAsJsonPrimitive("flag");
                            boolean flag = flagObj.getAsBoolean();
                            if(flag) {
                                JsonObject dataObj = root.getAsJsonObject("data");
                                if(dataObj == null) {
                                    callBack.onSuccess(null);
                                } else {
                                    Object data = new Gson().fromJson(dataObj,
                                            callBack.getClazz());
                                    Log.d(TAG, "newObjectResponseHandler" + dataObj);
                                    callBack.onSuccess(data);
                                }
                            } else {
                                JsonPrimitive errCodeObj = root
                                        .getAsJsonPrimitive("errorCode");
                                JsonPrimitive errStringObj = root
                                        .getAsJsonPrimitive("errorString");
                                int errorCode = errCodeObj.getAsInt();
                                String errorString =  errStringObj.getAsString();
                                callBack.onError(errorCode, errorString);
                            }
                        }
                    }
                }
                else {
                    if(callBack != null) {
                        callBack.onError(EMError.ERROR_SERVER, "服务器异常");
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String s,
                                  Throwable throwable) {
                if(callBack != null) {
                    callBack.onError(EMError.ERROR_SERVER, "服务器异常: "
                            + throwable.getMessage());
                }
            }
        };
    }
}
