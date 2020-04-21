package com.medicalservice.model.patients;

public class UnsubscribedPatient extends Patient {
    String diagnostic;

    public UnsubscribedPatient(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public UnsubscribedPatient(long CNP, String diagnostic) {
        super(CNP);
        this.diagnostic = diagnostic;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
