package com.medicalservice.service;

import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.consults.Prescription;
import com.medicalservice.model.consults.Procedure;
import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.model.patients.UnsubscribedPatient;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.repository.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service instance=null;
    private ActionsWrite actionWrite;
    private FileUtilsWrite write;
    private FileUtilsRead read;
    private MedicRepository medicRepo = new MedicRepository();
//    private NurseRepository nurseRepo = new NurseRepository();
    private ConsultRepository consultRepo = new ConsultRepository();
    private ProcedureRepository procedureRepo = new ProcedureRepository();
    private SubscribedPatientRepository subPatientRepo = new SubscribedPatientRepository();
    private MedicationRepository medicationRepo= new MedicationRepository();
    private UnsubscribedPatientRepository patientRepository=new UnsubscribedPatientRepository();
    private Service() throws IOException {
        actionWrite=ActionsWrite.getInstance();
        write=FileUtilsWrite.getInstance();
        read=FileUtilsRead.getInstance();

        //retrieving data
        //MEDICS
        List<Medic> retrievedMedics=read.readFile("medics.csv", new Medic("","",123));
        for(int i=0;i<retrievedMedics.size();i++)
            addMedic(retrievedMedics.get(i));
        //NURSES
//        List<Nurse> retrievedNurses=read.readFile("nurses.csv", new Nurse("",0,""));
//        for(int i=0;i<retrievedNurses.size();i++)
//            addNurse(retrievedNurses.get(i));
        //SUBSCRIBED PATIENTS
        List<SubscribedPatient> retrievedPatients=read.readFile("subscribedPatients.csv",new SubscribedPatient(0,"",0,0));
        for(int i=0;i<retrievedPatients.size();i++) {
            SubscribedPatient p=retrievedPatients.get(i);
            addSubscribedPatient(p.getCNP(), p.getName(),p.getPhone_number(),p.getAge());
        }
        //CONSULTS
        List<Consult> retrievedConsults=read.readFile("consults.csv",new Consult(0,0));
        for(int i=0;i<retrievedConsults.size();i++)
            addConsult(retrievedConsults.get(i));
        //MEDICATION
//        List<Medication> retrievedMedication=read.readFile("medication.csv",new Medication("",0,0));
//        for(int i=0;i<retrievedMedication.size();i++)
//             addMedication(retrievedMedication.get(i));

        System.out.println("Service on!");
    }

    public static Service getInstance() throws IOException {
        if(instance==null)
            instance=new Service();
        return instance;
    }

    public void addMedic(String name, String dep, long cnp) throws IOException {
        medicRepo.addMedic(name, dep, cnp);
        List<Medic> medic=medicRepo.getMedics();
        write.writeFile(medic,"medics.csv");
        System.out.println("Added medic " + name);

    }

    //1
    public void addMedic(Medic m) throws IOException {
        medicRepo.addMedic(m);
        System.out.println("Added medic " + m.getName());
        List<Medic> medic=medicRepo.getMedics();
        write.writeFile(medic,"medics.csv");
        actionWrite.writeAction("addMedic", new Timestamp(System.currentTimeMillis()));
    }

    //2
//    public void addNurse(Nurse n) throws IOException {
//        nurseRepo.addNurse(n);
//        System.out.println("Added nurse " + n.getName());
//        List<Nurse> nurses=nurseRepo.getNurses();
//        write.writeFile(nurses,"nurses.csv");
//        actionWrite.writeAction("addNurse", new Timestamp(System.currentTimeMillis()));
//    }

    //3
    public void addConsult(Consult c) throws IOException {
        consultRepo.addConsult(c);
        System.out.println("Added consult with patient "+ subPatientRepo.getPatient(c.getPatient_CNP()).getName()+" to medic "+ medicRepo.getMedic(c.getDoctor_CNP()).getName());
         actionWrite.writeAction("addConsult", new Timestamp(System.currentTimeMillis()));
        List<Consult> con=consultRepo.getAllConsult();
        write.writeFile(con,"consults.csv");
    }

    //4
    public void addSubscribedPatient(long cnp, String name, long phone, int age) throws IOException {
        subPatientRepo.addSubscribedPatient(cnp,name,phone,age);
        System.out.println(name+ " is now subscribed patient");
        List<SubscribedPatient> s=subPatientRepo.getAllSubPatient();
        if(s==null)
            System.out.println("null");
        else
        write.writeFile(s,"subscribedPatients.csv");
        actionWrite.writeAction("addSubscribedPatient", new Timestamp(System.currentTimeMillis()));
    }

    //5
    public void addProcedure(List<Long> medicCNP, String name, long price) throws IOException {
        Procedure p= new Procedure(medicCNP, name, price);
        procedureRepo.addProcedure(p);
        System.out.println("Procedure "+name + " added!");
        actionWrite.writeAction("addProcedure", new Timestamp(System.currentTimeMillis()));
    }

    //6
    public List<Medic> viewMedicsByDepartment(String dep) throws IOException {
        actionWrite.writeAction("viewMedicsbyDep", new Timestamp(System.currentTimeMillis()));
        return medicRepo.viewByDep(dep);
    }

    //7
    public List<Consult>viewConsultsByMedic(long cnp) throws IOException {
        actionWrite.writeAction("viewConsultByMedic", new Timestamp(System.currentTimeMillis()));
        return consultRepo.viewByMedic(cnp);
    }

//    //8
//    public void addMedication(Medication m) throws IOException {
//        medicationRepo.addMedication(m);
//        System.out.println("Added "+ m.getName()+ " to stock!");
//        List<Medication> med=medicationRepo.getAllMedication();
//        write.writeFile(med,"medication.csv");
//        actionWrite.writeAction("addMedicaion", new Timestamp(System.currentTimeMillis()));
//    }

    //9
    public List<Medic> viewMedicProcedure(long id) throws IOException {
        List<Long> m = procedureRepo.getProcedureMedics(id);
        actionWrite.writeAction("viewMedicProcedure", new Timestamp(System.currentTimeMillis()));
        return medicRepo.getMedicsById(m);
    }

    //10
    public void updateConsult(long id,String symptoms, String diagnose, String med, int prescription) throws IOException {

        Consult c=consultRepo.getOneConsult((id));
        consultRepo.updateConsult(id,symptoms, diagnose,prescription);
        List<Consult> con=consultRepo.getAllConsult();
        write.writeFile(con,"consults.csv");
        actionWrite.writeAction("updateConsult", new Timestamp(System.currentTimeMillis()));
    }

    //11
    public List<SubscribedPatient>viewSubscribedPatients() throws IOException {
        actionWrite.writeAction("viewSubscribedPatients", new Timestamp(System.currentTimeMillis()));
        return subPatientRepo.getAllSubPatient();
    }

    //12
    public List<Medication> viewMedication() throws IOException {
        actionWrite.writeAction("viewMedication", new Timestamp(System.currentTimeMillis()));
        return medicationRepo.getAllMedication();
    }

    //13
//    public void addQuantity(String name, int q) throws IOException {
//       int ok= medicationRepo.increaseQuantity(name,q);
//        List<Medication> med=medicationRepo.getAllMedication();
//        write.writeFile(med,"medication.csv");
//        actionWrite.writeAction("increaseMedicaion", new Timestamp(System.currentTimeMillis()));
//    }
//
//    //14
//    public void deleteQuantity(String name, int q) throws IOException {
//        medicationRepo.decreaseQuantity(name,q);
//        List<Medication> med=medicationRepo.getAllMedication();
//        write.writeFile(med,"medication.csv");
//        actionWrite.writeAction("deleteMedicaion", new Timestamp(System.currentTimeMillis()));
//    }

}
