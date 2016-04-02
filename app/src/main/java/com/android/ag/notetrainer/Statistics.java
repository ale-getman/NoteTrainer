package com.android.ag.notetrainer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;

/**
 * Created by User on 30.03.2016.
 */
public class Statistics extends ActionBarActivity {

    private static final int LAYOUT = R.layout.statistics;

    public String name_table;
    public String index_set;

    public SQLiteDatabase sdb;
    public String TAG = "LOGI";

    public static int number_asix = 0;

    public static ArrayList<String> osX_array = new ArrayList<>();
    public static ArrayList<Integer> retreat_array_max = new ArrayList<>();
    public static ArrayList<Integer> weight_array_max = new ArrayList<>();

    public static ArrayList<Integer> retreat_array_min = new ArrayList<>();
    public static ArrayList<Integer> weight_array_min = new ArrayList<>();

    public static ArrayList<Float> retreat_array_avg = new ArrayList<>();
    public static ArrayList<Float> weight_array_avg = new ArrayList<>();

    public static ArrayList<Integer> retreat_array_sum = new ArrayList<>();
    public static ArrayList<Integer> weight_array_sum = new ArrayList<>();

    public static int flag_graphic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        name_table = getIntent().getStringExtra("table_name");
        index_set = getIntent().getStringExtra("set");

        sdb = MainActivity.mDatabaseHelper.getWritableDatabase();
        CreateCursor();

        if(retreat_array_max.size() != 0)
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
            }
    }

    public Cursor CreateCursor(){
        Cursor cursor = sdb.query(name_table, new String[]{DatabaseHelper._ID, DatabaseHelper.DATA,
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

        String set_number = "set_"+index_set;
        String retreat_number = "set_"+index_set+"_retreat_";
        String weight_number = "set_"+index_set+"_weight_";
        String data;
        String set,set_retreat_1,set_retreat_2,set_retreat_3,set_retreat_4,
                set_weight_1,set_weight_2,set_weight_3,set_weight_4;
        int retreat_max,weight_max;
        int retreat_min,weight_min;
        int retreat_avg,weight_avg;
        int sum;

        while (cursor.moveToNext()) {
            data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATA));
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
            Log.d(TAG, "set_1: " + set);
            Log.d(TAG, "set_1_retreat_1: " + set_retreat_1 + " set_1_weight_1: " + set_weight_1);
            Log.d(TAG, "set_1_retreat_2: " + set_retreat_2 + " set_1_weight_2: " + set_weight_2);
            Log.d(TAG, "set_1_retreat_3: " + set_retreat_3 + " set_1_weight_3: " + set_weight_3);
            Log.d(TAG, "set_1_retreat_4: " + set_retreat_4 + " set_1_weight_4: " + set_weight_4);

            if(set_retreat_1!=null) {
                number_asix++;
                osX_array.add(data);
                retreat_max = findMax(Integer.valueOf(set_retreat_1),Integer.valueOf(set_retreat_2),
                        Integer.valueOf(set_retreat_3),Integer.valueOf(set_retreat_4),
                        Integer.valueOf(set_weight_1),Integer.valueOf(set_weight_2),
                        Integer.valueOf(set_weight_3),Integer.valueOf(set_weight_4));
                retreat_min = findMin(Integer.valueOf(set_retreat_1), Integer.valueOf(set_retreat_2),
                        Integer.valueOf(set_retreat_3), Integer.valueOf(set_retreat_4),
                        Integer.valueOf(set_weight_1), Integer.valueOf(set_weight_2),
                        Integer.valueOf(set_weight_3), Integer.valueOf(set_weight_4));
                sum = findSum(Integer.valueOf(set_retreat_1),Integer.valueOf(set_retreat_2),
                        Integer.valueOf(set_retreat_3),Integer.valueOf(set_retreat_4),
                        Integer.valueOf(set_weight_1),Integer.valueOf(set_weight_2),
                        Integer.valueOf(set_weight_3),Integer.valueOf(set_weight_4));
                switch (retreat_max) {
                    case 0:
                        retreat_array_max.add(Integer.valueOf(set_retreat_1));
                        weight_array_max.add(Integer.valueOf(set_weight_1));
                        break;
                    case 1:
                        retreat_array_max.add(Integer.valueOf(set_retreat_2));
                        weight_array_max.add(Integer.valueOf(set_weight_2));
                        break;
                    case 2:
                        retreat_array_max.add(Integer.valueOf(set_retreat_3));
                        weight_array_max.add(Integer.valueOf(set_weight_3));
                        break;
                    case 3:
                        retreat_array_max.add(Integer.valueOf(set_retreat_4));
                        weight_array_max.add(Integer.valueOf(set_weight_4));
                        break;
                }
                switch (retreat_min) {
                    case 0:
                        retreat_array_min.add(Integer.valueOf(set_retreat_1));
                        weight_array_min.add(Integer.valueOf(set_weight_1));
                        break;
                    case 1:
                        retreat_array_min.add(Integer.valueOf(set_retreat_2));
                        weight_array_min.add(Integer.valueOf(set_weight_2));
                        break;
                    case 2:
                        retreat_array_min.add(Integer.valueOf(set_retreat_3));
                        weight_array_min.add(Integer.valueOf(set_weight_3));
                        break;
                    case 3:
                        retreat_array_min.add(Integer.valueOf(set_retreat_4));
                        weight_array_min.add(Integer.valueOf(set_weight_4));
                        break;
                }
                retreat_array_avg.add((float)(Integer.valueOf(set_retreat_1) + Integer.valueOf(set_retreat_2) +
                        Integer.valueOf(set_retreat_3) + Integer.valueOf(set_retreat_4))/4);

                weight_array_avg.add((float)(Integer.valueOf(set_weight_1) + Integer.valueOf(set_weight_2) +
                        Integer.valueOf(set_weight_3) + Integer.valueOf(set_weight_4))/4);
                retreat_array_sum.add(sum);
                weight_array_sum.add(sum);

            }
        }
        return cursor;
    }

    public int findSum(int r1, int r2, int r3, int r4, int w1, int w2, int w3, int w4){
        int sum;

        sum = r1*w1 + r2*w2 + r3*w3 + r4*w4;

        return sum;
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

    public int findMin(int r1, int r2, int r3, int r4, int w1, int w2, int w3, int w4){
        int[] r = new int[]{r1,r2,r3,r4};
        int[] w = new int[]{w1,w2,w3,w4};
        int min = 500;
        int[] imin_arr = new int[]{500,500,500,500};
        int imin = 0;
        ArrayList<Integer> ibuf = new ArrayList<>();

        for (int i = 0; i < w.length; i++) {
            if (w[i] < min) {
                min = w[i];
                imin = i;
            }
        }

        for(int i = 0; i < w.length; i++){
            if(w[i] == min)
                imin_arr[i] = i;
        }

        for(int i = 0; i < imin_arr.length; i++)
            if(imin_arr[i]!=500)
                ibuf.add(r[imin_arr[i]]);
            else
                ibuf.add(500);

        min = 500;

        for(int i=0; i<ibuf.size(); i++){
            if(ibuf.get(i) < min){
                min = ibuf.get(i);
                imin = i;
            }
        }

        return imin;
    }

    public static class PlaceholderFragment extends Fragment {

        private ComboLineColumnChartView chart;
        private ComboLineColumnChartData data;

        private int numberOfLines = 1;
        private int maxNumberOfLines = 1;
        private int numberOfPoints = osX_array.size();

        float[][] randomNumbersTab_w = new float[maxNumberOfLines][numberOfPoints];
        float[][] randomNumbersTab_r = new float[maxNumberOfLines][numberOfPoints];

        private boolean hasAxes = true;
        private boolean hasAxesNames = true;
        private boolean hasPoints = true;
        private boolean hasLines = true;
        private boolean isCubic = false;
        private boolean hasLabels = false;

        public RadioGroup radiogroup;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_combo_line_column_chart, container, false);

            chart = (ComboLineColumnChartView) rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());

            generateValues();
            generateData();

            radiogroup = (RadioGroup) rootView.findViewById(R.id.radioGroup1);

            radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // TODO Auto-generated method stub
                    switch (checkedId) {
                        case R.id.radio_max:
                            flag_graphic = 0;
                            reset();
                            generateValues();
                            generateData();
                            break;
                        case R.id.radio_min:
                            flag_graphic = 1;
                            reset();
                            generateValues();
                            generateData();
                            break;
                        case R.id.radio_avg:
                            flag_graphic = 2;
                            reset();
                            generateValues();
                            generateData();
                            break;
                        case R.id.radio_sum:
                            flag_graphic = 3;
                            reset();
                            generateValues();
                            generateData();
                            break;

                        default:
                            break;
                    }
                }
            });

            return rootView;
        }

        private void generateValues() {
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfPoints; ++j) {
                    switch (flag_graphic) {
                        case(0):
                            randomNumbersTab_r[i][j] = retreat_array_max.get(j);
                            randomNumbersTab_w[i][j] = weight_array_max.get(j);
                            break;
                        case(1):
                            randomNumbersTab_r[i][j] = retreat_array_min.get(j);
                            randomNumbersTab_w[i][j] = weight_array_min.get(j);
                            break;
                        case(2):
                            randomNumbersTab_r[i][j] = retreat_array_avg.get(j);
                            randomNumbersTab_w[i][j] = weight_array_avg.get(j);
                            break;
                        case(3):
                            randomNumbersTab_r[i][j] = retreat_array_sum.get(j);
                            randomNumbersTab_w[i][j] = weight_array_sum.get(j);
                            break;
                    }
                }
            }
        }

        private void reset() {
            numberOfLines = 1;

            hasAxes = true;
            hasAxesNames = true;
            hasLines = true;
            hasPoints = true;
            hasLabels = false;
            isCubic = false;

        }

        private void generateData() {
            // Chart looks the best when line data and column data have similar maximum viewports.
            data = new ComboLineColumnChartData(generateColumnData(), generateLineData());

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Дата");
                    //axisY.setName("Показатели");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            for(int i = 0; i < numberOfPoints; i++) {
                axisValues.add(new AxisValue(i).setLabel(osX_array.get(i)));
            }
            data.setAxisXBottom(new Axis(axisValues).setHasLines(true));
            chart.setComboLineColumnChartData(data);
        }

        private LineChartData generateLineData() {

            List<Line> lines = new ArrayList<Line>();
            for (int i = 0; i < numberOfLines; ++i) {

                List<PointValue> values = new ArrayList<PointValue>();
                for (int j = 0; j < numberOfPoints; ++j) {
                    values.add(new PointValue(j, randomNumbersTab_r[i][j]));
                }

                Line line = new Line(values);
                line.setColor(ChartUtils.COLOR_GREEN);
                line.setCubic(isCubic);
                line.setHasLabels(hasLabels);
                line.setHasLines(hasLines);
                line.setHasPoints(hasPoints);
                lines.add(line);
            }

            LineChartData lineChartData = new LineChartData(lines);

            return lineChartData;

        }

        private ColumnChartData generateColumnData() {
            int numSubcolumns = 1;
            int numColumns = osX_array.size();
            // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue(randomNumbersTab_w[j][i], ChartUtils.COLOR_VIOLET));
                }

                columns.add(new Column(values));
            }

            ColumnChartData columnChartData = new ColumnChartData(columns);
            return columnChartData;
        }


        private class ValueTouchListener implements ComboLineColumnChartOnValueSelectListener {

            @Override
            public void onValueDeselected() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                float val = value.getValue();
                Toast.makeText(getActivity(), "Вес: " + val, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value) {
                if(flag_graphic==3)
                    Toast.makeText(getActivity(), "Вес: " + value.getY(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Повторений: " + value.getY(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        number_asix = 0;
        osX_array.clear();
        retreat_array_max.clear();
        weight_array_max.clear();
        retreat_array_min.clear();
        weight_array_min.clear();
        retreat_array_avg.clear();
        weight_array_avg.clear();
        super.onBackPressed();
    }
}
