package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.repository.MedicationRepository;
import com.medicalservice.repository.SubscribedPatientRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubscribedPatientService {
    private static SubscribedPatientService instance=null;
    private SubscribedPatientRepository subPatientRepo;
    private final Connection connection;
    private ActionsWrite actionWrite=ActionsWrite.getInstance();
    private SubscribedPatientService(Connection connection) throws IOException, SQLException {
        this.connection = connection;
        subPatientRepo = new SubscribedPatientRepository(connection);
//        actionWrite.writeAction("addProcedure", new Timestamp(System.currentTimeMillis()));
    }
    public static SubscribedPatientService getInstance(Connection connection) throws IOException, SQLException {
        if(instance==null)
            instance=new SubscribedPatientService(connection);
        return instance;
    }
    public void save(SubscribedPatient subscribedPatient) throws SQLException, IOException {
        subPatientRepo.save(subscribedPatient);
        actionWrite.writeAction("addPatient", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public void delete(int cnp) throws SQLException, IOException {
        subPatientRepo.delete(cnp);
        actionWrite.writeAction("deletePatient", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public List<SubscribedPatient> getAll() throws SQLException, IOException {
        List<SubscribedPatient> patientList=subPatientRepo.getAll();
        actionWrite.writeAction("getAllPatients", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return patientList;
    }
    public void update(int cnp, int age) throws SQLException, IOException {
        subPatientRepo.update(cnp,age);
        actionWrite.writeAction("updatePatients", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
}

