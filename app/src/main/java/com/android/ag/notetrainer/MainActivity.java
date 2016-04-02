package com.android.ag.notetrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private Toolbar toolbar;

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Intent intent;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        local = new Locale("ru","RU");
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",local);

        initTabs();
        initToolbar();
        initDrawer();

        context = this;
        mDatabaseHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    private void initDrawer() {
        mPlanetTitles = getResources().getStringArray(R.array.name_task);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        intent = new Intent(MainActivity.this,Statistics.class);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch (position){
                case 0:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","1");
                    break;
                case 1:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","2");
                    break;
                case 2:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","3");
                    break;
                case 3:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","4");
                    break;
                case 4:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","5");
                    break;
                case 5:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","6");
                    break;
                case 6:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","1");
                    break;
                case 7:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","2");
                    break;
                case 8:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","3");
                    break;
                case 9:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","4");
                    break;
                case 10:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","5");
                    break;
                case 11:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","6");
                    break;
                case 12:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","1");
                    break;
                case 13:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","2");
                    break;
                case 14:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","3");
                    break;
                case 15:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","4");
                    break;
                case 16:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","5");
                    break;
                case 17:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","6");
                    break;
            }
            startActivity(intent);
        }
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
        tabLayout.getTabAt(3).setIcon(R.drawable.press);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }
}
