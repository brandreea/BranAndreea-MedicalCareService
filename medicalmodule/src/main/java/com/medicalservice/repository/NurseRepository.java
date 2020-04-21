package com.medicalservice.repository;

import com.medicalservice.model.workers.Nurse;

import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
public class NurseRepository {

    private HashMap<Long, Nurse> nurses=new HashMap<>();

    public void addNurse(String name, long cnp, String dep)
    {
        Nurse n=new Nurse(name, cnp, dep);
        if(!nurses.containsKey(n.getCNP()))
           nurses.put(n.getCNP(),n);
    }

    public void removeNurse(long cnp)
    {
        if(nurses.containsKey(cnp))
            nurses.remove(cnp);
    }

    public List<Nurse> getNurses()
    {
        List<Nurse> aux= new ArrayList<Nurse>();
        nurses.forEach((key,value)-> aux.add(value));
        return aux;
    }
}
