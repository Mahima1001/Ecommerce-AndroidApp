package com.project.mahima.happyshopping.com.DatabasePackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by mahima on 22/4/17.
 */
public class MyDatabase {
    DbHelper helper;

    public MyDatabase(DbHelper helper){
        this.helper= helper;
    }



    public void insert(String username,
                       String password,
                       String address,
                       String email,
                       String mobile){
        SQLiteDatabase sqdb= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("Username",username);
        values.put("Password",password);
        values.put("Address",address);
        values.put("Email",email);
        values.put("Mobile",mobile);

        sqdb.insert("logininfo",null,values);

        sqdb.close();
    }


    public void insertquery(String username,
                       String email,
                       String message){
        SQLiteDatabase sqdb= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("Username",username);
        values.put("Email",email);
        values.put("Message",message);

        long id = sqdb.insert("userquery",null,values);
        Log.e("mahima","Message ID: " + username);
        sqdb.close();
    }

    public Cursor fetchquery(){
        SQLiteDatabase sqdb= helper.getReadableDatabase();
        String table="userquery";
        String[] columns= new String[]{"Username","Email","Message"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy= null;
        String having= null;
        String orderBy= null;

        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        return  cursor;
    }



    public String CheckForUniqueUsername(String name){
        String table="logininfo";
        String[] columns= new String[]{"Username"};
        String selection = "Username=?";
        String[] selectionArgs = new String[]{name};
        String groupBy= null;
        String having= null;
        String orderBy= null;

        String name_found=null;

        SQLiteDatabase sqdb= helper.getReadableDatabase();
        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        while(cursor.moveToNext()){
            name_found=cursor
                    .getString(cursor
                            .getColumnIndex("Username"));
            if(name_found.equals(name)){
                cursor.close();
                sqdb.close();
                return"You cannot use this username";
            }
        }
        cursor.close();
        sqdb.close();
        return "You can use this username";
    }

    public String CheckForUniquePhone(String phone){
        String table="logininfo";
        String[] columns= new String[]{"Mobile"};
        String selection = "Mobile=?";
        String[] selectionArgs = new String[]{phone};
        String groupBy= null;
        String having= null;
        String orderBy= null;

        String mobile_found=null;

        SQLiteDatabase sqdb= helper.getReadableDatabase();
        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        while(cursor.moveToNext()){
            mobile_found=cursor
                    .getString(cursor
                            .getColumnIndex("Mobile"));
            if(mobile_found.equals(phone)){
                cursor.close();
                sqdb.close();
                return"You cannot use this phone no";
            }
        }
        cursor.close();
        sqdb.close();
        return "You can use this phone no";

    }

    public String CheckLogin(String username, String password){
        String table="logininfo";
        String[] columns= new String[]{"Password"};
        String selection = "Username=?";
        String[] selectionArgs = new String[]{username};
        String groupBy= null;
        String having= null;
        String orderBy= null;

        String password_found=null;

        SQLiteDatabase sqdb= helper.getReadableDatabase();
        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        while(cursor.moveToNext()){
            password_found=cursor
                    .getString(cursor
                            .getColumnIndex("Password"));
            if(password_found.equals(password)){
                cursor.close();
                sqdb.close();
                return"Allow Login";
            }
        }
        cursor.close();
        sqdb.close();
        return "Deny Login";
    }

    public void DeleteAccount(String username){
        SQLiteDatabase sqdb= helper.getWritableDatabase();
        String table="logininfo";
        String whereClause="Username=?";
        String[] whereArgs= new String[]{username};

        sqdb.delete(table,whereClause,whereArgs);
        sqdb.close();
    }

    public Cursor getInformation(String username){
        SQLiteDatabase sqdb= helper.getReadableDatabase();

        String table="logininfo";
        String[] columns= new String[]{"Password","Email","Mobile"};
        String selection = "Username=?";
        String[] selectionArgs = new String[]{username};
        String groupBy= null;
        String having= null;
        String orderBy= null;

        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        return  cursor;
    }

    public void getEdit(String name, String password,String email,String phone,String oldname){
        SQLiteDatabase sqdb= helper.getWritableDatabase();

        String table="logininfo";
        ContentValues values= new ContentValues();
        values.put("Username",name);
        values.put("Password",password);
        values.put("Email",email);
        values.put("Mobile",phone);
        String whereClause = "Username=?";
        String[] whereArgs = new String[]{oldname};
        sqdb.update(table,values,whereClause,whereArgs);
        sqdb.close();
    }

    public void insertimage(String imagename ,
                       String image ,
                            String cost ,
                            String imagetype ,
                            String imagedesc){
        SQLiteDatabase sqdb= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("imagename",imagename);
        values.put("image",image);
        values.put("cost",cost);
        values.put("imagetype",imagetype);
        values.put("imagedesc",imagedesc);
        long id = sqdb.insert("image",null,values);
        Log.e("mahima","Image insert: " + String.valueOf(id));

        sqdb.close();
    }

    public void addtocart(String imagename ,
                          String image ,
                          String cost ,
                          String imagetype ,
                          String imagedesc,
                          String user){
        SQLiteDatabase sqdb= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("imagename",imagename);
        values.put("image",image);
        values.put("cost",cost);
        values.put("imagetype",imagetype);
        values.put("imagedesc",imagedesc);
        values.put("user",user);
        long id = sqdb.insert("cart",null,values);
        Log.e("mahima","Add to cart ID: " + String.valueOf(id));

        sqdb.close();
    }


    public Cursor getImage(String imagename,String type){
        SQLiteDatabase sqdb= helper.getReadableDatabase();

        String table="image";
        String[] columns= new String[]{"imagename","image","imagetype"};
        String selection = "imagename=? and imagetype=?";
        String[] selectionArgs = new String[]{imagename,type};
        String groupBy= null;
        String having= null;
        String orderBy= null;

        Cursor cursor= sqdb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        return  cursor;
    }

    public Cursor getAllImage(String type){
        SQLiteDatabase sqdb= helper.getReadableDatabase();

       String sql = "select *  from image where imagetype = ?";

        Cursor cursor= sqdb.rawQuery(sql,new String[]{type});
        return  cursor;
    }

    public Cursor getCart(String user){
        SQLiteDatabase sqdb= helper.getReadableDatabase();

        String sql = "select *  from cart where user = ?";

        Cursor cursor= sqdb.rawQuery(sql,new String[]{user});
        return  cursor;
    }

}

