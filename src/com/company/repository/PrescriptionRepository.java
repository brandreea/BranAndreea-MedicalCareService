package com.company.repository;

import com.company.model.consults.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {
    List<Prescription> prescriptions=new ArrayList<Prescription>();
    void addProcedure(Prescription p)
    {
        prescriptions.add(p);

    }
    List<Prescription> getAllConsult()
    {
        return prescriptions;
    }
}
