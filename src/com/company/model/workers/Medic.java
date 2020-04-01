package com.company.model.workers;
import java.util.List;
public class Medic extends employee {
    String Speciality;
    public Medic(String name, long CNP, long salary, List<Long> patientList) {
        super(name, CNP, salary);
    }
    public Medic(String name, String department, long CNP)
    {
        super(name,CNP);
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getSpeciality() {
        return Speciality;
    }

    @Override
    public void sth() {

    }
}
