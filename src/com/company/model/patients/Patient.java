package com.company.model.patients;

public abstract class Patient {
   long CNP;
   public Patient()
   {
       this.CNP=0;
   }

    public Patient(long CNP) {
        this.CNP = CNP;
    }

    public long getCNP() {
        return CNP;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }
}
