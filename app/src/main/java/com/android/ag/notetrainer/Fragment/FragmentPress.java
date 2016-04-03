package com.android.ag.notetrainer.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.android.ag.notetrainer.AbstractTabFragment;
import com.android.ag.notetrainer.DatabaseHelper;
import com.android.ag.notetrainer.MainActivity;
import com.android.ag.notetrainer.R;
import com.android.ag.notetrainer.TaskActivity.TaskLegs;
import com.android.ag.notetrainer.TaskActivity.TaskPress;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 28.03.2016.
 */
public class FragmentPress extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragments_spisok;
    public static Context frg_context;
    public String TAG = "LOGI";

    public static FloatingActionButton fab;
    public static ListView listView;

    public Locale local;
    public SimpleDateFormat df;
    public Date currentDate;
    public String data_trainer;

    public static ListAdapter adapter;
    public SQLiteDatabase sdb;

    public Intent intent;
    public TextView text_id,text_data;

    public int delRow;
    public Button acceptDel;
    public String textBtnDel;

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
        sdb = MainActivity.mDatabaseHelper.getWritableDatabase();

        acceptDel = (Button) view.findViewById(R.id.acceptDel);
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
                sdb.insert(DatabaseHelper.DATABASE_TABLE_4, null, newValues);
                CreateList();
            }
        });

        check_table();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                text_id = (TextView) parent.findViewById(R.id.id_column);
                text_data = (TextView) parent.findViewById(R.id.data_trainer);

                intent = new Intent(frg_context, TaskPress.class);
                intent.putExtra("id", text_id.getText().toString());
                intent.putExtra("data", text_data.getText().toString());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                text_id = (TextView) parent.findViewById(R.id.id_column);
                text_data = (TextView) parent.findViewById(R.id.data_trainer);

                if(acceptDel.getVisibility()!=View.VISIBLE)
                    acceptDel.setVisibility(View.VISIBLE);
                else
                    acceptDel.setVisibility(View.GONE);

                textBtnDel = text_data.getText().toString();
                acceptDel.setText("Подтвердить удаление: " + textBtnDel);
                return true;
            }
        });

        acceptDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delRow = sdb.delete(MainActivity.mDatabaseHelper.DATABASE_TABLE_4, DatabaseHelper._ID + " = " + text_id.getText().toString(), null);
                acceptDel.setVisibility(View.GONE);
                Log.d(TAG, "delRow: " + delRow);
                Log.d(TAG, "text_id: " + text_id.getText().toString());
                CreateList();
            }
        });

        return view;
    }

    private void check_table() {

        String querySelect = "SELECT * FROM " + DatabaseHelper.DATABASE_TABLE_4;
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
                new String[] { DatabaseHelper._ID, "Data" },
                // Массив, связывающий запомненные курсоры и шаблоны с ними связанные
                new int[] { R.id.id_column, R.id.data_trainer});
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public Cursor CreateCursor(){
        Cursor cursor = sdb.query(MainActivity.mDatabaseHelper.DATABASE_TABLE_4, new String[]{DatabaseHelper._ID, DatabaseHelper.DATA,
                        DatabaseHelper.SET_1_RETREAT_1, DatabaseHelper.SET_1_WEIGHT_1,
                        DatabaseHelper.SET_1_RETREAT_2, DatabaseHelper.SET_1_WEIGHT_2,
                        DatabaseHelper.SET_1_RETREAT_3, DatabaseHelper.SET_1_WEIGHT_3,
                        DatabaseHelper.SET_1_RETREAT_4, DatabaseHelper.SET_1_WEIGHT_4,},
                null, null, null, null, DatabaseHelper._ID + " DESC") ;

        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATA));

            String set_1_retreat_1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_RETREAT_1));
            String set_1_retreat_2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_RETREAT_2));
            String set_1_retreat_3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_RETREAT_3));
            String set_1_retreat_4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_RETREAT_4));

            String set_1_weight_1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_WEIGHT_1));
            String set_1_weight_2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_WEIGHT_2));
            String set_1_weight_3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_WEIGHT_3));
            String set_1_weight_4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SET_1_WEIGHT_4));


            Log.d(TAG, "data: " + data);
            Log.d(TAG, "set_1_retreat_1: " + set_1_retreat_1 + " set_1_weight_1: " + set_1_weight_1);
            Log.d(TAG, "set_1_retreat_2: " + set_1_retreat_2 + " set_1_weight_2: " + set_1_weight_2);
            Log.d(TAG, "set_1_retreat_3: " + set_1_retreat_3 + " set_1_weight_3: " + set_1_weight_3);
            Log.d(TAG, "set_1_retreat_4: " + set_1_retreat_4 + " set_1_weight_4: " + set_1_weight_4);
        }
        return cursor;
    }
}
