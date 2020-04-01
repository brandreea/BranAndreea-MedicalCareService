package com.company.repository;

import com.company.model.consults.Procedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcedureRepository {
    HashMap<Long, Procedure> procedures=new HashMap<Long,Procedure>();
    void addProcedure(Procedure p)
    {
        if(!procedures.containsKey(p.getId()))
            procedures.put(p.getId(), p);
    }
    List<Procedure> getAllConsult()
    {
        List<Procedure> aux= new ArrayList<Procedure>();
        procedures.forEach((key,value)-> aux.add(value));
        return aux;
    }
    public List<Long> getProcedureMedics(long id)
    {
        List<Long> medics=new ArrayList<Long>();
        medics=procedures.get(id).getDoctor_CNP();
        return medics;
    }
}
