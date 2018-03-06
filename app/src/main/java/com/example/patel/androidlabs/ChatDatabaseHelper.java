package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Message;

public class ChatDatabaseHelper extends SQLiteOpenHelper {


      public static final String DATABASE_NAME ="Message.db";

      public static final String KEY_ID="_id";

      public static final String KEY_MESSAGE="message";

      public static final String TABLE_NAME ="Dta";

      public static final int VERSION_NUM=6;


     public ChatDatabaseHelper(Context ctx){
            super(ctx,DATABASE_NAME,null,VERSION_NUM);
     }

    @Override
     public void onCreate(SQLiteDatabase db){
   db.execSQL("CREATE TABLE " + TABLE_NAME + "( "+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + KEY_MESSAGE + " TEXT);");
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

      db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);

       onCreate(db);


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
    }
}
