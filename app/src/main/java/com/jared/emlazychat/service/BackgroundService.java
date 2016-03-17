package com.jared.emlazychat.service;

import android.content.Intent;
import android.database.Cursor;

import com.jared.emlazychat.ChatApplication;
import com.jared.emlazychat.base.BaseIntentService;
import com.jared.emlazychat.db.BackTaskDao;
import com.jared.emlazychat.db.EMDB;
import com.jared.emlazychat.domain.Account;
import com.jared.emlazychat.domain.NetTask;
import com.jared.emlazychat.lib.EMChatManager;
import com.jared.emlazychat.utils.SerializableUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jared on 16/3/16.
 */
public class BackgroundService extends BaseIntentService {

    private Account account;

    public BackgroundService() {
        super("backgroud");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        account = ((ChatApplication) getApplication()).getCurrentAccount();
        String owner = account.getAccount();
        EMChatManager.getInstance().initAccount(account.getAccount(),
                account.getToken());

        final BackTaskDao dao = new BackTaskDao(this);
        Map<Long, String> map = new HashMap<Long, String>();

        Cursor cursor = dao.query(owner, 0);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final long id = cursor.getLong(cursor
                        .getColumnIndex(EMDB.BackTask.COLUMN_ID));
                String filePath = cursor.getString(cursor
                        .getColumnIndex(EMDB.BackTask.COLUMN_PATH));
                map.put(id, filePath);
            }
            cursor.close();
        }
        for (Map.Entry<Long, String>me : map.entrySet()) {
            try {
                final long id = me.getKey();
                String filePath = me.getValue();
                NetTask task = (NetTask) SerializableUtil.read(filePath);
                int type = task.getType();
                if (type == NetTask.TYPE_NORMAL) {

                } else if(type == NetTask.TYPE_DOWNLOAD) {

                } else if(type == NetTask.TYPE_UPLOAD) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
