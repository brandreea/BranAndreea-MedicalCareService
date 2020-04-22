package com.medicalservice.model.workers;
import java.util.Date;
public abstract class employee {
    String name;
    long CNP;
    long salary;
    Date hire_date;
    public employee(){
        this.name="none";
        this.CNP=0;
        this.salary=0;
    }
    public employee(String name, long CNP, long salary, Date hire_date) {
        this.name = name;
        this.CNP = CNP;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    public employee(String name, long CNP, long salary) {
        this.name = name;
        this.CNP = CNP;
        this.salary = salary;
       // this.hire_date = hire_date;
    }
    public employee(String name, long CNP) {
        this.name = name;
        this.CNP = CNP;

    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCNP() {
        return CNP;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getHire_date() {
        return hire_date;
    }


}
