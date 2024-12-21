package com.example.my_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_preference extends SQLiteOpenHelper {
    private static final String PREFERENCE_DATABASE = "preference.db";
    private static final int DATABASE_VERSION = 1;
    private static final String COL_ID = "ID";
    private static final String COL_1 = "CAR_SEAT";
    private static final String COL_2 = "SHIFT";
    private static final String COL_3 = "DISTANCE";
    private static final String COL_4 = "PAYMENT_METHOD";




    public Database_preference(@Nullable Context context) {
        super(context,PREFERENCE_DATABASE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER_PREFERENCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,CAR_SEAT INTEGER,SHIFT TEXT,DISTANCE INTEGER,PAYMENT_METHOD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USER_PREFERENCE");
        onCreate(db);
    }

    public boolean insertdata(int car_seat,String shift,int distance,String payment_method){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, car_seat);
        contentValues.put(COL_2, distance);
        contentValues.put(COL_3, shift);
        contentValues.put(COL_4, payment_method);
        long result = db.insert("USER_PREFERENCE", null, contentValues);
        return result != -1;
    }
    public Cursor getPreferenceData(String currentUserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM USER_PREFERENCE", null);
    }


}
