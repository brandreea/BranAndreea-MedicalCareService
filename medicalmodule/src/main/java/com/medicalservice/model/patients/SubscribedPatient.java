package com.medicalservice.model.patients;

import java.sql.Connection;
import java.util.List;
public class SubscribedPatient extends Patient {
    String name;
    String phone_number;
    int age;
//    long id=0;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    //  List<String>medical_history;
    public SubscribedPatient(Connection connection){

    }

    @Override
    public String toString() {
        return "Subscribed Patient: " +
                " " + name  +
                ", phone: " + phone_number +
                ", age: " + age +
                ", CNP: " + CNP +
                '\n';
    }

    public SubscribedPatient(int CNP, String name, String phone_number, int age) {
        super(CNP);
        this.name = name;
        this.phone_number = phone_number;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
