package com.company;

import com.company.Student;

import java.util.Comparator;


public class myComparator implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        return o2.getNumber() - o1.getNumber();
    }
}