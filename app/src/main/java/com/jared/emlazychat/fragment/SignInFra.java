package com.jared.emlazychat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.utils.ToastUtil;

/**
 * Created by jared on 16/2/28.
 */
public class SignInFra extends BaseFragment implements View.OnClickListener{

    private EditText etAccount;
    private EditText etPwd;
    private Button btnSignIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_sign_in, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        etAccount = (EditText) view.findViewById(R.id.et_sign_in_account);
        etPwd = (EditText) view.findViewById(R.id.et_sign_in_pwd);
        btnSignIn = (Button) view.findViewById(R.id.btn_sign_in);
    }

    private void initEvent() {
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSignIn) {
            doSignIn();
        }
    }

    private void doSignIn() {
        Context context = getActivity();
        if(context == null) {
            return;
        }

        String account = etAccount.getText().toString().trim();
        if(TextUtils.isEmpty(account)) {
            ToastUtil.show(context, "用户名不能为空");
            return;
        }

        String password = etPwd.getText().toString().trim();
        if(TextUtils.isEmpty(password)) {
            ToastUtil.show(context, "密码为空");
            return;
        }


    }
}
