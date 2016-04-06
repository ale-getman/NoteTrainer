package com.android.ag.notetrainer.TaskActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.ag.notetrainer.MainActivity;
import com.android.ag.notetrainer.R;
import com.android.ag.notetrainer.TaskSet;

/**
 * Created by User on 30.03.2016.
 */
public class TaskPress extends Activity {
    private static final int LAYOUT = R.layout.choose_task;

    public ImageButton task[] = new ImageButton[6];
    public TextView text_task[] = new TextView[6];
    public View view[] = new View[6];
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

        view[0] = (View) findViewById(R.id.view_1);
        view[1] = (View) findViewById(R.id.view_2);
        view[2] = (View) findViewById(R.id.view_3);
        view[3] = (View) findViewById(R.id.view_4);
        view[4] = (View) findViewById(R.id.view_5);
        view[5] = (View) findViewById(R.id.view_6);

        for(int i = 1; i < 6; i++)
        {
            task[i].setVisibility(View.GONE);
            text_task[i].setVisibility(View.GONE);
            view[i].setVisibility(View.GONE);
        }

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            task[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.skruchivanie));

        } else {
            task[0].setBackground(getResources().getDrawable(R.drawable.skruchivanie));
        }

        text_task[0].setText("Скручивание торса");

        task[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_set = "1";
                intent = new Intent(TaskPress.this, TaskSet.class);
                intent.putExtra("id",text_id);
                intent.putExtra("data",text_data);
                intent.putExtra("name_table", MainActivity.mDatabaseHelper.DATABASE_TABLE_4);
                intent.putExtra("index_set", index_set);
                startActivity(intent);
            }
        });
    }
}
