package com.android.ag.notetrainer.TaskActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.ag.notetrainer.MainActivity;
import com.android.ag.notetrainer.R;
import com.android.ag.notetrainer.Statistics;
import com.android.ag.notetrainer.TaskSet;

/**
 * Created by User on 30.03.2016.
 */
public class TaskLegs extends Activity {

    private static final int LAYOUT = R.layout.choose_task;

    public ImageButton task[] = new ImageButton[6];
    public TextView text_task[] = new TextView[6];
    public Intent intent;
    public String text_id,text_data;
    public String index_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        text_id = getIntent().getStringExtra("id");
        text_data = getIntent().getStringExtra("data");

        task[0] = (ImageButton) findViewById(R.id.task_1);
        task[1] = (ImageButton) findViewById(R.id.task_2);
        task[2] = (ImageButton) findViewById(R.id.task_3);
        task[3] = (ImageButton) findViewById(R.id.task_4);
        task[4] = (ImageButton) findViewById(R.id.task_5);
        task[5] = (ImageButton) findViewById(R.id.task_6);

        text_task[0] = (TextView) findViewById(R.id.text_task_1);
        text_task[1] = (TextView) findViewById(R.id.text_task_2);
        text_task[2] = (TextView) findViewById(R.id.text_task_3);
        text_task[3] = (TextView) findViewById(R.id.text_task_4);
        text_task[4] = (TextView) findViewById(R.id.text_task_5);
        text_task[5] = (TextView) findViewById(R.id.text_task_6);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            task[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.armeyskiy_jim));
            task[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.jim_na_ikru));
            task[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.shragi));
            task[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.prised));
            task[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.tyga_k_podborodku));
            task[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.jim_platformi));

        } else {
            task[0].setBackground(getResources().getDrawable(R.drawable.armeyskiy_jim));
            task[1].setBackground(getResources().getDrawable(R.drawable.jim_na_ikru));
            task[2].setBackground(getResources().getDrawable(R.drawable.shragi));
            task[3].setBackground(getResources().getDrawable(R.drawable.prised));
            task[4].setBackground(getResources().getDrawable(R.drawable.tyga_k_podborodku));
            task[5].setBackground(getResources().getDrawable(R.drawable.jim_platformi));
        }

        text_task[0].setText("Армейский жим");
        text_task[1].setText("Жим на икры");
        text_task[2].setText("Шраги");
        text_task[3].setText("Приседание");
        text_task[4].setText("Тяга к подбородку");
        text_task[5].setText("Жим платформы");

        task[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "1";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });

        task[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "2";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });

        task[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "3";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });

        task[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "4";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });

        task[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "5";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });

        task[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "6";
                intent = new Intent(TaskLegs.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.TABLE_NAME[1]);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });
    }
}