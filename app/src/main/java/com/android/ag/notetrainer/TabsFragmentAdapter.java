package com.android.ag.notetrainer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.ag.notetrainer.Fragment.FragmentBack;
import com.android.ag.notetrainer.Fragment.FragmentHands;
import com.android.ag.notetrainer.Fragment.FragmentLegs;
import com.android.ag.notetrainer.Fragment.FragmentPress;

import java.util.HashMap;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, FragmentHands.getInstance(context));
        tabs.put(1, FragmentLegs.getInstance(context));
        tabs.put(2, FragmentBack.getInstance(context));
        tabs.put(3, FragmentPress.getInstance(context));
    }
}
