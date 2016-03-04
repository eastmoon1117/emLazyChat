package com.jared.emlazychat.lib.callback;

import java.lang.reflect.ParameterizedType;

/**
 * Created by jared on 16/3/4.
 */
public abstract class EMObjectCallBack<T> {
    private Class<T> clazz;

    public EMObjectCallBack() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>)type.getActualTypeArguments()[0];
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int error, String msg);

    public Class<T> getClazz() {
        return clazz;
    }
}
