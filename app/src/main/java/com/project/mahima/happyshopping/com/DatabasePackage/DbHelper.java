package com.project.mahima.happyshopping.com.DatabasePackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahima on 22/4/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table logininfo(Username text,Password text,Address text,Email text,Mobile text)");
        db.execSQL("Create table userquery(Username text,Email text,Message text)");
        db.execSQL("Create table image(imagename text,image text,cost text,imagetype text,imagedesc text)");
        db.execSQL("Create table cart(imagename text,image text,cost text,imagetype text,imagedesc text,user text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "logininfo");
        db.execSQL("DROP TABLE IF EXISTS " + "userquery");
        onCreate(db);
    }
}