package com.medicalservice.model.consults;

public class Consult {
    int doctor_CNP;
    int patient_CNP;
    static long count=0;
//    long id;
    String symptoms;
    String diagnose;
    int prescription;
    public Consult(){

    }
//    public long getId() {
//        return id;
//    }


    public Consult(int doctor_CNP, int patient_CNP, String symptoms, String diagnose) {
        this.doctor_CNP = doctor_CNP;
        this.patient_CNP = patient_CNP;
        this.symptoms = symptoms;
        this.diagnose = diagnose;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public Consult(int doctor_CNP, int patient_CNP) {
        count++;
//        this.id=count;
        this.doctor_CNP = doctor_CNP;
        this.patient_CNP = patient_CNP;
        this.prescription=-1;
    }
    public int getDoctor_CNP() {
        return doctor_CNP;
    }

    public void setDoctor_CNP(int doctor_CNP) {
        this.doctor_CNP = doctor_CNP;
    }

    public int getPatient_CNP() {
        return patient_CNP;
    }

    public void setPatient_CNP(int patient_CNP) {
        this.patient_CNP = patient_CNP;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public int getPrescription() {
        return prescription;
    }

    @Override
    public String toString() {
        return "Consult:" +
                "doctor CNP: " + doctor_CNP +
                ", patient CNP: " + patient_CNP +
                ", symptoms: " + symptoms +
                ", diagnose: " + diagnose +
                ", prescription: " + prescription +
                '\n';
    }

    public void setPrescription(int prescription) {
        this.prescription = prescription;
    }
}
