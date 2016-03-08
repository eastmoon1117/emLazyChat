package com.jared.emlazychat.fragment;

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
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.db.AccountDao;
import com.jared.emlazychat.domain.Account;

/**
 * Created by jared on 16/2/25.
 */
public class PersonalListFra extends BaseFragment implements View.OnClickListener {

    private final static String TAG = "PersonalListFra";

    private final static int REQUEST_CODE_GALLERY = 0X11;
    private final static int REQUEST_CODE_CAMERA = 0x12;
    private final static int REQUEST_CODE_CROP = 0x13;

    private View mItemIconView;
    private ImageView mIvIcon;

    private View mItemNameView;
    private TextView mTvNameView;

    private View mItemAccountView;
    private TextView mTvAccountView;

    private View mItemQRView;

    private View mItemSexView;
    private TextView mTvSexView;

    private View mItemSignView;
    private TextView mTvSignView;

    private Account account;
    private AccountDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        account = arguments.getParcelable(PersonalInfoActivity.KEY_INTENT);
        Log.d(TAG, account.getAccount()+":"+account.getName()+":"+account.getSex()+":"+account.getSign());
        dao = new AccountDao(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_personal, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mItemIconView = view.findViewById(R.id.personal_item_icon);
        mIvIcon = (ImageView)view.findViewById(R.id.personal_iv_icon);

        mItemNameView = view.findViewById(R.id.personal_item_name);
        mTvNameView = (TextView)view.findViewById(R.id.personal_tv_name);

        mItemAccountView = view.findViewById(R.id.personal_item_account);
        mTvAccountView = (TextView) view.findViewById(R.id.personal_tv_account);

        mItemQRView = view.findViewById(R.id.personal_item_qr);

        mItemSexView = view.findViewById(R.id.personal_item_sex);
        mTvSexView = (TextView)view.findViewById(R.id.personal_tv_sex);

        mItemSignView = view.findViewById(R.id.personal_item_sign);
        mTvSignView = (TextView)view.findViewById(R.id.personal_tv_sign);

        loadInfo();
    }

    private void loadInfo() {
        int sex = account.getSex();
        String sexStr = "未填写";
        switch (sex) {
            case 1:
                sexStr = "女";
                break;
            case 2:
                sexStr = "男";
                break;
            default:
                break;
        }

        mTvNameView.setText(account.getName());
        Bitmap bitmap = BitmapFactory.decodeFile(account.getIcon());
        if (bitmap != null) {
            mIvIcon.setImageBitmap(bitmap);
        }
        mTvAccountView.setText(account.getAccount());
        mTvSexView.setText(sexStr);
        mTvSignView.setText(account.getSign());
    }

    private void initEvent() {
        mItemIconView.setOnClickListener(this);
        mItemQRView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
