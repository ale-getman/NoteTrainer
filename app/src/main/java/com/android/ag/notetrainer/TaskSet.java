package com.android.ag.notetrainer;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 30.03.2016.
 */
public class TaskSet extends Activity {

    private static final int LAYOUT = R.layout.task_set;
    public String TAG = "LOGI";
    public SeekBar retreat_seek, weight_seek;
    public TextView retreat_number, weight_number, number_step;
    public TextView last_max_retreat, last_max_weight;
    public ImageButton accept_step, back_step, end_set;
    public ImageButton minus_retreat, plus_retreat;
    public ImageButton minus_weight, plus_weight;
    //public CheckBox half_weight;
    public int interval;
    public String text_id,text_data,name_table, index_set;
    public int count_steps = 1;
    public SQLiteDatabase sdb;

    public boolean action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        text_id = getIntent().getStringExtra("id");
        text_data = getIntent().getStringExtra("data");
        name_table = getIntent().getStringExtra("name_table");
        index_set = getIntent().getStringExtra("index_set");

        sdb = MainActivity.mDatabaseHelper.getWritableDatabase();

        retreat_seek = (SeekBar) findViewById(R.id.retreat_seek);
        weight_seek = (SeekBar) findViewById(R.id.weight_seek);

        retreat_number = (TextView) findViewById(R.id.retreat_number);
        weight_number = (TextView) findViewById(R.id.weight_number);
        number_step = (TextView) findViewById(R.id.number_step);
        last_max_retreat = (TextView) findViewById(R.id.last_max_retreat);
        last_max_weight = (TextView) findViewById(R.id.last_max_weight);

        accept_step = (ImageButton) findViewById(R.id.accept_step);
        back_step = (ImageButton) findViewById(R.id.back_step);
        end_set = (ImageButton) findViewById(R.id.end_set);

        minus_retreat = (ImageButton) findViewById(R.id.minus_retreat);
        plus_retreat = (ImageButton) findViewById(R.id.plus_retreat);
        minus_weight = (ImageButton) findViewById(R.id.minus_weight);
        plus_weight = (ImageButton) findViewById(R.id.plus_weight);

        //half_weight = (CheckBox) findViewById(R.id.half_weight);

        interval = 5;

        switch (index_set) {
            case "1":
                count_steps = MainActivity.check_point[0];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[0]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[0]);
                break;
            case "2":
                count_steps = MainActivity.check_point[1];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[1]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[1]);
                break;
            case "3":
                count_steps = MainActivity.check_point[2];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[2]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[2]);
                break;
            case "4":
                count_steps = MainActivity.check_point[3];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[3]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[3]);
                break;
            case "5":
                count_steps = MainActivity.check_point[4];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[4]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[4]);
                break;
            case "6":
                count_steps = MainActivity.check_point[5];
                retreat_seek.setProgress(MainActivity.check_point_progress_retreat[5]);
                weight_seek.setProgress(MainActivity.check_point_progress_weight[5]);
                break;
        }

        CreateCursor();

        number_step.setText("Подход : " + count_steps);

        retreat_number.setText(retreat_seek.getProgress()+"");
        weight_number.setText(weight_seek.getProgress() + "");

        /*half_weight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    //
                else
                    //
            }
        });*/

        minus_retreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retreat_seek.getProgress() > 0)
                    retreat_seek.setProgress(retreat_seek.getProgress() - 1);
            }
        });
        plus_retreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retreat_seek.getProgress() < 20)
                    retreat_seek.setProgress(retreat_seek.getProgress() + 1);
            }
        });

        minus_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weight_seek.getProgress() > 0)
                    weight_seek.setProgress(weight_seek.getProgress() - 1);
            }
        });
        plus_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight_seek.getProgress() < 150)
                    weight_seek.setProgress(weight_seek.getProgress() + 1);
            }
        });

        plus_weight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                    if (weight_seek.getProgress() < 150)
                        weight_seek.setProgress(weight_seek.getProgress() + 1);
                return true;
            }
        });

        accept_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_steps <= 4) {
                    Toast.makeText(getApplicationContext(), "Подход номер : " + count_steps + " добавлен.", Toast.LENGTH_SHORT).show();
                    updateRowOnBase();
                } else {
                    retreat_seek.setProgress(0);
                    weight_seek.setProgress(0);
                    retreat_number.setText("#");
                    weight_number.setText("#");
                    Toast.makeText(getApplicationContext(), "Это был последний.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_steps!=1) {
                    count_steps--;
                    number_step.setText("Подход : " + count_steps);
                }
            }
        });

        end_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Упражнение окончено.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        retreat_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                retreat_number.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        weight_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //weight_number.setText(String.valueOf(seekBar.getProgress()));
                /*progress = (((int)Math.round(progress/interval))*interval);
                seekBar.setProgress(progress);
                weight_number.setText(progress + "");*/
                weight_number.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateRowOnBase() {
        ContentValues updatedValues = new ContentValues();

        String retreat = "SET_" + index_set + "_RETREAT_" + count_steps;
        String weight = "SET_" + index_set + "_WEIGHT_" + count_steps;
        String set = "SET_"+index_set;


        switch (count_steps) {
            case 1:
                if(!(name_table.equals(MainActivity.mDatabaseHelper.DATABASE_TABLE_4)))
                    updatedValues.put(set, "+");
                updatedValues.put(retreat, retreat_number.getText().toString());
                updatedValues.put(weight, weight_number.getText().toString());
                count_steps++;
                number_step.setText("Подход : " + count_steps);
                break;
            case 2:
                updatedValues.put(retreat, retreat_number.getText().toString());
                updatedValues.put(weight, weight_number.getText().toString());
                count_steps++;
                number_step.setText("Подход : " + count_steps);
                break;
            case 3:
                updatedValues.put(retreat, retreat_number.getText().toString());
                updatedValues.put(weight, weight_number.getText().toString());
                count_steps++;
                number_step.setText("Подход : " + count_steps);
                break;
            case 4:
                updatedValues.put(retreat, retreat_number.getText().toString());
                updatedValues.put(weight, weight_number.getText().toString());
                count_steps++;
                break;
        }
        String where = DatabaseHelper._ID + "=" + text_id;

        sdb.update(name_table, updatedValues, where, null);
    }

    @Override
    public void onBackPressed() {
        CreateCheckPoint();
        super.onBackPressed();
    }

    public void CreateCheckPoint(){
        switch (index_set) {
            case "1":
                MainActivity.check_point[0] = count_steps;
                MainActivity.check_point_progress_retreat[0] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[0] = weight_seek.getProgress();
                break;
            case "2":
                MainActivity.check_point[1] = count_steps;
                MainActivity.check_point_progress_retreat[1] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[1] = weight_seek.getProgress();
                break;
            case "3":
                MainActivity.check_point[2] = count_steps;
                MainActivity.check_point_progress_retreat[2] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[2] = weight_seek.getProgress();
                break;
            case "4":
                MainActivity.check_point[3] = count_steps;
                MainActivity.check_point_progress_retreat[3] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[3] = weight_seek.getProgress();
                break;
            case "5":
                MainActivity.check_point[4] = count_steps;
                MainActivity.check_point_progress_retreat[4] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[4] = weight_seek.getProgress();
                break;
            case "6":
                MainActivity.check_point[5] = count_steps;
                MainActivity.check_point_progress_retreat[5] = retreat_seek.getProgress();
                MainActivity.check_point_progress_weight[5] = weight_seek.getProgress();
                break;
        }
    }


    public void CreateCursor(){
        Cursor cursor;
        if(name_table.equals(MainActivity.mDatabaseHelper.DATABASE_TABLE_4)) {
            cursor = sdb.query(name_table, new String[]{DatabaseHelper._ID, DatabaseHelper.DATA,
                            DatabaseHelper.SET_1_RETREAT_1, DatabaseHelper.SET_1_WEIGHT_1,
                            DatabaseHelper.SET_1_RETREAT_2, DatabaseHelper.SET_1_WEIGHT_2,
                            DatabaseHelper.SET_1_RETREAT_3, DatabaseHelper.SET_1_WEIGHT_3,
                            DatabaseHelper.SET_1_RETREAT_4, DatabaseHelper.SET_1_WEIGHT_4,},
                    null, null, null, null, null);
        }
        else {
            cursor = sdb.query(name_table, new String[]{DatabaseHelper._ID, DatabaseHelper.DATA,
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
                    null, null, null, null, null);
        }
        String set_number = "set_"+index_set;
        String retreat_number = "set_"+index_set+"_retreat_";
        String weight_number = "set_"+index_set+"_weight_";
        String data;
        String set = "",set_retreat_1,set_retreat_2,set_retreat_3,set_retreat_4,
                set_weight_1,set_weight_2,set_weight_3,set_weight_4;
        int retreat_max,weight_max;

        while (cursor.moveToNext()) {
            data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATA));
            if(!(name_table.equals(MainActivity.mDatabaseHelper.DATABASE_TABLE_4)))
                set = cursor.getString(cursor.getColumnIndex(set_number));

            set_retreat_1 = cursor.getString(cursor.getColumnIndex(retreat_number + "1"));
            set_retreat_2 = cursor.getString(cursor.getColumnIndex(retreat_number + "2"));
            set_retreat_3 = cursor.getString(cursor.getColumnIndex(retreat_number + "3"));
            set_retreat_4 = cursor.getString(cursor.getColumnIndex(retreat_number + "4"));

            set_weight_1 = cursor.getString(cursor.getColumnIndex(weight_number + "1"));
            set_weight_2 = cursor.getString(cursor.getColumnIndex(weight_number + "2"));
            set_weight_3 = cursor.getString(cursor.getColumnIndex(weight_number + "3"));
            set_weight_4 = cursor.getString(cursor.getColumnIndex(weight_number + "4"));

            Log.d(TAG, "data: " + data);
            if(!(name_table.equals(MainActivity.mDatabaseHelper.DATABASE_TABLE_4)))
                Log.d(TAG, "set_1: " + set);
            Log.d(TAG, "set_1_retreat_1: " + set_retreat_1 + " set_1_weight_1: " + set_weight_1);
            Log.d(TAG, "set_1_retreat_2: " + set_retreat_2 + " set_1_weight_2: " + set_weight_2);
            Log.d(TAG, "set_1_retreat_3: " + set_retreat_3 + " set_1_weight_3: " + set_weight_3);
            Log.d(TAG, "set_1_retreat_4: " + set_retreat_4 + " set_1_weight_4: " + set_weight_4);

            if(set_retreat_1!=null && set_retreat_2!=null && set_retreat_3!=null && set_retreat_4!=null) {
                retreat_max = findMax(Integer.valueOf(set_retreat_1),Integer.valueOf(set_retreat_2),
                        Integer.valueOf(set_retreat_3),Integer.valueOf(set_retreat_4),
                        Integer.valueOf(set_weight_1),Integer.valueOf(set_weight_2),
                        Integer.valueOf(set_weight_3),Integer.valueOf(set_weight_4));
                switch (retreat_max) {
                    case 0:
                        last_max_weight.setText(set_weight_1.toString());
                        last_max_retreat.setText(set_retreat_1.toString());
                        break;
                    case 1:
                        last_max_weight.setText(set_weight_2.toString());
                        last_max_retreat.setText(set_retreat_2.toString());
                        break;
                    case 2:
                        last_max_weight.setText(set_weight_3.toString());
                        last_max_retreat.setText(set_retreat_3.toString());
                        break;
                    case 3:
                        last_max_weight.setText(set_weight_4.toString());
                        last_max_retreat.setText(set_retreat_4.toString());
                        break;
                }
            }
        }
        cursor.close();
    }

    public int findMax(int r1, int r2, int r3, int r4, int w1, int w2, int w3, int w4){
        int[] r = new int[]{r1,r2,r3,r4};
        int[] w = new int[]{w1,w2,w3,w4};
        int max = -1;
        int[] imax_arr = new int[]{-1,-1,-1,-1};
        int imax = 0;
        ArrayList<Integer> ibuf = new ArrayList<>();

        for (int i = 0; i < w.length; i++) {
            if (w[i] > max) {
                max = w[i];
                imax = i;
            }
        }

        for(int i = 0; i < w.length; i++){
            if(w[i] == max)
                imax_arr[i] = i;
        }

        for(int i = 0; i < imax_arr.length; i++)
            if(imax_arr[i]!=-1)
                ibuf.add(r[imax_arr[i]]);
            else
                ibuf.add(-1);

        max = -1;

        for(int i=0; i<ibuf.size(); i++){
            if(ibuf.get(i) > max){
                max = ibuf.get(i);
                imax = i;
            }
        }

        return imax;
    }
}
