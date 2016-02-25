package com.jared.emlazychat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseFragment;
import com.jared.emlazychat.widget.NormalTopBar;

/**
 * Created by jared on 16/2/22.
 */
public class DiscoverFra extends BaseFragment {
    private NormalTopBar mTopBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_discover, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTopBar = (NormalTopBar)view.findViewById(R.id.discover_to_bar);
        mTopBar.setBackVisibility(false);
        mTopBar.setTitle("发现");
    }
}
