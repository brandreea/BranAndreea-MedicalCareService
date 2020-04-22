package com.medicalservice.model.workers;
import java.util.Date;
import java.util.List;
public class Medic extends employee {
    String speciality;
    public Medic(){

    }
    public Medic(String name, long CNP, long salary) {
        super(name, CNP, salary);
    }
    public Medic(String name, String department, long CNP)
    {
        super(name,CNP);
        speciality=department;
    }

    public Medic(long salary, Date hire_date, String speciality,String name, long CNP) {
        super(name, CNP, salary, hire_date);
        this.speciality = speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return this.speciality;
    }
    @Override
    public String toString(){
        return name.toString()+" "+speciality.toString();
    }
}
