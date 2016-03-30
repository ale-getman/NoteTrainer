package com.android.ag.notetrainer;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by User on 30.03.2016.
 */
public class Statistics extends Activity {

    private static final int LAYOUT = R.layout.statistics;

    public String name_table;
    public String index_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        name_table = getIntent().getStringExtra("name_table");
        index_set = getIntent().getStringExtra("index_set");
    }
}
