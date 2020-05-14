package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.repository.MedicRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class MedicService {
    private MedicRepository medicRepo = new MedicRepository();
    private ActionsWrite actionWrite;
    private FileUtilsWrite write;
    private FileUtilsRead read;
    private static MedicService instance=null;
    private MedicService() throws IOException {
        actionWrite=ActionsWrite.getInstance();
        write=FileUtilsWrite.getInstance();
        read=FileUtilsRead.getInstance();
        List<Medic> retrievedMedics=read.readFile("medics.csv", new Medic("","",123));
        for(int i=0;i<retrievedMedics.size();i++)
            addMedic(retrievedMedics.get(i));
    }
    public static MedicService getInstance() throws IOException {
        if(instance==null)
            instance=new MedicService();
        return instance;
    }
    public void addMedic(Medic m) throws IOException {
        medicRepo.addMedic(m);
        System.out.println("Added medic " + m.getName());
        List<Medic> medic=medicRepo.getMedics();
        write.writeFile(medic,"medics.csv");
        actionWrite.writeAction("addMedic", new Timestamp(System.currentTimeMillis()));
    }
    public List<Medic> viewMedicsByDepartment(String dep) throws IOException {
        actionWrite.writeAction("viewMedicsbyDep", new Timestamp(System.currentTimeMillis()));
        return medicRepo.viewByDep(dep);
    }

}
