package com.example.shahparan.designpart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shahparan.designpart.model.Student;

import java.util.ArrayList;

/**
 * Created by Shah Paran on 20-Feb-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "student_wg.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            TableAttribute tableAttribute = new TableAttribute();

            Log.i("Table Query",tableAttribute.getTableQuery());
            db.execSQL(tableAttribute.getTableQuery());
            Log.i("Table Create","Successfully");
    }

    public int insertData(String name,String phone,String email, String cgpa){

        int isDataInserted= 0;
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.STUDENT_NAME,name);
        contentValues.put(TableAttribute.STUDENT_PHONE,phone);
        contentValues.put(TableAttribute.STUDENT_EMAIL,email);
        contentValues.put(TableAttribute.STUDENT_CCGPA,cgpa);

        try{
            isDataInserted  = (int) db.insert(TableAttribute.TABLE_NAME,null,contentValues);
            Log.i("ValueInsert", "Successfully");
            Log.i("Value ID", String.valueOf(isDataInserted));
        }catch (SQLException e){
            Log.e("SqliteException",e.toString());
        }
        return  isDataInserted;
    }

    public ArrayList<Student> getAllData(){

        ArrayList<Student> studentArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM  "+TableAttribute.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            String tempName;
            String tempPhone;
            String tempEmail;
            String tempCgpa;

            while (!cursor.isAfterLast()){

                tempName = cursor.getString(cursor.getColumnIndex(TableAttribute.STUDENT_NAME));
                tempPhone = cursor.getString(cursor.getColumnIndex(TableAttribute.STUDENT_PHONE));
                tempEmail = cursor.getString(cursor.getColumnIndex(TableAttribute.STUDENT_EMAIL));
                tempCgpa = cursor.getString(cursor.getColumnIndex(TableAttribute.STUDENT_CCGPA));

                studentArrayList.add(new Student(tempName,tempPhone,tempEmail,tempCgpa));

                cursor.moveToNext();
            }

        }
        return  studentArrayList;
    }

    public int deleteItem(String phone){

        SQLiteDatabase db = this.getWritableDatabase();

        int tempDelete = 0;

        try{
            tempDelete = db.delete(TableAttribute.TABLE_NAME,TableAttribute.STUDENT_PHONE+"='"+phone+"'",null);
            Log.e("Delete_","Success");
        }catch (SQLException e){
            Log.e("Delete Problem","Happen");
        }finally {
            db.close();
        }

        return tempDelete;

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int updateItem(String phone) {

        int tempHoldUpdateValue = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableAttribute.STUDENT_NAME,"ABCD");
        contentValues.put(TableAttribute.STUDENT_PHONE,"8888889999");
        contentValues.put(TableAttribute.STUDENT_EMAIL,"abc@gmail.com");
        contentValues.put(TableAttribute.STUDENT_CCGPA,"3.55");

        try{
            tempHoldUpdateValue = db.update(TableAttribute.TABLE_NAME,contentValues,TableAttribute.STUDENT_PHONE+"='"+phone+"'",null);
        }catch (SQLException e){
            Log.e("Update","Problem");
        }finally {
            db.close();
        }

        return  tempHoldUpdateValue;
    }
}
