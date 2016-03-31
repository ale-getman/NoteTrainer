package com.android.ag.notetrainer.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.ag.notetrainer.AbstractTabFragment;
import com.android.ag.notetrainer.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 28.03.2016.
 */
public class FragmentPress extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragments_spisok;
    public static Context frg_context;

    public static FloatingActionButton fab;
    public static ListView listView;

    public Locale local;
    public SimpleDateFormat df;
    public Date currentDate;

    public static FragmentPress getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentPress fragment = new FragmentPress();
        fragment.setArguments(args);
        fragment.setContext(context);
        //fragment.setTitle(context.getString(R.string.press));
        frg_context = context;

        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(LAYOUT, container, false);
        } catch (InflateException e) {
    /* map is already there, just return view as it is */
        }
        listView = (ListView) view.findViewById(R.id.list);
        local = new Locale("ru","RU");
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",local);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
