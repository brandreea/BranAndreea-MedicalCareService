package com.medicalservice.service;

import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.consults.Prescription;
import com.medicalservice.model.consults.Procedure;
import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.model.patients.UnsubscribedPatient;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.repository.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class Service {
    private static Service instance=null;
    private ActionsWrite actionWrite;
    private MedicRepository medicRepo = new MedicRepository();
    private NurseRepository nurseRepo = new NurseRepository();
    private ConsultRepository consultRepo = new ConsultRepository();
    private ProcedureRepository procedureRepo = new ProcedureRepository();
    private SubscribedPatientRepository subPatientRepo = new SubscribedPatientRepository();
    private MedicationRepository medicationRepo= new MedicationRepository();
    private UnsubscribedPatientRepository patientRepository=new UnsubscribedPatientRepository();
    private Service()
    {
        actionWrite=ActionsWrite.getInstance();
        System.out.println("Service on!");
    }

    public static Service getInstance() {
        if(instance==null)
            instance=new Service();
        return instance;
    }

    public void addMedic(String name, String dep, long cnp) throws IOException {
        medicRepo.addMedic(name, dep, cnp);
        System.out.println("Added medic " + name);

    }
    public void addMedic(Medic m) throws IOException {
        medicRepo.addMedic(m);
        System.out.println("Added medic " + m.getName());
        actionWrite.writeAction("addMedic", new Timestamp(System.currentTimeMillis()));
    }
    public void addNurse(Nurse n) throws IOException {
        nurseRepo.addNurse(n);
        System.out.println("Added nurse " + n.getName());
        actionWrite.writeAction("addNurse", new Timestamp(System.currentTimeMillis()));
    }

    public void addConsult(Consult c) throws IOException {
        consultRepo.addConsult(c);
        System.out.println("Added consult with patient "+ subPatientRepo.getPatient(c.getPatient_CNP()).getName()+" to medic "+ medicRepo.getMedic(c.getDoctor_CNP()).getName());
        actionWrite.writeAction("addConsult", new Timestamp(System.currentTimeMillis()));
    }
    public void addSubscribedPatient(long cnp, String name, long phone, int age) throws IOException {
        subPatientRepo.addSubscribedPatient(cnp,name,phone,age);
        System.out.println(name+ " is now subscribed patient");
        actionWrite.writeAction("addSubscribedPatient", new Timestamp(System.currentTimeMillis()));
    }
    public void addProcedure(List<Long> medicCNP, String name, long price) throws IOException {
        Procedure p= new Procedure(medicCNP, name, price);
        procedureRepo.addProcedure(p);
        System.out.println("Procedure "+name + " added!");
        actionWrite.writeAction("addProcedure", new Timestamp(System.currentTimeMillis()));
    }
    public List<Medic> viewMedicsByDepartment(String dep) throws IOException {
        actionWrite.writeAction("viewMedicsbyDep", new Timestamp(System.currentTimeMillis()));
        return medicRepo.viewByDep(dep);
    }
    public List<Consult>viewConsultsByMedic(long cnp) throws IOException {
        actionWrite.writeAction("viewConsultByMedic", new Timestamp(System.currentTimeMillis()));
        return consultRepo.viewByMedic(cnp);
    }
    public void addMedication(Medication m) throws IOException {
        medicationRepo.addMedication(m);
        System.out.println("Added "+ m.getName()+ " to stock!");
        actionWrite.writeAction("addMedicaion", new Timestamp(System.currentTimeMillis()));
    }
    public List<Medic> viewMedicProcedure(long id) throws IOException {
        List<Long> m = procedureRepo.getProcedureMedics(id);
        actionWrite.writeAction("addMedicProcedure", new Timestamp(System.currentTimeMillis()));
        return medicRepo.getMedicsById(m);
    }
    public void updateConsult(long id,String symptoms, String diagnose, String med, int prescription) throws IOException {

        Consult c=consultRepo.getOneConsult((id));
        consultRepo.updateConsult(id,symptoms, diagnose,prescription);
        actionWrite.writeAction("updateConsult", new Timestamp(System.currentTimeMillis()));
    }
    public List<SubscribedPatient>viewSubscribedPatients() throws IOException {
        actionWrite.writeAction("viewSubscribedPatients", new Timestamp(System.currentTimeMillis()));
        return subPatientRepo.getAllSubPatient();
    }
    public List<Medication> viewMedication() throws IOException {
        actionWrite.writeAction("viewMedication", new Timestamp(System.currentTimeMillis()));
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
