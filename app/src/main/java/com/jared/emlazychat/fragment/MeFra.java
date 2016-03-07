package com.jared.emlazychat.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jared.emlazychat.R;
import com.jared.emlazychat.activity.PersonalInfoActivity;
import com.jared.emlazychat.activity.SettingActivity;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.db.AccountDao;
import com.jared.emlazychat.domain.Account;
import com.jared.emlazychat.widget.NormalTopBar;

/**
 * Created by jared on 16/2/22.
 */
public class MeFra extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "MeFra";

    public static final int REQUEST_CODE_PERSONAL = 0x0011;

    private NormalTopBar mTopBar;

    private TextView tvName;
    private TextView tvAccount;
    private ImageView ivIcon;

    private View mAccountView;
    private View mSettingView;

    private Account account;
    private AccountDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new AccountDao(getActivity());
        account = dao.getCurrentAccount();
    }

    @Override
    public void onStart() {
        super.onStart();

        loadAccountInfo();
    }

    private void loadAccountInfo() {
        account = dao.getCurrentAccount();
        tvAccount.setText(account.getAccount());
        tvName.setText(account.getName());

        Log.d(TAG, account.getAccount()+account.getName());
        Bitmap bitmap = BitmapFactory.decodeFile(account.getIcon());
        if (bitmap != null) {
            ivIcon.setImageBitmap(bitmap);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_me, container, false);

        initView(view);
        initEvent();

        return view;
    }

    private void initView(View view) {
        mTopBar = (NormalTopBar) view.findViewById(R.id.me_top_bar);

        tvName = (TextView) view.findViewById(R.id.me_tv_name);
        tvAccount = (TextView) view.findViewById(R.id.me_tv_account);
        ivIcon = (ImageView) view.findViewById(R.id.me_iv_icon);
        mAccountView = view.findViewById(R.id.me_item_account);
        mSettingView = view.findViewById(R.id.me_item_setting);

        mTopBar.setBackVisibility(false);
        mTopBar.setTitle("我");

    }

    private void initEvent() {
        mAccountView.setOnClickListener(this);
        mSettingView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "MeFra onClick!!");
        if(view == mAccountView) {
            clickAccount();
        } else if(view == mSettingView) {
            clickSetting();
        }
    }

    private void clickSetting() {
        Log.d(TAG, "clickSetting!!");
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    private void clickAccount() {
        Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
        intent.putExtra(PersonalInfoActivity.KEY_INTENT, account);
        startActivityForResult(intent, REQUEST_CODE_PERSONAL);
    }
}

