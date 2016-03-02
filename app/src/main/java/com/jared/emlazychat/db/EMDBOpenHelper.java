package com.jared.emlazychat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jared on 16/3/2.
 */
public class EMDBOpenHelper extends SQLiteOpenHelper {

    private EMDBOpenHelper(Context context) {
        super(context, EMDB.NAME, null, EMDB.VERSON);
    }

    private static EMDBOpenHelper instance;
    public static EMDBOpenHelper getInstance(Context context) {
        if(instance == null) {
            synchronized (EMDBOpenHelper.class) {
                if(instance == null) {
                    instance = new EMDBOpenHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EMDB.Account.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(EMDB.Friend.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
