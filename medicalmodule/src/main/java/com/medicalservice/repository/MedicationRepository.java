package com.medicalservice.repository;

import com.medicalservice.model.medication.Medication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicationRepository {
    HashMap<String, Medication> medicationMap=new HashMap<String,Medication>();
   // List<Medication> medication =new ArrayList<Medication>();
    public void addMedication(Medication m)
    {
       // medication.add(m);
        medicationMap.put(m.getName(), m);
    }
    public Medication getMedicationByName(String name)
    {
        return medicationMap.get(name);
    }
    public List<Medication>getAllMedication()
    {
        List<Medication> medication=new ArrayList<Medication>();
        medicationMap.forEach((key,value)->medication.add(value));
        return medication;
    }
    public int decreaseQuantity(String name, int q)
    {
        int oldQ=medicationMap.get(name).getQuantity();
        if(oldQ==0) return -1;
        if(oldQ<q)  return (oldQ-q);
        Medication m= medicationMap.get(name);
        Medication mNew= medicationMap.get(name);
        mNew.setQuantity(oldQ-q);
        medicationMap.replace(name, m, mNew);
        return 1;
    }
    public int increaseQuantity(String name, int q)
    {
        int oldQ=medicationMap.get(name).getQuantity();
        Medication m= medicationMap.get(name);
        Medication mNew= medicationMap.get(name);
        mNew.setQuantity(oldQ+q);
        medicationMap.replace(name, m, mNew);
        return 1;
    }
}
