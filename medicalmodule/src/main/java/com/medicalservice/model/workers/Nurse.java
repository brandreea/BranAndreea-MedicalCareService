package com.medicalservice.model.workers;

import java.util.Date;

public class Nurse extends employee {
    String department;
    public Nurse(){

    }
    public Nurse(String name, Date hire_date, int CNP, int salary, String department) {
        super(name, hire_date,CNP, salary);

        this.department = department;
    }

    public Nurse(String name, int CNP, String department) {
        super(name, CNP);
        this.department = department;
    }

    @Override
    public String toString() {
        return "Nurse from " +
                "department " + department + '\'' +
                "," + name + '\'' +
                ", hired on " + hire_date +
                "  with CNP:" + CNP +
                " and salary" + salary +
                '\n';
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
