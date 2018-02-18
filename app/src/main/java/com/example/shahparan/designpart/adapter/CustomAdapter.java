package com.example.shahparan.designpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahparan.designpart.R;
import com.example.shahparan.designpart.model.Student;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<Student> arrayList;
    Context context;

    public CustomAdapter(ArrayList<Student> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View customView, ViewGroup viewGroup) {

        final Student student;
        ViewHolder viewHolder;

        if(customView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            customView = inflater.inflate(R.layout.list_view_item, null);
            viewHolder.textViewStudentName = customView.findViewById(R.id.showName);
            viewHolder.textViewStudentPhone = customView.findViewById(R.id.showPhone);
            viewHolder.textViewStudentEmail = customView.findViewById(R.id.showEmail);
            viewHolder.imageViewStudentCall = customView.findViewById(R.id.call);
            customView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) customView.getTag();
        }

        student = arrayList.get(position);
        viewHolder.textViewStudentName.setText(student.getName());
        viewHolder.textViewStudentPhone.setText(student.getPhone());
        viewHolder.textViewStudentEmail.setText(student.getEmail());
        viewHolder.imageViewStudentCall.setImageResource(R.drawable.call);

        viewHolder.imageViewStudentCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,student.getName(),Toast.LENGTH_LONG).show();
            }
        });

        return customView;
    }

    private class ViewHolder{

        TextView textViewStudentName;
        TextView textViewStudentPhone;
        TextView textViewStudentEmail;
        ImageView imageViewStudentCall;
    }


}
