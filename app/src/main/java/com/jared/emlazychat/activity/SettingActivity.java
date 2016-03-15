package com.jared.emlazychat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jared.emlazychat.ChatApplication;
import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseActivity;
import com.jared.emlazychat.db.AccountDao;
import com.jared.emlazychat.domain.Account;
import com.jared.emlazychat.widget.NormalTopBar;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private NormalTopBar mTopBar;
    private Button mBtnLogout;
    private Button mBtnSettingAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);

        initView();
        initEvent();
    }

    private void initView() {
        mTopBar = (NormalTopBar)findViewById(R.id.setting_top_bar);
        mBtnLogout = (Button)findViewById(R.id.setting_logout);
        mBtnSettingAbout = (Button)findViewById(R.id.setting_about);
        mTopBar.setTitle("设置");
    }

    private void initEvent() {
        mTopBar.setOnBackListener(this);
        mBtnLogout.setOnClickListener(this);
        mBtnSettingAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mTopBar.getBackView()) {
            finish();
        } else if(view == mBtnLogout) {
            clickLogout();
        } else if(view == mBtnSettingAbout) {
            aboutLazyChat();
        }
    }

    private void clickLogout() {
        AccountDao dao = new AccountDao(this);
        Account account = dao.getCurrentAccount();
        account.setCurrent(false);
        account.setToken(null);
        dao.updateAccount(account);

        ((ChatApplication) getApplication()).closeApplication();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(LoginActivity.ENTER_KEY, LoginActivity.ENTER_SIGN_IN);
        startActivity(intent);
    }

    private void aboutLazyChat() {

    }
}
