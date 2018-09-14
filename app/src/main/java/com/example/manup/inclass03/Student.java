package com.example.manup.inclass03;

import java.io.Serializable;

/**
 * Created by manup on 1/29/2018.
 */

public class Student implements Serializable {
    String name;
    String email;
    String department;
    String mood;

    public Student(String name, String email, String department, String mood) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
