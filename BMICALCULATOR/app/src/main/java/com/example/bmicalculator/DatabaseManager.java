package com.example.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String RECORD_TABLE = "RECORD_TABLE";
    public static final String COLUMN_RECORD_NAME = "RECORD_NAME";
    public static final String COLUMN_RECORD_AGE = "RECORD_AGE";
    public static final String COLUMN_RECORD_BMI = "RECORD_BMI";
    public static final String COLUMN_RECORD_STATUS = "RECORD_STATUS";
    public static final String COLUMN_ID = "ID";

    public DatabaseManager(@Nullable Context context) {
        super(context, "record.db", null, 1);
    }

    //Called the first time a database is accessed. There should be code to generate new table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + RECORD_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECORD_NAME + " TEXT, " + COLUMN_RECORD_AGE + " TEXT, " + COLUMN_RECORD_BMI + " TEXT, " + COLUMN_RECORD_STATUS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    //this is called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addOne(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RECORD_NAME, record.getName());
        cv.put(COLUMN_RECORD_AGE, record.getAge());
        cv.put(COLUMN_RECORD_BMI, record.getBMI());
        cv.put(COLUMN_RECORD_STATUS, record.getStatus());
        long insert = db.insert(RECORD_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<Record> getEveryone(){
        List<Record> recordList = new ArrayList<>();

        //Get data from database
        String queryString = "SELECT * FROM " + RECORD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //Loop through results and create Record objects and return to out list
            do {
                Record record_fetched = new Record(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                recordList.add(record_fetched);
            }while (cursor.moveToNext());
        }
        else{// Failure to add anything
            }

        //Close both the cursor and the db when done
        cursor.close();
        db.close();
        return recordList;
    }

    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "DELETE FROM " + RECORD_TABLE;
        db.execSQL(sqlQuery);
        db.close();
    }
}
