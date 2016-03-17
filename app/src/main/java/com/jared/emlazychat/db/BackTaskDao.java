package com.jared.emlazychat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jared.emlazychat.domain.BackTask;

/**
 * Created by jared on 16/3/16.
 */
public class BackTaskDao {
    private EMDBOpenHelper helper;

    public BackTaskDao(Context context) {
        helper = EMDBOpenHelper.getInstance(context);
    }

    public void addTask(BackTask backTask) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMDB.BackTask.COLUMN_OWNER, backTask.getOwner());
        values.put(EMDB.BackTask.COLUMN_PATH, backTask.getPath());
        values.put(EMDB.BackTask.COLUMN_STATE, backTask.getState());
        backTask.setId(db.insert(EMDB.BackTask.TABLE_NAME, null, values));
    }

    public void updateTask(BackTask backTask) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMDB.BackTask.COLUMN_OWNER, backTask.getOwner());
        values.put(EMDB.BackTask.COLUMN_PATH, backTask.getPath());
        values.put(EMDB.BackTask.COLUMN_STATE, backTask.getState());

        String whereClause = EMDB.BackTask.COLUMN_ID + "=?";
        String[] whereArgs = new String[]{backTask.getId()+""};
        db.update(EMDB.BackTask.TABLE_NAME, values, whereClause, whereArgs);
    }

    public void updateState(long id, int state) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMDB.BackTask.COLUMN_STATE, state);
        String whereClause = EMDB.BackTask.COLUMN_ID + "=?";
        String[] whereArgs = new String[]{id + ""};
        db.update(EMDB.BackTask.TABLE_NAME, values, whereClause, whereArgs);
    }

    public Cursor query(String owner) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from" + EMDB.BackTask.TABLE_NAME + "where"
                + EMDB.BackTask.COLUMN_OWNER + "=?";
        return db.rawQuery(sql, new String[]{owner});
    }

    public Cursor query(String owner, int state) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from" + EMDB.BackTask.TABLE_NAME + "where"
                + EMDB.BackTask.COLUMN_OWNER + "=? and "
                + EMDB.BackTask.COLUMN_STATE + "=?";
        return db.rawQuery(sql, new String[]{owner, "0"});
    }
}
