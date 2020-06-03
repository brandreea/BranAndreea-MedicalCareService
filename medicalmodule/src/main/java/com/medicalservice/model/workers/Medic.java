package com.medicalservice.model.workers;
import java.util.Date;
import java.util.List;
public class Medic extends employee {
    String speciality;

    public Medic(){

    }
    public Medic(String name, int CNP, int salary) {
        super(name, CNP, salary);
    }
    public Medic(String name, String department, int CNP)
    {
        super(name,CNP);
        speciality=department;
    }

    public Medic(int salary, Date hire_date, String speciality,String name, int CNP) {
        super(name,hire_date, CNP,salary);
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
        return name.toString()+" "+speciality.toString()+"\n";
    }
}
