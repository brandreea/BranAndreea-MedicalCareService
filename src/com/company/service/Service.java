package com.company.service;

import com.company.model.consults.Consult;
import com.company.model.consults.Prescription;
import com.company.model.consults.Procedure;
import com.company.model.medication.Medication;
import com.company.model.patients.SubscribedPatient;
import com.company.model.workers.Medic;
import com.company.repository.*;

import java.util.List;

public class Service {
    private static Service instance=null;
    private MedicRepository medicRepo = new MedicRepository();
    private NurseRepository nurseRepo = new NurseRepository();
    private ConsultRepository consultRepo = new ConsultRepository();
    private ProcedureRepository procedureRepo = new ProcedureRepository();
    private SubscribedPatientRepository subPatientRepo = new SubscribedPatientRepository();
    private MedicationRepository medicationRepo= new MedicationRepository();
    private Service()
    {
        System.out.println("Service on!");
    }

    public static Service getInstance() {
        if(instance==null)
            instance=new Service();
        return instance;
    }

    public void addMedic(String name, String dep, long cnp)
    {
        medicRepo.addMedic(name, dep, cnp);
        System.out.println("Added medic " + name);
    }
    public void addNurse(String name, long cnp, String department)
    {
        nurseRepo.addNurse(name, cnp, department);
        System.out.println("Added nurse " + name);
    }

    public void addConsult(long medicCNP, long patientCNP)
    {
        consultRepo.addConsult(medicCNP,patientCNP);
        System.out.println("Added consult with patient "+ patientCNP+" to medic "+ medicRepo.getMedic(medicCNP).getName());
    }
    public void addSubscribedPatient(long cnp, String name, long phone, int age)
    {
        subPatientRepo.addSubscribedPatient(cnp,name,phone,age);
        System.out.println(name+ " is now subscribed patient");
    }
    public void addProcedure(List<Long> medicCNP, String name, long price)
    {
        Procedure p= new Procedure(medicCNP, name, price);
        procedureRepo.addProcedure(p);
        System.out.println("Procedure "+name + " added!");
    }
    public List<Medic> viewMedicsByDepartment(String dep)
    {
        return medicRepo.viewByDep(dep);
    }
    public List<Consult>viewConsultsByMedic(long cnp){
        return consultRepo.viewByMedic(cnp);
    }
    public void addMedication(String name, int q, int p)
    {
        medicationRepo.addMedication(new Medication(name,q,p));
        System.out.println("Added "+ name+ " to stock!");
    }
    public List<Medic> viewMedicProcedure(long id)
    {
        List<Long> m = procedureRepo.getProcedureMedics(id);
        return medicRepo.getMedicsById(m);
    }
    public void updateConsult(long id, List<String> symptoms, List<String> diagnose, String med, String rec, int q, int days, List<String> moments)
    {
        Prescription p=new Prescription(med,rec, q, days,moments );
        Consult c=consultRepo.getOneConsult((id));
        consultRepo.updateConsult(id,symptoms, diagnose, p);
    }
    public List<SubscribedPatient>viewSubscribedPatients()
    {
        return subPatientRepo.getAllSubPatient();
    }
    public List<Medication> viewMedication()
    {
        return medicationRepo.getAllMedication();
    }
    public void addQuantity(String name, int q)
    {
       int ok= medicationRepo.increaseQuantity(name,q);
        if(ok<0) System.out.println("Lacking "+ (-1)*ok+"elements of " + name);
    }
    public void deleteQuantity(String name, int q)
    {
        medicationRepo.decreaseQuantity(name,q);
    }

}
