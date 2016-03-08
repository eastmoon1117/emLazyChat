package com.jared.emlazychat.db;

/**
 * Created by jared on 16/3/2.
 */
public interface EMDB {
    String NAME = "em.db";
    int VERSON = 1;

    public interface Account {
        String TABLE_NAME = "account";

        String COLUMN_ID      = "_id";
        String COLUMN_ACCOUNT = "account";
        String COLUMN_NAME    = "name";
        String COLUMN_SEX     = "sex";
        String COLUMN_ICON    = "icon";
        String COLUMN_SIGN    = "sign";
        String COLUMN_AREA    = "area";
        String COLUMN_TOKEN   = "token";
        String COLUMN_CURRENT = "current";

        String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " ("
                + COLUMN_ID      + " integer primary key autoincrement, "
                + COLUMN_ACCOUNT + " text,"
                + COLUMN_NAME    + " text,"
                + COLUMN_SEX     + " integer,"
                + COLUMN_ICON    + " text,"
                + COLUMN_SIGN    + " text,"
                + COLUMN_AREA    + " text,"
                + COLUMN_TOKEN   + " text,"
                + COLUMN_CURRENT + " integer" + ")";
    }

    public interface Friend {
        String TABLE_NAME = "friend";

        String COLUMN_ID      = "_id";
        String COLUMN_OWNER   = "owner";
        String COLUMN_ACCOUNT = "account";
        String COLUMN_NAME    = "name";
        String COLUMN_SIGN    = "sign";
        String COLUMN_AREA    = "area";
        String COLUMN_ICON    = "icon";
        String COLUMN_SEX     = "sex";
        String COLUMN_NICKNAME   = "nick_name";
        String COLUMN_ALPHA   = "alpha";
        String COLUMN_SORT    = "sort";

        String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " ("
                + COLUMN_ID       + " integer primary key autoincrement, "
                + COLUMN_OWNER    + " text,"
                + COLUMN_ACCOUNT  + " text,"
                + COLUMN_NAME     + " text,"
                + COLUMN_SIGN     + " text,"
                + COLUMN_AREA     + " text,"
                + COLUMN_ICON     + " text,"
                + COLUMN_SEX      + " integer,"
                + COLUMN_NICKNAME + " text,"
                + COLUMN_ALPHA    + " text,"
                + COLUMN_SORT     + " integer" + ")";
    }
}
