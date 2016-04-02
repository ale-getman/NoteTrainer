package com.android.ag.notetrainer;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 30.03.2016.
 */
public class TaskSet extends Activity {

    private static final int LAYOUT = R.layout.task_set;
    public SeekBar retreat_seek, weight_seek;
    public TextView retreat_number, weight_number, number_step;
    public ImageButton accept_step, back_step, end_set;
    public int interval;
    public String text_id,text_data,name_table, index_set;
    public int count_steps = 1;
    public SQLiteDatabase sdb;

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

        accept_step = (ImageButton) findViewById(R.id.accept_step);
        back_step = (ImageButton) findViewById(R.id.back_step);
        end_set = (ImageButton) findViewById(R.id.end_set);

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

        number_step.setText("Подход : " + count_steps);

        retreat_number.setText(retreat_seek.getProgress()+"");
        weight_number.setText(weight_seek.getProgress()+"");

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
                progress = (((int)Math.round(progress/interval))*interval);
                seekBar.setProgress(progress);
                weight_number.setText(progress + "");
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
}
