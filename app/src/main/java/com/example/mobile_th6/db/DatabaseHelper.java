package com.example.mobile_th6.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mobile_th6.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "students.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mssv TEXT, avatar BLOB)");

        insertStudent(db, "Nguyễn Văn A", "SV001");
        insertStudent(db, "Trần Thị B", "SV002");
        insertStudent(db, "Lê Văn C", "SV003");
        insertStudent(db, "Phạm Quốc D", "SV004");
        insertStudent(db, "Đặng Thị E", "SV005");
        insertStudent(db, "Hoàng Văn F", "SV006");
        insertStudent(db, "Ngô Thị G", "SV007");
        insertStudent(db, "Võ Văn H", "SV008");
        insertStudent(db, "Đỗ Thị I", "SV009");
        insertStudent(db, "Bùi Văn J", "SV010");
    }

    // Helper method
    private void insertStudent(SQLiteDatabase db, String name, String mssv) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("mssv", mssv);
        db.insert("Student", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Student", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String mssv = cursor.getString(2);
                byte[] avatar = cursor.getBlob(3);
                list.add(new Student(id, name, mssv, avatar));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Student WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String mssv = cursor.getString(2);
            byte[] avatar = cursor.getBlob(3);
            return new Student(id, name, mssv, avatar);
        }
        cursor.close();
        db.close();
        return null;
    }
}
