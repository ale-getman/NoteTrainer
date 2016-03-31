package com.android.ag.notetrainer;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    public Locale local;
    public SimpleDateFormat df;
    public static DatabaseHelper mDatabaseHelper;
    public static int check_point[] = {1,1,1,1,1,1};
    public static int check_point_progress_retreat[] = {0,0,0,0,0,0};
    public static int check_point_progress_weight[] = {0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        local = new Locale("ru","RU");
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",local);

        initTabs();

        mDatabaseHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.gym);
        tabLayout.getTabAt(1).setIcon(R.drawable.people);
        tabLayout.getTabAt(2).setIcon(R.drawable.medical);
        tabLayout.getTabAt(3).setIcon(R.drawable.gym_1);

    }
}
