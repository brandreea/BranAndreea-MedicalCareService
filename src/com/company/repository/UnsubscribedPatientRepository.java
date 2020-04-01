package com.company.repository;

import com.company.model.patients.UnsubscribedPatient;

import java.util.ArrayList;
import java.util.List;

public class UnsubscribedPatientRepository {
    private List<UnsubscribedPatient> patients=new ArrayList<UnsubscribedPatient>();

    public void addPatients(UnsubscribedPatient p)
    {
        patients.add(p);
    }


}
