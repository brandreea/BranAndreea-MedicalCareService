package com.company.model.consults;
import java.util.List;
public class Consult {
    long doctor_CNP;
    long patient_CNP;
    static long count=0;
    long id;
    List<String>symptoms;
    List<String>diagnose;
    Prescription prescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Consult(long doctor_CNP, long patient_CNP) {
        count++;
        this.id=count;
        this.doctor_CNP = doctor_CNP;
        this.patient_CNP = patient_CNP;
        this.prescription=new Prescription();
    }
    public void addSymptoms(String s)
    {
        symptoms.add(s);
    }
    public void addDiagnose(String d)
    {
        symptoms.add(d);
    }
    public long getDoctor_CNP() {
        return doctor_CNP;
    }

    public void setDoctor_CNP(long doctor_CNP) {
        this.doctor_CNP = doctor_CNP;
    }

    public long getPatient_CNP() {
        return patient_CNP;
    }

    public void setPatient_CNP(long patient_CNP) {
        this.patient_CNP = patient_CNP;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public List<String> getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(List<String> diagnose) {
        this.diagnose = diagnose;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
