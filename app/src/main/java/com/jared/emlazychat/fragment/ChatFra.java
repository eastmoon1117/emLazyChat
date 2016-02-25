package com.jared.emlazychat.fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.widget.NormalTopBar;

/**
 * Created by jared on 16/2/22.
 */
public class ChatFra extends BaseFragment implements AdapterView.OnItemClickListener{
    private NormalTopBar mTopBar;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_chat, container, false);
        initView(view);

        IntentFilter flater = new IntentFilter();
        //flater.addAction();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view) {
        mTopBar = (NormalTopBar)view.findViewById(R.id.chat_to_bar);
        listView = (ListView)view.findViewById(R.id.chat_list_view);

        mTopBar.setBackVisibility(false);
        mTopBar.setTitle("消息");
    }

    private void initEvent() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
