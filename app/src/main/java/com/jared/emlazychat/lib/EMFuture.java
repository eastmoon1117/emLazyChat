package com.jared.emlazychat.lib;

/**
 * Created by jared on 16/3/3.
 */
public interface EMFuture {

    boolean isCancelled();

    boolean cancel(boolean mayInterruptIfRunning);

    boolean isFinished();
}
