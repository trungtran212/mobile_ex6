package com.example.mobile_th6;

public class Student {
    private int id;
    private String name;
    private String mssv;
    private byte[] avatar;

    public Student(int id, String name, String mssv, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.mssv = mssv;
        this.avatar = avatar;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMssv() { return mssv; }
    public byte[] getAvatar() { return avatar; }
}
