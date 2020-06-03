package com.medicalservice.service;

import com.medicalservice.model.consults.Prescription;
import com.medicalservice.repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService {
    private static PrescriptionService instance =null;
    public static PrescriptionService getInstance(){
        if(instance ==null)
            instance = new PrescriptionService();
        return instance;
    }
    PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    void addPrescription(Prescription p){
        prescriptionRepository.addPrescription(p);
    }
    List<Prescription> getAll(){
       return prescriptionRepository.getAllPrescriptions();
    }
}
