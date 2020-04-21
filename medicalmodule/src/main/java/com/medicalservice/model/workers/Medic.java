package com.medicalservice.model.workers;
import java.util.List;
public class Medic extends employee {
    String speciality;
    public Medic(String name, long CNP, long salary, List<Long> patientList) {
        super(name, CNP, salary);
    }
    public Medic(String name, String department, long CNP)
    {
        super(name,CNP);
        speciality=department;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return this.speciality;
    }

}
