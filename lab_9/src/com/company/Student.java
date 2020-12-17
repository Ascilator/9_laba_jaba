package com.company;

import javax.swing.*;

public class Student {
    private int number;
    private String name;

    private String name_of_subject;
    private int mark;

    public Student() {
        this.name = this.name_of_subject  ="";
        this.number = this.mark = 0;
    }

    public Student(int number, String name, String name_of_subject, int mark) {
            this.name = name;
            this.number = number;
            this.name_of_subject = name_of_subject;
            this.mark = mark;
    }

    public Student(Student o) {
        this.name = o.name;
        this.number = o.number;
        this.name_of_subject = o.name_of_subject;
        this.mark = o.mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName_of_subject() {
        return name_of_subject;
    }

    public void setName_of_subject(String name_of_subject) {
        this.name_of_subject = name_of_subject;
    }

    public int getMark_field() {
        return mark;
    }

    public void setMark_field(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return number + "    " + name + "    " + name_of_subject + "    " + mark;
    }
    public String toString2() {
        return number + "    " + name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (number != student.number) return false;
        if (!name.equals(student.name)) return false;
        if (mark != student.mark) return false;
        return name_of_subject.equals(student.name_of_subject);

    }
}
