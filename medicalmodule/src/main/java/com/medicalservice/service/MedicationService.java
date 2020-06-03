package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.repository.MedicationRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MedicationService {
    private MedicationRepository medicationRepo;
    private Connection connection;
    private ActionsWrite actionWrite=ActionsWrite.getInstance();
    private FileUtilsWrite write;
    private FileUtilsRead read;
    private static MedicationService instance;

    private MedicationService(Connection connection) throws IOException, SQLException {
        this.connection = connection;
        medicationRepo = new MedicationRepository(connection);
    }
    public static MedicationService getInstance(Connection connection) throws IOException, SQLException {
        if(instance==null)
            instance=new MedicationService(connection);
        return instance;
    }
    public void save(Medication m) throws IOException, SQLException {
        medicationRepo.save(m);
        System.out.println("Added "+ m.getName()+ " to stock!");
        actionWrite.writeAction("saveMedication", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public List<Medication> getAll() throws SQLException, IOException {
        actionWrite.writeAction("getAllMedication", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return medicationRepo.getAll();
    }

    public void delete(String name) throws SQLException, IOException {
        medicationRepo.delete(name);
        actionWrite.writeAction("deleteMedication", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public void update(String name, int quantity) throws SQLException, IOException {
        medicationRepo.update(name,quantity);
        actionWrite.writeAction("updateMedication", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }

}
