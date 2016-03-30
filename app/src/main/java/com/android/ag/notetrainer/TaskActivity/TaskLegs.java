package com.android.ag.notetrainer.TaskActivity;

import android.app.Activity;
import android.os.Bundle;

import com.android.ag.notetrainer.R;

/**
 * Created by User on 30.03.2016.
 */
public class TaskLegs extends Activity {

    private static final int LAYOUT = R.layout.choose_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
    }
}
