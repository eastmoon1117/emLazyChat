package com.jared.emlazychat.lib.future;

import com.jared.emlazychat.lib.EMFuture;
import com.loopj.android.http.RequestHandle;

/**
 * Created by jared on 16/3/3.
 */
public class HttpFuture implements EMFuture {

    private RequestHandle handle;

    public HttpFuture(RequestHandle handle) {
        this.handle = handle;
    }

    public void test() {
        handle.isFinished();
    }

    @Override
    public boolean isCancelled() {
        return handle == null || handle.isCancelled();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return handle == null || handle.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isFinished() {
        return handle == null || handle.isFinished();
    }

}
