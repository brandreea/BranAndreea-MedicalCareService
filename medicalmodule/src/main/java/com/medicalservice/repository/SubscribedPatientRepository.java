package com.medicalservice.repository;
import com.medicalservice.model.patients.SubscribedPatient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubscribedPatientRepository {
    HashMap<Long, SubscribedPatient> patientsMap=new HashMap<Long,SubscribedPatient>();
    private List<SubscribedPatient> patients;

    public SubscribedPatientRepository()
    {
        patients=new ArrayList<SubscribedPatient>();
    }
    public void addSubscribedPatient(Long cnp, String name, long phone, int age)
    {
        SubscribedPatient p=new SubscribedPatient(cnp,name,phone,age);
        patientsMap.put(cnp,p);
        patients.add(p);
    }
    public List<SubscribedPatient> getAllSubPatient()
    {
        return patients;
    }
    public SubscribedPatient getPatient(long cnp)
    {
        if(patientsMap.containsKey(cnp))
            return patientsMap.get(cnp);
        return null;
    }

}
