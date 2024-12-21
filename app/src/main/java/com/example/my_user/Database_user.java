package com.example.my_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_user extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_signin.db";
    private static final String col_1="ID";
    private static final String col_2="USERNAME";
    private static final String col_3="PASSWORD";
    private static final String col_4="PHONE";
    private static final String col_5="GMAIL";

    public Database_user(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,PHONE TEXT,GMAIL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);

    }

    public boolean insertdata(String username,String password,String phone,String gmail){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,username);
        contentValues.put(col_3,password);
        contentValues.put(col_4,phone);
        contentValues.put(col_5,gmail);
        long result=db.insert("USERS",null,contentValues);
        return result!=-1;
    }

    public String checklogin(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] columns={col_2};
        String selection ="USERNAME=? AND PASSWORD=?";
        String[] selectionArgs={username,password};
        Cursor cursor=db.query("USERS",columns,selection,selectionArgs,null,null,null);
        String result=null;
        if(cursor!=null && cursor.moveToFirst()){
            result=cursor.getString(cursor.getColumnIndex(col_2));
            cursor.close();
        }
        return  result;



    }


    public Cursor getUserData(String currentUserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM USERS", null);
    }

}
