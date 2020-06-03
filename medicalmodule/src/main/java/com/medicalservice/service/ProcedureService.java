package com.medicalservice.service;

import com.medicalservice.model.consults.Procedure;
import com.medicalservice.repository.ProcedureRepository;

public class ProcedureService {
    private static ProcedureService instance=null;
    private ProcedureRepository procedureRepository=new ProcedureRepository();
    public static ProcedureService getInstance(){
        if(instance ==null)
            instance = new ProcedureService();
        return instance;
    }
    void addProcedure(Procedure p){
        procedureRepository.addProcedure(p);
    }
}
