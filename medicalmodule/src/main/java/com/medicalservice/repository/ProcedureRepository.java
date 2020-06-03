package com.medicalservice.repository;

import com.medicalservice.model.consults.Procedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcedureRepository {
    HashMap<Long, Procedure> procedures=new HashMap<Long,Procedure>();
    public void addProcedure(Procedure p)
    {
        if(!procedures.containsKey(p.getId()))
            procedures.put(p.getId(), p);
    }
    public List<Procedure> getAllConsult()
    {
        List<Procedure> aux= new ArrayList<Procedure>();
        procedures.forEach((key,value)-> aux.add(value));
        return aux;
    }
    public List<Integer> getProcedureMedics(long id)
    {
        List<Integer> medics=new ArrayList<>();

        medics=procedures.get(id).getDoctor_CNP();
        return medics;
    }
}
