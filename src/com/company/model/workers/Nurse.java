package com.company.model.workers;

public class Nurse extends employee {
    String department;

    public Nurse(String name, long CNP, long salary, String department) {
        super(name, CNP, salary);
        this.department = department;
    }

    public Nurse(String name, long CNP, String department) {
        super(name, CNP);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void sth() {

    }
}
