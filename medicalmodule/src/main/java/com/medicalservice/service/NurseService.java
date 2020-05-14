package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.repository.NurseRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class NurseService {
    private static NurseService instance=null;
    private NurseRepository nurseRepository=new NurseRepository();
    private ActionsWrite actionWrite;
    private FileUtilsWrite write;
    private FileUtilsRead read;
    public static NurseService getInstance() throws IOException {
        if(instance==null)
            instance=new NurseService();
        return instance;
    }
    private NurseService() throws IOException {
        actionWrite=ActionsWrite.getInstance();
        write=FileUtilsWrite.getInstance();
        read=FileUtilsRead.getInstance();
        List<Nurse> retrievedNurses=read.readFile("nurses.csv", new Nurse("",0,""));
        for(int i=0;i<retrievedNurses.size();i++)
            addNurse(retrievedNurses.get(i));
    }
    public void addNurse(Nurse n) throws IOException {
        nurseRepository.addNurse(n);
        System.out.println("Added nurse " + n.getName());
        List<Nurse> nurses=nurseRepository.getNurses();
        write.writeFile(nurses,"nurses.csv");
        actionWrite.writeAction("addNurse", new Timestamp(System.currentTimeMillis()));
    }
}
