package com.medicalservice.service;

import com.medicalservice.model.patients.UnsubscribedPatient;
import com.medicalservice.repository.UnsubscribedPatientRepository;

public class UnsubscribedPatientService {
    private static UnsubscribedPatientService instance=null;
    private UnsubscribedPatientRepository unsubscribedPatientRepository= new UnsubscribedPatientRepository();
    public static UnsubscribedPatientService getInstance(){
        if(instance==null)
            instance= new UnsubscribedPatientService();
        return instance;
    }
    public void addUnsubscribedPatient(UnsubscribedPatient p){
        unsubscribedPatientRepository.addPatients(p);
    }
}
