package com.android.ag.notetrainer.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.android.ag.notetrainer.AbstractTabFragment;
import com.android.ag.notetrainer.DatabaseHelper;
import com.android.ag.notetrainer.MainActivity;
import com.android.ag.notetrainer.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 28.03.2016.
 */
public class FragmentHands extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragments_spisok;
    public String TAG = "LOGI";
    public static Context frg_context;

    public static FloatingActionButton fab;
    public static ListView listView;

    public Locale local;
    public SimpleDateFormat df;
    public Date currentDate;
    public String data_trainer;

    public static ListAdapter adapter;
    public SQLiteDatabase sdb;

    public static FragmentHands getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentHands fragment = new FragmentHands();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.hands));
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

        sdb = MainActivity.mDatabaseHelper.getWritableDatabase();

        listView = (ListView) view.findViewById(R.id.list);
        local = new Locale("ru","RU");
        df = new SimpleDateFormat("dd.MM.yyyy",local);
        currentDate = new Date();
        data_trainer = df.format(currentDate);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "df^ " + data_trainer);
                ContentValues newValues = new ContentValues();
                newValues.put(DatabaseHelper.DATA, data_trainer);
                // Вставляем данные в таблицу
                sdb.insert(DatabaseHelper.TABLE_NAME[0], null, newValues);
                CreateList();
            }
        });

        check_table();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int delRow = sdb.delete(MainActivity.mDatabaseHelper.TABLE_NAME[0], DatabaseHelper.DATA + " = " + data_trainer, null);
                Log.d(TAG, "delRow: " + delRow);
                CreateList();
                return true;
            }
        });

        return view;
    }

    private void check_table() {

        String querySelect = "SELECT * FROM " + DatabaseHelper.TABLE_NAME[0];
        Cursor cursorSelect = sdb.rawQuery(querySelect, null);
        if (cursorSelect.moveToFirst())
        {
            Log.d(TAG, "Таблица заполнена");
            CreateList();
        }
        else
        {
            Log.d(TAG, "Таблица не заполнена");
        }
        cursorSelect.close();
    }

    public void CreateList(){

        Cursor cursor = CreateCursor();

        adapter = new SimpleCursorAdapter(frg_context, // Связь.
                R.layout.list_element_main, // Определения шаблона элемента
                cursor, // Переход к курсору, который надо запомнить.
                // Массив курсоров, которые надо запомнить.
                new String[] { "Data" },
                // Массив, связывающий запомненные курсоры и шаблоны с ними связанные
                new int[] { R.id.data_trainer});
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public Cursor CreateCursor(){
        Cursor cursor = sdb.query(MainActivity.mDatabaseHelper.TABLE_NAME[0], new String[]{DatabaseHelper._ID, DatabaseHelper.DATA,
                        DatabaseHelper.SET_1,
                        DatabaseHelper.SET_1_RETREAT_1, DatabaseHelper.SET_1_WEIGHT_1,
                        DatabaseHelper.SET_1_RETREAT_2, DatabaseHelper.SET_1_WEIGHT_2,
                        DatabaseHelper.SET_1_RETREAT_3, DatabaseHelper.SET_1_WEIGHT_3,
                        DatabaseHelper.SET_1_RETREAT_4, DatabaseHelper.SET_1_WEIGHT_4,
                        DatabaseHelper.SET_2,
                        DatabaseHelper.SET_2_RETREAT_1, DatabaseHelper.SET_2_WEIGHT_1,
                        DatabaseHelper.SET_2_RETREAT_2, DatabaseHelper.SET_2_WEIGHT_2,
                        DatabaseHelper.SET_2_RETREAT_3, DatabaseHelper.SET_2_WEIGHT_3,
                        DatabaseHelper.SET_2_RETREAT_4, DatabaseHelper.SET_2_WEIGHT_4,
                        DatabaseHelper.SET_3,
                        DatabaseHelper.SET_3_RETREAT_1, DatabaseHelper.SET_3_WEIGHT_1,
                        DatabaseHelper.SET_3_RETREAT_2, DatabaseHelper.SET_3_WEIGHT_2,
                        DatabaseHelper.SET_3_RETREAT_3, DatabaseHelper.SET_3_WEIGHT_3,
                        DatabaseHelper.SET_3_RETREAT_4, DatabaseHelper.SET_3_WEIGHT_4,
                        DatabaseHelper.SET_4,
                        DatabaseHelper.SET_4_RETREAT_1, DatabaseHelper.SET_4_WEIGHT_1,
                        DatabaseHelper.SET_4_RETREAT_2, DatabaseHelper.SET_4_WEIGHT_2,
                        DatabaseHelper.SET_4_RETREAT_3, DatabaseHelper.SET_4_WEIGHT_3,
                        DatabaseHelper.SET_4_RETREAT_4, DatabaseHelper.SET_4_WEIGHT_4,
                        DatabaseHelper.SET_5,
                        DatabaseHelper.SET_5_RETREAT_1, DatabaseHelper.SET_5_WEIGHT_1,
                        DatabaseHelper.SET_5_RETREAT_2, DatabaseHelper.SET_5_WEIGHT_2,
                        DatabaseHelper.SET_5_RETREAT_3, DatabaseHelper.SET_5_WEIGHT_3,
                        DatabaseHelper.SET_5_RETREAT_4, DatabaseHelper.SET_5_WEIGHT_4,
                        DatabaseHelper.SET_6,
                        DatabaseHelper.SET_6_RETREAT_1, DatabaseHelper.SET_6_WEIGHT_1,
                        DatabaseHelper.SET_6_RETREAT_2, DatabaseHelper.SET_6_WEIGHT_2,
                        DatabaseHelper.SET_6_RETREAT_3, DatabaseHelper.SET_6_WEIGHT_3,
                        DatabaseHelper.SET_6_RETREAT_4, DatabaseHelper.SET_6_WEIGHT_4,},
                null, null, null, null, DatabaseHelper._ID + " DESC") ;

        return cursor;
    }
}
