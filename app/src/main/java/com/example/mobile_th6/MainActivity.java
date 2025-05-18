package com.example.mobile_th6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_th6.db.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Student> students;
    DatabaseHelper dbHelper;

    private long lastClickTime = 0;
    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listViewStudents);

        dbHelper = new DatabaseHelper(this);
        students = dbHelper.getAllStudents();

        StudentAdapter adapter = new StudentAdapter(this, R.layout.student_item, students);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                Student student = students.get(position);
                Intent intent = new Intent(this, StudentDetailActivity.class);
                intent.putExtra("student_id", student.getId());
                startActivity(intent);
            }
            lastClickTime = clickTime;
        });
    }
}