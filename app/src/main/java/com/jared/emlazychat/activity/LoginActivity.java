package com.jared.emlazychat.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseActivity;
import com.jared.emlazychat.db.AccountDao;
import com.jared.emlazychat.domain.Account;
import com.jared.emlazychat.fragment.FillInfoFra;
import com.jared.emlazychat.fragment.LogoFra;
import com.jared.emlazychat.fragment.SignInFra;
import com.jared.emlazychat.fragment.SignUpFra;
import com.jared.emlazychat.widget.NormalTopBar;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";

    private static final String TAG_LOGO = "logo";
    private static final String TAG_SIGN_IN = "sign_in";
    private static final String TAG_SIGN_UP = "sign_up";
    private static final String TAG_FILL_INFO = "fill_info";

    public static final String ENTER_KEY = "enter";
    public static final int ENTER_FIRST = 0;
    public static final int ENTER_LOGINED = 1;
    public static final int ENTER_SIGN_IN = 2;
    public static final int ENTER_SIGN_UP = 3;
    public static final int ENTER_FILL_INFO = 4;

    private NormalTopBar mTopBar;

    private Fragment currentFra;
    private String currentTag;
    private FragmentManager fm;

    private int enterFlag = 0;

    private AccountDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        initView();
        initEvent();

        initFragment();
    }

    private void initView() {
        mTopBar = (NormalTopBar)findViewById(R.id.activity_login_top_bar);
    }

    private void firstLogin() {
        mTopBar.setVisibility(View.GONE);
        currentFra = new LogoFra();
        Bundle args = new Bundle();
        args.putInt(LogoFra.ARG_KEY, LogoFra.ARG_TYPE_FIRST);
        currentFra.setArguments(args);
        currentTag = TAG_LOGO;
    }

    private void alreadyLogin() {
        mTopBar.setVisibility(View.GONE);
        currentFra = new LogoFra();
        Bundle args = new Bundle();
        args.putInt(LogoFra.ARG_KEY, LogoFra.ARG_TYPE_LOINED);
        currentFra.setArguments(args);
        currentTag = TAG_LOGO;
    }

    private void signIn() {
        currentFra = new SignInFra();
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("登录");
        mTopBar.setBackVisibility(false);

        currentTag = TAG_SIGN_IN;
    }

    private void signUp() {

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("注册");
        mTopBar.setBackVisibility(false);

        currentTag = TAG_SIGN_IN;
    }

    private void fillInfo() {

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("填写信息");
        mTopBar.setBackVisibility(false);

        currentTag = TAG_FILL_INFO;
    }


    private void setTopBarAndCurrentFlag(int flag) {
        switch (flag) {
            case ENTER_FIRST:
                firstLogin();
                break;
            case ENTER_LOGINED:
                alreadyLogin();
                break;
            case ENTER_SIGN_IN:
                signIn();
                break;
            case ENTER_SIGN_UP:
                signUp();
            case ENTER_FILL_INFO:
                fillInfo();
                break;
            default:
                break;
        }
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        enterFlag = getIntent().getIntExtra(ENTER_KEY, ENTER_FIRST);
        //enterFlag = ENTER_FIRST;

        dao = new AccountDao(this);
        Account account = dao.getCurrentAccount();
        //Log.d(TAG, account.getAccount()+":"+account.getName());
        if(account != null && !TextUtils.isEmpty(account.getName())) {
            enterFlag =  ENTER_LOGINED;
        } else if(account != null){
            enterFlag = ENTER_FILL_INFO;
        }

        setTopBarAndCurrentFlag(enterFlag);

        FragmentTransaction transaction =  fm.beginTransaction();
        transaction.replace(R.id.container_login, currentFra, currentTag);
        transaction.addToBackStack(currentTag);
        transaction.commit();
    }

    private void initEvent() {
        mTopBar.setOnBackListener(this);
    }

    public void go2SignIn() {
        Fragment fragment = fm.findFragmentByTag(TAG_SIGN_IN);
        if(fragment == null) {
            fragment = new SignInFra();
        }

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("登陆");
        mTopBar.setBackVisibility(true);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_login, fragment, TAG_SIGN_IN);
        ft.addToBackStack(TAG_SIGN_IN);
        ft.commit();
    }

    public void go2SignUp() {
        Fragment fragment = fm.findFragmentByTag(TAG_SIGN_UP);
        if(fragment == null) {
            fragment = new SignUpFra();
        }

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("注册");
        mTopBar.setBackVisibility(true);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_login, fragment, TAG_SIGN_UP);
        ft.addToBackStack(TAG_SIGN_UP);
        ft.commit();
    }

    public void go2FillInfo() {
        Fragment fragment = fm.findFragmentByTag(TAG_FILL_INFO);
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; i++) {
            fm.popBackStack();
        }

        if(fragment == null) {
            fragment = new FillInfoFra();
        }

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle("填写信息");
        mTopBar.setBackVisibility(false);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_login, fragment, TAG_FILL_INFO);
        ft.addToBackStack(TAG_FILL_INFO);
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
        clickBack();
    }

    private void clickBack() {
        int count = fm.getBackStackEntryCount();
        if(count <= 1) {
            finish();
        } else {
            fm.popBackStack();
            if(count == 2) {
                FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(0);
                String name = entry.getName();
                if(TAG_LOGO.equals(name)) {
                    mTopBar.setVisibility(View.GONE);
                }
            }
        }
    }
}
