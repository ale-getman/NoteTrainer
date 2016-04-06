package com.android.ag.notetrainer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by User on 06.04.2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "db_trainer.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER_PROGRAM = "user_program";
    public static final String TABLE_PROGRAM = "program";
    public static final String TABLE_USER = "user";
    public static final String TABLE_TRAIN = "train";
    public static final String TABLE_WORK = "work";
    public static final String TABLE_PART_OF_BODY = "part_of_body";
    public static final String TABLE_EXERCISE = "exercise";

    public static final String ID_USER_PROGRAM = "id_user_program";
    public static final String ID_PROGRAM = "id_program";
    public static final String ID_USER = "id_user";
    public static final String ID_TRAIN = "id_train";
    public static final String ID_WORK = "id_work";
    public static final String ID_PART_OF_BODY = "id_part_of_body";
    public static final String ID_EXERCISE = "id_exercize";

    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String REPEAT = "repeat";
    public static final String WEIGHT = "weight";
    public static final String DATE = "date";
    public static final String PART_OF_BODY = "part_of_body";
    public static final String DESCRIPTION = "description";
    public static final String PHOTO = "photo";

    public static final String DB_CREATE_SCRIPT_1 = "create table " + TABLE_USER_PROGRAM + " (" + ID_USER_PROGRAM + " integer primary key autoincrement, "
            + ID_USER + " integer, " + ID_PROGRAM + " integer);" ;

    public static final String DB_CREATE_SCRIPT_2 = "create table " + TABLE_USER + " (" + ID_USER + " integer primary key autoincrement, "
            + NAME + " text, " + PASSWORD + " text);" ;

    public static final String DB_CREATE_SCRIPT_3 = "create table " + TABLE_PROGRAM + " (" + ID_PROGRAM + " integer primary key autoincrement, "
            + NAME + " text);" ;

    public static final String DB_CREATE_SCRIPT_4 = "create table " + TABLE_TRAIN + " (" + ID_TRAIN + " integer primary key autoincrement, "
            + ID_PROGRAM + " integer, " + ID_EXERCISE + " integer);" ;

    public static final String DB_CREATE_SCRIPT_5 = "create table " + TABLE_WORK + " (" + ID_WORK + " integer primary key autoincrement, "
            + REPEAT + " integer, " + WEIGHT + " integer, " + DATE + " text, " + ID_EXERCISE + " integer, " + ID_USER + " integer);" ;

    public static final String DB_CREATE_SCRIPT_6 = "create table " + TABLE_PART_OF_BODY + " (" + ID_PART_OF_BODY + " integer primary key autoincrement, "
            + PART_OF_BODY + " text);" ;

    public static final String DB_CREATE_SCRIPT_7 = "create table " + TABLE_EXERCISE + " (" + ID_EXERCISE + " integer primary key autoincrement, "
            + NAME + " text, " + DESCRIPTION + " text, " + PHOTO + " text, " + ID_PART_OF_BODY + " integer);" ;


    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_SCRIPT_1);
        db.execSQL(DB_CREATE_SCRIPT_2);
        db.execSQL(DB_CREATE_SCRIPT_3);
        db.execSQL(DB_CREATE_SCRIPT_4);
        db.execSQL(DB_CREATE_SCRIPT_5);
        db.execSQL(DB_CREATE_SCRIPT_6);
        db.execSQL(DB_CREATE_SCRIPT_7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROGRAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART_OF_BODY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        // Создаём новую таблицу
        onCreate(db);
    }
}
