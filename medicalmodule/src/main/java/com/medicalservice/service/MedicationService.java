package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.repository.MedicationRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class MedicationService {
    private MedicationRepository medicationRepo= new MedicationRepository();
    private ActionsWrite actionWrite;
    private FileUtilsWrite write;
    private FileUtilsRead read;
    private static MedicationService instance;

    private MedicationService() throws IOException {
        actionWrite=ActionsWrite.getInstance();
        write=FileUtilsWrite.getInstance();
        read=FileUtilsRead.getInstance();
        List<Medication> retrievedMedication=read.readFile("medication.csv",new Medication("",0,0));
        for(int i=0;i<retrievedMedication.size();i++)
            addMedication(retrievedMedication.get(i));
    }
    public static MedicationService getInstance() throws IOException {
        if(instance==null)
            instance=new MedicationService();
        return instance;
    }
    public void addMedication(Medication m) throws IOException {
        medicationRepo.addMedication(m);
        System.out.println("Added "+ m.getName()+ " to stock!");
        List<Medication> med=medicationRepo.getAllMedication();
        write.writeFile(med,"medication.csv");
        actionWrite.writeAction("addMedicaion", new Timestamp(System.currentTimeMillis()));
    }
    public void addQuantity(String name, int q) throws IOException {
        int ok= medicationRepo.increaseQuantity(name,q);
        List<Medication> med=medicationRepo.getAllMedication();
        write.writeFile(med,"medication.csv");
        actionWrite.writeAction("increaseMedicaion", new Timestamp(System.currentTimeMillis()));
    }

    //14
    public void deleteQuantity(String name, int q) throws IOException {
        medicationRepo.decreaseQuantity(name,q);
        List<Medication> med=medicationRepo.getAllMedication();
        write.writeFile(med,"medication.csv");
        actionWrite.writeAction("deleteMedicaion", new Timestamp(System.currentTimeMillis()));
    }

}
