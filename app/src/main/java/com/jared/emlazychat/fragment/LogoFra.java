package com.jared.emlazychat.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jared.emlazychat.R;
import com.jared.emlazychat.activity.LoginActivity;
import com.jared.emlazychat.base.BaseFragment;

/**
 * Created by jared on 16/2/28.
 */
public class LogoFra extends BaseFragment implements View.OnClickListener {
    public final static String ARG_KEY = "ARG";
    public final static int ARG_TYPE_FIRST = 0;
    public final static int ARG_TYPE_LOINED = 1;

    public int currentFlag = 0;

    private Button btnSignUp;
    private Button btnSignIn;

    private Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_logo, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        btnSignIn = (Button) view.findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) view.findViewById(R.id.btn_sign_up);

        if(currentFlag == ARG_TYPE_FIRST) {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);

        }
    }

    private void initEvent() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSignIn) {
            signIn();
        } else if(view == btnSignUp) {
            signUp();
        }
    }

    private void signIn() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((LoginActivity) activity).go2SignIn();
        }
    }

    private void signUp() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((LoginActivity) activity).go2SignUp();
        }
    }
}
