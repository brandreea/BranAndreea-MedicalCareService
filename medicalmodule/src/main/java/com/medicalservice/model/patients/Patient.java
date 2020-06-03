package com.medicalservice.model.patients;

public abstract class Patient {
   int CNP;

   public Patient()
   {
       this.CNP=0;
   }

    public Patient(int CNP) {
        this.CNP = CNP;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }
}
