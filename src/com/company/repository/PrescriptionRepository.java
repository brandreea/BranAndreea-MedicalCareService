package com.company.repository;

import com.company.model.consults.Prescription;
import com.company.model.patients.SubscribedPatient;

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
