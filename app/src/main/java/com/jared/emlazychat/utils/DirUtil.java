package com.jared.emlazychat.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by jared on 16/3/16.
 */
public class DirUtil {
    public static String getTaskDir(Context context) {
        String dir = getDir(context, "task");
        return dir;
    }

    public static String getIconDir(Context context) {
        String dir = getDir(context, "icon");
        return dir;
    }

    private static String getDir(Context context, String path) {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equalsIgnoreCase(state)) {
            File root = Environment.getExternalStorageDirectory();

            File dir = new File(root, "Android/data/"
                        + context.getPackageName() + "/" + path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            return dir.getAbsolutePath();
        } else {
            File dir = new File(context.getFilesDir(), path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            return dir.getAbsolutePath();
        }
    }
}
