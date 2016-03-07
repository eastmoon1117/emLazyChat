package com.jared.emlazychat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseActivity;
import com.jared.emlazychat.fragment.PersonalListFra;
import com.jared.emlazychat.widget.NormalTopBar;
import com.jared.emlazychat.domain.Account;


public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "PersonalInfoActivity";

    public static final String KEY_INTENT = "data";
    private static final String TAG_PERSONAL = "personal";
    private static final String TAG_QR = "qr";

    private NormalTopBar mTopBar;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_personal);

        account = getIntent().getParcelableExtra(KEY_INTENT);
        initView();
        initEvent();
        initFragment();
    }

    private void initView() {
        mTopBar =  (NormalTopBar)findViewById(R.id.personal_top_bar);
        mTopBar.setTitle("个人信息");
    }

    private void initEvent() {
        mTopBar.setOnBackListener(this);
    }

    private void initFragment() {
        Log.d(TAG, "initFragment");
        FragmentManager fm = getSupportFragmentManager();

        PersonalListFra fragment = new PersonalListFra();
        Bundle args = new Bundle();
        args.putParcelable(KEY_INTENT, account);
        fragment.setArguments(args);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_personal_container, fragment, TAG_PERSONAL);
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        if(view == mTopBar.getBackView()) {
            clickBack();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clickBack();
    }

    private void clickBack() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();

        if (count == 0) {
            finish();
        } else {
            fm.popBackStack();
            if (count == 1) {
                mTopBar.setTitle("个人信息");
            }
        }
    }

    public void go2PersonalQR() {
        mTopBar.setTitle("我的二维码");

    }
}
