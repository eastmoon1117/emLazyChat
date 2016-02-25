package com.jared.emlazychat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;

import com.jared.emlazychat.R;
import com.jared.emlazychat.base.BaseActivity;
import com.jared.emlazychat.fragment.ChatFra;
import com.jared.emlazychat.fragment.ContactFra;
import com.jared.emlazychat.fragment.DiscoverFra;
import com.jared.emlazychat.fragment.MeFra;
import com.jared.emlazychat.widget.TabIndicatorView;

public class HomeActivity extends BaseActivity implements TabHost.OnTabChangeListener{

    private static final String TAB_CHAT = "chat";
    private static final String TAB_CONTACT = "contact";
    private static final String TAB_DISCOVER = "discover";
    private static final String TAB_ME = "me";

    private FragmentTabHost tabHost;

    private TabIndicatorView chatIndicator;
    private TabIndicatorView contactIndicator;
    private TabIndicatorView discoverIndicator;
    private TabIndicatorView meIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //1.初始化TabHost
        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //2.新建TabSpec
        FragmentTabHost.TabSpec spec = tabHost.newTabSpec(TAB_CHAT);

        chatIndicator = new TabIndicatorView(this);
        chatIndicator.setTabTitle("消息");
        chatIndicator.setTabIcon(R.drawable.tab_icon_chat_normal,
                R.drawable.tab_icon_chat_focus);
        spec.setIndicator(chatIndicator);

        tabHost.addTab(spec, ChatFra.class, null);

        spec = tabHost.newTabSpec(TAB_CONTACT);
        contactIndicator = new TabIndicatorView(this);
        contactIndicator.setTabIcon(R.drawable.tab_icon_contact_normal,
                R.drawable.tab_icon_contact_focus);
        contactIndicator.setTabTitle("通讯录");
        contactIndicator.setTabUnreadCount(1);
        spec.setIndicator(contactIndicator);

        tabHost.addTab(spec, ContactFra.class, null);

        spec = tabHost.newTabSpec(TAB_DISCOVER);
        discoverIndicator = new TabIndicatorView(this);
        discoverIndicator.setTabIcon(R.drawable.tab_icon_discover_normal,
                R.drawable.tab_icon_discover_focus);
        discoverIndicator.setTabTitle("发现");
        spec.setIndicator(discoverIndicator);

        tabHost.addTab(spec, DiscoverFra.class, null);

        spec = tabHost.newTabSpec(TAB_ME);
        meIndicator = new TabIndicatorView(this);
        meIndicator.setTabIcon(R.drawable.tab_icon_me_normal,
                R.drawable.tab_icon_me_focus);
        meIndicator.setTabTitle("我");
        spec.setIndicator(meIndicator);

        tabHost.addTab(spec, MeFra.class, null);

        tabHost.getTabWidget().setDividerDrawable(android.R.color.white);

        tabHost.setCurrentTabByTag(TAB_CHAT);
        chatIndicator.setTabSelected(true);

        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tag) {
        chatIndicator.setTabSelected(false);
        contactIndicator.setTabSelected(false);
        discoverIndicator.setTabSelected(false);
        meIndicator.setTabSelected(false);

        if (TAB_CHAT.equals(tag)) {
            chatIndicator.setTabSelected(true);
        } else if (TAB_CONTACT.equals(tag)) {
            contactIndicator.setTabSelected(true);
        } else if (TAB_DISCOVER.equals(tag)) {
            discoverIndicator.setTabSelected(true);
        } else if (TAB_ME.equals(tag)) {
            meIndicator.setTabSelected(true);
        }
    }
}
