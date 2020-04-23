package com.medicalservice.model.patients;

import java.util.List;
public class SubscribedPatient extends Patient {
    String name;
    long phone_number;
    int age;
  //  List<String>medical_history;
    public SubscribedPatient(){

    }
    public SubscribedPatient(long CNP, String name, long phone_number, int age) {
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
//    public List<String> getMedical_history() {
//        return medical_history;
//    }

//   // public void setMedical_history(List<String> medical_history) {
//        this.medical_history = medical_history;
//    }
 //   public void addInMedicalHistory(String problem)
//    {
//        this.medical_history.add(problem);
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

}
