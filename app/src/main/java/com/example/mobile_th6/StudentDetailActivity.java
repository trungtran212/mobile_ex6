package com.example.mobile_th6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_th6.db.DatabaseHelper;

public class StudentDetailActivity extends AppCompatActivity {
    TextView nameView, mssvView;
    ImageView avatarView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        nameView = findViewById(R.id.txtDetailName);
        mssvView = findViewById(R.id.txtDetailMSSV);
        avatarView = findViewById(R.id.imgDetailAvatar);

        int id = getIntent().getIntExtra("student_id", -1);
        dbHelper = new DatabaseHelper(this);
        Student student = dbHelper.getStudentById(id);

        if (student != null) {
            nameView.setText(student.getName());
            mssvView.setText(student.getMssv());
            if (student.getAvatar() != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(student.getAvatar(), 0, student.getAvatar().length);
                avatarView.setImageBitmap(bmp);
            }
        }
    }
}
