package com.jared.emlazychat.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jared.emlazychat.R;
import com.jared.emlazychat.activity.HomeActivity;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.db.AccountDao;
import com.jared.emlazychat.domain.Account;
import com.jared.emlazychat.lib.EMChatManager;
import com.jared.emlazychat.lib.EMError;
import com.jared.emlazychat.lib.callback.EMObjectCallBack;
import com.jared.emlazychat.utils.ToastUtil;
import com.jared.emlazychat.widget.DialogLoading;

/**
 * Created by jared on 16/2/28.
 */
public class SignInFra extends BaseFragment implements View.OnClickListener {

    private final static String TAG = "SignInFra";
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
        if (view == btnSignIn) {
            doSignIn();
        }
    }

    private void doSignIn() {
        Context context = getActivity();
        if (context == null) {
            return;
        }

        final String account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.show(context, "用户名不能为空");
            return;
        }

        String password = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(context, "密码为空");
            return;
        }

        final DialogLoading dialog = new DialogLoading(getActivity());
        dialog.show();

        EMChatManager.getInstance().login(account, password,
                new EMObjectCallBack<Account>() {
                    @Override
                    public void onSuccess(Account account) {
                        dialog.dismiss();
                        EMChatManager.getInstance().initAccount(
                                account.getAccount(), account.getToken());

                        AccountDao dao = new AccountDao(getActivity());
                        account.setCurrent(true);
                        Account localAccount = dao.getByAccount(account.getAccount());
                        if (!TextUtils.isEmpty(account.getIcon())) {

                        }
                        //dao.deleteAccount(account);
                        if(localAccount != null) {
                            dao.updateAccount(account);
                        } else {
                            dao.addAccount(account);
                        }

                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int error, String msg) {
                        dialog.dismiss();
                        switch (error) {
                            case EMError.ERROR_CLIENT_NET:
                                Log.d(TAG, "客户端网络异常");
                                ToastUtil.show(getActivity(), "客户端网络异常");
                                break;
                            case EMError.ERROR_SERVER:
                                Log.d(TAG, "服务器异常");
                                ToastUtil.show(getActivity(), "服务器异常");
                                break;
                            case EMError.Login.ACCOUNT_MISS:
                                Log.d(TAG, "不存在此用户");
                                ToastUtil.show(getActivity(), "不存在此用户");
                                break;
                            case EMError.Login.PASSWORD_ERROR:
                                Log.d(TAG, "密码错误");
                                ToastUtil.show(getActivity(), "密码错误");
                                break;
                            default:
                                break;
                        }
                    }
                });

    }
}
