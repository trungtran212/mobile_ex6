package com.example.mobile_th6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> students;

    public StudentAdapter(Context context, int resource, List<Student> students) {
        super(context, resource, students);
        this.context = context;
        this.resource = resource;
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(resource, parent, false);
        Student student = students.get(position);

        TextView nameView = view.findViewById(R.id.txtName);
        TextView mssvView = view.findViewById(R.id.txtMSSV);
        ImageView avatarView = view.findViewById(R.id.imgAvatar);

        nameView.setText(student.getName());
        mssvView.setText(student.getMssv());

        if (student.getAvatar() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(student.getAvatar(), 0, student.getAvatar().length);
            avatarView.setImageBitmap(bmp);
        }

        return view;
    }
}
