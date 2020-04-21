package com.medicalservice.repository;

import com.medicalservice.model.workers.Medic;

import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
public class MedicRepository {
    private HashMap<Long, Medic> medics=new HashMap<Long, Medic>();
    public void addMedic(String name, String dep, long cnp)
    {
        Medic m=new Medic(name,dep,cnp);
        if(!medics.containsKey(m.getCNP()))
            medics.put(m.getCNP(),m);
    }
    public Medic getMedic(long cnp)
    {
        return medics.get(cnp);
    }
    public void removeMedic(long cnp)
    {
        if(medics.containsKey(cnp))
            medics.remove(cnp);
    }
    public List<Medic> getMedics()
    {
        List<Medic> aux= new ArrayList<Medic>();
        medics.forEach((key,value)-> aux.add(value));
        return aux;
    }
    public List<Medic> getMedicsById(List<Long> cnp)
    {
        List<Medic> aux=new ArrayList<Medic>();
        for(Long CNP:cnp)
            aux.add(medics.get(CNP));
        return aux;
    }
    public List<Medic> viewByDep(String dep)
    {
        List<Medic> departmentDep=new ArrayList<Medic>();
        medics.forEach((key,value)-> departmentDep.add(value));
        for(int i=0;i<departmentDep.size();i++)
        {
            String s=departmentDep.get(i).getSpeciality();

            if(!s.equals(dep))
                departmentDep.remove(i);}
            return departmentDep;
    }
}
