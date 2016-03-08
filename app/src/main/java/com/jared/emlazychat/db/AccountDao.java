package com.jared.emlazychat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jared.emlazychat.domain.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jared on 16/3/2.
 */
public class AccountDao {
    private final static String TAG = "AccountDao";
    private EMDBOpenHelper helper;

    public AccountDao(Context context) {
        helper = EMDBOpenHelper.getInstance(context);
    }

    private void setAccountContent(Account account, Cursor cursor) {
        account.setAccount(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_ACCOUNT)));
        account.setArea(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_AREA)));
        account.setCurrent(cursor.getInt(
                cursor.getColumnIndex(EMDB.Account.COLUMN_CURRENT)) == 1);
        account.setIcon(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_ICON)));
        account.setName(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_NAME)));
        account.setSex(cursor.getInt(
                cursor.getColumnIndex(EMDB.Account.COLUMN_SEX)));
        account.setSign(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_SIGN)));
        account.setToken(cursor.getString(
                cursor.getColumnIndex(EMDB.Account.COLUMN_TOKEN)));
    }

    public List<Account> getAllAccount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from " + EMDB.Account.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        List<Account> list = null;
        if(cursor != null) {
            while (cursor.moveToNext()) {
                if(list == null) {
                    list = new ArrayList<Account>();
                }

                Account account = new Account();
                setAccountContent(account, cursor);
                list.add(account);
            }
        }
        return list;
    }

    public Account getCurrentAccount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from " + EMDB.Account.TABLE_NAME + " where "
                + EMDB.Account.COLUMN_CURRENT + "=1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Account account = new Account();
                setAccountContent(account, cursor);
                Log.d(TAG, "getCurrentAccount"+":"+account.getAccount()+":"+account.getName());
                Log.d(TAG, account.getArea()+":"+account.getSign()+":"+account.getToken());
                return account;
            }
        }
        return null;
    }

    public Account getByAccount(String account_name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from " + EMDB.Account.TABLE_NAME + " where "
                + EMDB.Account.COLUMN_ACCOUNT + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{account_name});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Account account = new Account();
                setAccountContent(account, cursor);
                Log.d(TAG, "getByAccount"+":"+account.getAccount()+":"+account.getName());
                Log.d(TAG, account.getArea()+":"+account.getSign()+":"+account.getToken());
                return account;
            }
        }
        return null;
    }

    private void setAccountValues(Account account, ContentValues values) {
        values.put(EMDB.Account.COLUMN_ACCOUNT, account.getAccount());
        values.put(EMDB.Account.COLUMN_AREA,    account.getArea());
        values.put(EMDB.Account.COLUMN_ICON,    account.getIcon());
        values.put(EMDB.Account.COLUMN_NAME,    account.getName());
        values.put(EMDB.Account.COLUMN_SEX,     account.getSex());
        values.put(EMDB.Account.COLUMN_SIGN,    account.getSign());
        values.put(EMDB.Account.COLUMN_TOKEN,   account.getToken());
        values.put(EMDB.Account.COLUMN_CURRENT, account.isCurrent() ? 1: 0);
        Log.d(TAG, "setAccountValues"+":"+account.getAccount()+":"+account.getName());
        Log.d(TAG, account.getArea()+":"+account.getSign()+":"+account.getToken());
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        setAccountValues(account, values);

        db.insert(EMDB.Account.TABLE_NAME, null, values);
    }

    public void updateAccount(Account account) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        setAccountValues(account, values);

        String where = EMDB.Account.COLUMN_ACCOUNT + "=?";
        String[] whereArgs = new String[] {account.getAccount()};
        db.update(EMDB.Account.TABLE_NAME, values, where, whereArgs);
    }

    public void deleteAccount(Account account) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = EMDB.Account.COLUMN_ACCOUNT + "=?";
        String[] whereArgs = new String[] {account.getAccount()};
        db.delete(EMDB.Account.TABLE_NAME, where, whereArgs);
    }
}
