package com.android.ag.notetrainer;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by User on 13.11.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns{

    public static final String DATABASE_NAME = "mydatabase.db";
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_TABLE = "Hands";
    public static final String DATABASE_TABLE_2 = "Legs";
    public static final String DATABASE_TABLE_3 = "Back";
    public static final String DATABASE_TABLE_4 = "Press";
    public static final String TABLE_NAME[]= {"Hands","Legs","Back"};
    private static final String DB_CREATE_SCRIPT[]= new String[3];
    public static final String DATA = "Data";

    public static final String SET_1 = "set_1";
    public static final String SET_1_RETREAT_1 = "set_1_retreat_1";
    public static final String SET_1_WEIGHT_1 = "set_1_weight_1";
    public static final String SET_1_RETREAT_2 = "set_1_retreat_2";
    public static final String SET_1_WEIGHT_2 = "set_1_weight_2";
    public static final String SET_1_RETREAT_3 = "set_1_retreat_3";
    public static final String SET_1_WEIGHT_3 = "set_1_weight_3";
    public static final String SET_1_RETREAT_4 = "set_1_retreat_4";
    public static final String SET_1_WEIGHT_4 = "set_1_weight_4";

    public static final String SET_2 = "set_2";
    public static final String SET_2_RETREAT_1 = "set_2_retreat_1";
    public static final String SET_2_WEIGHT_1 = "set_2_weight_1";
    public static final String SET_2_RETREAT_2 = "set_2_retreat_2";
    public static final String SET_2_WEIGHT_2 = "set_2_weight_2";
    public static final String SET_2_RETREAT_3 = "set_2_retreat_3";
    public static final String SET_2_WEIGHT_3 = "set_2_weight_3";
    public static final String SET_2_RETREAT_4 = "set_2_retreat_4";
    public static final String SET_2_WEIGHT_4 = "set_2_weight_4";

    public static final String SET_3 = "set_3";
    public static final String SET_3_RETREAT_1 = "set_3_retreat_1";
    public static final String SET_3_WEIGHT_1 = "set_3_weight_1";
    public static final String SET_3_RETREAT_2 = "set_3_retreat_2";
    public static final String SET_3_WEIGHT_2 = "set_3_weight_2";
    public static final String SET_3_RETREAT_3 = "set_3_retreat_3";
    public static final String SET_3_WEIGHT_3 = "set_3_weight_3";
    public static final String SET_3_RETREAT_4 = "set_3_retreat_4";
    public static final String SET_3_WEIGHT_4 = "set_3_weight_4";

    public static final String SET_4 = "set_4";
    public static final String SET_4_RETREAT_1 = "set_4_retreat_1";
    public static final String SET_4_WEIGHT_1 = "set_4_weight_1";
    public static final String SET_4_RETREAT_2 = "set_4_retreat_2";
    public static final String SET_4_WEIGHT_2 = "set_4_weight_2";
    public static final String SET_4_RETREAT_3 = "set_4_retreat_3";
    public static final String SET_4_WEIGHT_3 = "set_4_weight_3";
    public static final String SET_4_RETREAT_4 = "set_4_retreat_4";
    public static final String SET_4_WEIGHT_4 = "set_4_weight_4";

    public static final String SET_5 = "set_5";
    public static final String SET_5_RETREAT_1 = "set_5_retreat_1";
    public static final String SET_5_WEIGHT_1 = "set_5_weight_1";
    public static final String SET_5_RETREAT_2 = "set_5_retreat_2";
    public static final String SET_5_WEIGHT_2 = "set_5_weight_2";
    public static final String SET_5_RETREAT_3 = "set_5_retreat_3";
    public static final String SET_5_WEIGHT_3 = "set_5_weight_3";
    public static final String SET_5_RETREAT_4 = "set_5_retreat_4";
    public static final String SET_5_WEIGHT_4 = "set_5_weight_4";

    public static final String SET_6 = "set_6";
    public static final String SET_6_RETREAT_1 = "set_6_retreat_1";
    public static final String SET_6_WEIGHT_1 = "set_6_weight_1";
    public static final String SET_6_RETREAT_2 = "set_6_retreat_2";
    public static final String SET_6_WEIGHT_2 = "set_6_weight_2";
    public static final String SET_6_RETREAT_3 = "set_6_retreat_3";
    public static final String SET_6_WEIGHT_3 = "set_6_weight_3";
    public static final String SET_6_RETREAT_4 = "set_6_retreat_4";
    public static final String SET_6_WEIGHT_4 = "set_6_weight_4";

    {
        for (int i = 0; i < 3; i++)
            DB_CREATE_SCRIPT[i] = "create table "
                    + TABLE_NAME[i] + " (" + BaseColumns._ID
                    + " integer primary key autoincrement, " + DATA + " text not null, "
                    + SET_1 + " text, "
                    + SET_1_RETREAT_1 + " text, " + SET_1_WEIGHT_1 + " text, " + SET_1_RETREAT_2 + " text, " + SET_1_WEIGHT_2 + " text, "
                    + SET_1_RETREAT_3 + " text, " + SET_1_WEIGHT_3 + " text, " + SET_1_RETREAT_4 + " text, " + SET_1_WEIGHT_4 + " text, "
                    + SET_2 + " text, "
                    + SET_2_RETREAT_1 + " text, " + SET_2_WEIGHT_1 + " text, " + SET_2_RETREAT_2 + " text, " + SET_2_WEIGHT_2 + " text, "
                    + SET_2_RETREAT_3 + " text, " + SET_2_WEIGHT_3 + " text, " + SET_2_RETREAT_4 + " text, " + SET_2_WEIGHT_4 + " text, "
                    + SET_3 + " text, "
                    + SET_3_RETREAT_1 + " text, " + SET_3_WEIGHT_1 + " text, " + SET_3_RETREAT_2 + " text, " + SET_3_WEIGHT_2 + " text, "
                    + SET_3_RETREAT_3 + " text, " + SET_3_WEIGHT_3 + " text, " + SET_3_RETREAT_4 + " text, " + SET_3_WEIGHT_4 + " text, "
                    + SET_4 + " text, "
                    + SET_4_RETREAT_1 + " text, " + SET_4_WEIGHT_1 + " text, " + SET_4_RETREAT_2 + " text, " + SET_4_WEIGHT_2 + " text, "
                    + SET_4_RETREAT_3 + " text, " + SET_4_WEIGHT_3 + " text, " + SET_4_RETREAT_4 + " text, " + SET_4_WEIGHT_4 + " text, "
                    + SET_5 + " text, "
                    + SET_5_RETREAT_1 + " text, " + SET_5_WEIGHT_1 + " text, " + SET_5_RETREAT_2 + " text, " + SET_5_WEIGHT_2 + " text, "
                    + SET_5_RETREAT_3 + " text, " + SET_5_WEIGHT_3 + " text, " + SET_5_RETREAT_4 + " text, " + SET_5_WEIGHT_4 + " text, "
                    + SET_6 + " text, "
                    + SET_6_RETREAT_1 + " text, " + SET_6_WEIGHT_1 + " text, " + SET_6_RETREAT_2 + " text, " + SET_6_WEIGHT_2 + " text, "
                    + SET_6_RETREAT_3 + " text, " + SET_6_WEIGHT_3 + " text, " + SET_6_RETREAT_4 + " text, " + SET_6_WEIGHT_4 + " text);";
    }

    public static final String DB_CREATE_SCRIPT_2 = "create table " + DATABASE_TABLE_4 + " (" + BaseColumns._ID + " integer primary key autoincrement, " + DATA + " text not null, "
            + SET_1_RETREAT_1 + " text, " + SET_1_WEIGHT_1 + " text, " + SET_1_RETREAT_2 + " text, " + SET_1_WEIGHT_2 + " text, "
            + SET_1_RETREAT_3 + " text, " + SET_1_WEIGHT_3 + " text, " + SET_1_RETREAT_4 + " text, " + SET_1_WEIGHT_4 + " text);" ;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_SCRIPT[0]);
        db.execSQL(DB_CREATE_SCRIPT[1]);
        db.execSQL(DB_CREATE_SCRIPT[2]);
        db.execSQL(DB_CREATE_SCRIPT_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME[0]);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME[1]);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME[2]);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_4);
        // Создаём новую таблицу
        onCreate(db);
    }
}
