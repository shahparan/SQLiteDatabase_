package com.example.shahparan.designpart;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shahparan.designpart.adapter.CustomAdapter;
import com.example.shahparan.designpart.database.DatabaseHelper;
import com.example.shahparan.designpart.model.Student;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,phone,email,cgpa;
    Button show;
    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayList<Student> arrayListStudent;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        cgpa = findViewById(R.id.cgpa);

        listView = findViewById(R.id.myListView);

        arrayListStudent = new ArrayList<>();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        arrayListStudent.addAll(databaseHelper.getAllData());
        customAdapter = new CustomAdapter(arrayListStudent,MainActivity.this);
        listView.setAdapter(customAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                updateItem(arrayListStudent.get(position).getPhone());
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                deleteItem(arrayListStudent.get(position).getPhone());
            }
        });
    }


    private void deleteItem(final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Delete Item");
        builder.setMessage("Are You Sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper = new DatabaseHelper(MainActivity.this);
                if(databaseHelper.deleteItem(phone)> 0){
                    updateAdapter();
                    Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Delete Problem", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }


    private void updateItem(final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Update Item");
        builder.setMessage("Are You Sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper = new DatabaseHelper(MainActivity.this);
                if(databaseHelper.updateItem(phone)> 0){
                    updateAdapter();
                    Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Update Problem", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }


    public void updateAdapter(){
        arrayListStudent.clear();
        arrayListStudent.addAll(databaseHelper.getAllData());
        customAdapter.notifyDataSetChanged();
    }

    public void clearEditField(){

        name.getText().clear();
        phone.getText().clear();
        email.getText().clear();
        cgpa.getText().clear();
    }

    public void show(View view) {

        if(checkValidation()==false){
            databaseHelper = new DatabaseHelper(MainActivity.this);
            int isDataInserted = 0;
            isDataInserted = databaseHelper.insertData(name.getText().toString(),phone.getText().toString(),email.getText().toString(),cgpa.getText().toString());
            if(isDataInserted > 0){
                updateAdapter();
                clearEditField();
                Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(MainActivity.this,"Validation Problem..",Toast.LENGTH_LONG).show();
        }
    }


    private Boolean checkValidation() {

        String nameTemp;
        String phoneTemp;
        String emailTemp;
        String cgpaTemp;

        nameTemp = name.getText().toString();
        phoneTemp = phone.getText().toString();
        emailTemp = email.getText().toString();
        cgpaTemp = cgpa.getText().toString();

        Boolean flag=false;
        if(emailTemp.isEmpty()){
            email.setError("Email Field is Empty");
            flag = true;
        }
        if(nameTemp.isEmpty()){
            name.setError("Name Field is Empty");
            flag = true;
        }
        if(phoneTemp.isEmpty()){
            phone.setError("Phone Number Field is Empty");
            flag = true;
        }else {
            if (phoneTemp.length() < 10)
            {
                phone.setError("Number is too Short..");
                flag = true;
            }
        }
        if(cgpaTemp.isEmpty()){
            cgpa.setError("CGPA Field is Empty");
            flag = true;
        }else {
            if(cgpaTemp.length()==4){
                Float cgpaFloat = Float.valueOf(cgpaTemp);
                if((cgpaFloat > 4.0) || (cgpaFloat < 2.0)){
                    cgpa.setError("CGPA is not Valid");
                    flag = true;
                }
            }else{
                cgpa.setError("CGPA is not Valid");
                flag = true;
            }
        }

        return  flag;
    }
}
