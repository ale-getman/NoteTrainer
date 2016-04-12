package com.android.ag.notetrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
    public CustomDrawerAdapter adapter;
    public List<DrawerItem> dataList;
    private Intent intent;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDBdir();

        local = new Locale("ru","RU");
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",local);

        initTabs();
        initToolbar();
        initDrawer();

        context = this;
        mDatabaseHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    private void initDrawer() {
        dataList = new ArrayList<DrawerItem>();
        mPlanetTitles = getResources().getStringArray(R.array.name_task);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        dataList.add(new DrawerItem("Hands", R.drawable.gym));
        dataList.add(new DrawerItem("Жим штанги узким хватом"));
        dataList.add(new DrawerItem("Подъем стоя на бицепс"));
        dataList.add(new DrawerItem("Французский жим"));
        dataList.add(new DrawerItem("Молотки"));
        dataList.add(new DrawerItem("Разгибание на трицепс"));
        dataList.add(new DrawerItem("Жим на скамье Скотта"));
        dataList.add(new DrawerItem("Legs", R.drawable.people));
        dataList.add(new DrawerItem("Армейский жим"));
        dataList.add(new DrawerItem("Жим на икры"));
        dataList.add(new DrawerItem("Шраги"));
        dataList.add(new DrawerItem("Приседание"));
        dataList.add(new DrawerItem("Тяга к подбородку"));
        dataList.add(new DrawerItem("Жим платформы"));
        dataList.add(new DrawerItem("Back", R.drawable.medical));
        dataList.add(new DrawerItem("Горизонтальный жим лежа"));
        dataList.add(new DrawerItem("Разводка гантелей"));
        dataList.add(new DrawerItem("Жим гантелей"));
        dataList.add(new DrawerItem("Становая тяга"));
        dataList.add(new DrawerItem("Тяга штанги к поясу"));
        dataList.add(new DrawerItem("Тяга верхнего блока"));
        dataList.add(new DrawerItem("Press", R.drawable.press));
        dataList.add(new DrawerItem("Скручивание торса"));

        intent = new Intent(MainActivity.this,Statistics.class);
        // Set the adapter for the list view
        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        /*mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));*/
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        RelativeLayout relativeLayout;
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
            if (dataList.get(position).getTitle() == null)
            switch (position){
                case 1:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set", "1");
                    break;
                case 2:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","2");
                    break;
                case 3:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","3");
                    break;
                case 4:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","4");
                    break;
                case 5:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","5");
                    break;
                case 6:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[0]);
                    intent.putExtra("set","6");
                    break;
                case 8:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","1");
                    break;
                case 9:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","2");
                    break;
                case 10:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","3");
                    break;
                case 11:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","4");
                    break;
                case 12:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","5");
                    break;
                case 13:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[1]);
                    intent.putExtra("set","6");
                    break;
                case 15:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","1");
                    break;
                case 16:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","2");
                    break;
                case 17:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","3");
                    break;
                case 18:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","4");
                    break;
                case 19:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","5");
                    break;
                case 20:
                    intent.putExtra("table_name", DatabaseHelper.TABLE_NAME[2]);
                    intent.putExtra("set","6");
                    break;
                case 22:
                    intent.putExtra("table_name", DatabaseHelper.DATABASE_TABLE_4);
                    intent.putExtra("set","1");
                    break;
            }
            mDrawerLayout.closeDrawer(mDrawerList);
            if(position != 0 && position != 7 && position != 14 && position != 21)
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
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_import) {
            importDB();
            return true;
        }

        if (id == R.id.action_export) {
            exportDB();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createDBdir(){
        File direct = new File(Environment.getExternalStorageDirectory() + "/NoteTrainerBD");

        if(!direct.exists())
        {
            if(direct.mkdir())
            {
                //directory is created;
            }

        }
    }

    private void importDB() {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data  = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.android.ag.notetrainer"
                        + "//databases//" + "mydatabase.db";
                String backupDBPath  = "/NoteTrainerBD/mydatabase.db";
                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
    //exporting database
    private void exportDB() {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.android.ag.notetrainer"
                        + "//databases//" + "mydatabase.db";
                String backupDBPath  = "/NoteTrainerBD/mydatabase.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}
