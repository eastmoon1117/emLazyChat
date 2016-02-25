package com.jared.emlazychat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseFragment;

/**
 * Created by jared on 16/2/22.
 */
public class ContactFra extends BaseFragment implements AdapterView.OnItemClickListener{
    private ImageView ivAddFriend;
    private TextView tvTitle;

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_contact, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ivAddFriend = (ImageView)view.findViewById(R.id.bar_add_friend);
        tvTitle = (TextView)view.findViewById(R.id.bar_title);
        listView = (ListView)view.findViewById(R.id.contact_list_view);
        tvTitle.setText("通讯录");

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
