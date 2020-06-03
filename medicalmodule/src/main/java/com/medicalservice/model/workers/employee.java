package com.medicalservice.model.workers;
import java.util.Date;
public abstract class employee {
    String name;
    Date hire_date;
    int CNP;
    int salary;
    int id=0;
    public employee(){
        this.name="none";
        this.CNP=0;
        this.salary=0;
    }
    public employee(String name, Date hire_date, int CNP, int salary) {
        this.name = name;
        this.CNP = CNP;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    public employee(String name, int CNP, int salary) {
        this.name = name;
        this.CNP = CNP;
        this.salary = salary;
       // this.hire_date = hire_date;
    }
    public employee(String name, int CNP) {
        this.name = name;
        this.CNP = CNP;

    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
