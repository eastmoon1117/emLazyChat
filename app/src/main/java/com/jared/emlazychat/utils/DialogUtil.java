package com.jared.emlazychat.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by jared on 16/2/26.
 */
public class DialogUtil {
    public Dialog getProgressDialog(Context context, String title,
                                    String message, boolean indeterminate, boolean cancelable,
                                    DialogInterface.OnCancelListener cancelListener) {
        return ProgressDialog.show(context, title, message, indeterminate, cancelable, cancelListener);
    }
}
