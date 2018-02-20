package com.example.shahparan.designpart.database;

/**
 * Created by Shah Paran on 20-Feb-18.
 */

public class TableAttribute {

    public static final String TABLE_NAME = "student_";
    public static final String STUDENT_ID = "id_";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_PHONE = "phone";
    public static final String STUDENT_EMAIL = "email";
    public static final String STUDENT_CCGPA = "cgpa";

    public String getTableQuery(){
            String tempQuery = "CREATE TABLE "+TABLE_NAME +"("+
                    STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    STUDENT_NAME+" TEXT,"+
                    STUDENT_PHONE+" TEXT,"+
                    STUDENT_EMAIL+" TEXT,"+
                    STUDENT_CCGPA+" TEXT)";

        return  tempQuery;
    }

}
