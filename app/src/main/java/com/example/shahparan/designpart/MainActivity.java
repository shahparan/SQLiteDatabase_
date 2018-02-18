package com.example.shahparan.designpart;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shahparan.designpart.adapter.CustomAdapter;
import com.example.shahparan.designpart.model.Student;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,phone,email,cgpa;
    Button show;
    ListView listView;

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
        customAdapter = new CustomAdapter(arrayListStudent,MainActivity.this);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                name.setText(arrayListStudent.get(position).getName().toString()+"Edited..");
            }
        });

    }


    public void show(View view) {

        if(checkValidation()==false){
            arrayListStudent.add(new Student(name.getText().toString(),
                    phone.getText().toString(),email.getText().toString(),cgpa.getText().toString()));
            customAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"Data Not Valid",Toast.LENGTH_LONG).show();
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

    private Boolean checfffk(){
        //rn
        return true;
    }
}
