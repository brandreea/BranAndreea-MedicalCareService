package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.repository.NurseRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class NurseService {
    private static NurseService instance=null;
    private final Connection connection;
    private NurseRepository nurseRepository;
    private ActionsWrite actionWrite=ActionsWrite.getInstance();
    private FileUtilsWrite write=FileUtilsWrite.getInstance();
    private FileUtilsRead read=FileUtilsRead.getInstance();
    private final String audit="";
    public static NurseService getInstance(Connection connection) throws IOException {
        if(instance==null)
            instance=new NurseService(connection);
        return instance;
    }
    private NurseService(Connection connection) throws IOException {
        actionWrite=ActionsWrite.getInstance();
        write=FileUtilsWrite.getInstance();
        read=FileUtilsRead.getInstance();
        nurseRepository=new NurseRepository(connection);
        this.connection=connection;
    }
    public void save(Nurse n) throws SQLException, IOException {
        nurseRepository.save(n);
        actionWrite.writeAction("addNurse", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
//
    }
    public void delete(int cnp) throws SQLException, IOException {
        nurseRepository.delete(cnp);
        actionWrite.writeAction("deleteNurse", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public List<Nurse> getAll() throws SQLException, IOException {
        List<Nurse> nurses = nurseRepository.getAll();
        actionWrite.writeAction("getAllNurses", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return nurses;
    }

    public void update(int cnp, int salary) throws SQLException, IOException {
        nurseRepository.update(cnp,salary);
        actionWrite.writeAction("updateNurse", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
}
