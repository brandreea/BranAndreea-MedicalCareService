package com.company.repository;

import com.company.model.workers.Medic;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
public class MedicRepository {
    private HashMap<Long, Medic> medics=new HashMap<>();
    public void addMedic(String name, String dep, long cnp)
    {
        Medic m=new Medic(name,dep,cnp);
        if(!medics.containsKey(m.getCNP()))
            medics.put(m.getCNP(),m);
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
            if(departmentDep.get(i).getSpeciality()!=dep)
                departmentDep.remove(i);
            return departmentDep;
    }
}
