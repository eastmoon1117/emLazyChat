package com.jared.emlazychat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jared.emlazychat.R;
import com.jared.emlazychat.activity.HomeActivity;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.utils.ToastUtil;

/**
 * Created by jared on 16/2/29.
 */
public class FillInfoFra extends BaseFragment implements View.OnClickListener,TextWatcher{

    private final static int REQUEST_CODE_GALLERY = 0x11;
    private final static int REQUEST_CODE_CAMERA = 0x12;
    private final static int REQUEST_CODE_CROP = 0x13;

    private ImageView ivIcon;
    private EditText etName;
    private Button btnClear;
    private Button btnOk;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_fill_info, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        ivIcon = (ImageView) view.findViewById(R.id.fill_info_iv_icon);
        etName = (EditText) view.findViewById(R.id.fill_info_et_name);
        btnOk = (Button) view.findViewById(R.id.fill_info_btn_ok);

        //btnClear.setVisibility(View.GONE);
        btnOk.setEnabled(false);
    }

    private void initEvent() {
        ivIcon.setOnClickListener(this);
        etName.addTextChangedListener(this);
        //btnClear.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnOk) {
            doOk();
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        int length = etName.getText().toString().trim().length();
        if(length > 0) {
            btnOk.setEnabled(true);
        } else {
            btnOk.setEnabled(false);
        }
    }

    private void doOk() {
        String text = etName.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            ToastUtil.show(getActivity(), "名字不能为空");
            return;
        }

        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}
