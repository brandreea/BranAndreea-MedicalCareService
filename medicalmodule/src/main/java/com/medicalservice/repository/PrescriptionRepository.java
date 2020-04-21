package com.medicalservice.repository;

import com.medicalservice.model.consults.Prescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrescriptionRepository {
    List<Prescription> prescriptions=new ArrayList<Prescription>();
    HashMap<Long, Prescription> prescriptionMap=new HashMap<Long,Prescription>();
    void addPrescription(Prescription p)
    {
        prescriptions.add(p);

    }
    List<Prescription> getAllConsult()
    {
        return prescriptions;
    }

}
