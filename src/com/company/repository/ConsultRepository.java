package com.company.repository;
import com.company.model.consults.Consult;
import com.company.model.consults.Prescription;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class ConsultRepository {
    HashMap<Long,Consult> consults=new HashMap<Long,Consult>();


    public void addConsult(long doctorCNP, long patientCNP)
    {
        Consult c = new Consult(doctorCNP, patientCNP);
        if(!consults.containsKey(c.getId()))
            consults.put(c.getId(), c);
    }
    List<Consult>getAllConsult()
    {
            List<Consult> aux= new ArrayList<Consult>();
            consults.forEach((key,value)-> aux.add(value));
            return aux;
    }
    public List<Consult> viewByMedic(long cnp)
    {
        List<Consult> aux= new ArrayList<Consult>();
        consults.forEach((key,value)-> aux.add(value));
        for(int i=0;i<aux.size();i++)
            if(aux.get(i).getDoctor_CNP()!=cnp)
                aux.remove(i);
        return aux;
    }
    public Consult getOneConsult(long id)
    {
        return consults.get(id);
    }
    public void updateConsult(long id, List<String> symptoms, List<String> diagnose, Prescription p)
    {
        Consult aux=consults.get(id);
        Consult c=consults.get(id);
        c.setDiagnose(diagnose);
        c.setSymptoms(symptoms);
        c.setPrescription(p);
        consults.replace(id, aux, c);
    }
    public void addPrescription()
    {

    }

}
