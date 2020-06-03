package com.medicalservice.service;

import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.repository.MedicRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MedicService {
    private static MedicService instance = null;
    private MedicRepository medicRepository;
    private final Connection connection;
    private ActionsWrite actionWrite=ActionsWrite.getInstance();
    private MedicService(Connection connection){
        medicRepository = new MedicRepository(connection);
        this.connection=connection;
    }
    public static MedicService getInstance(Connection connection){
        if(instance == null)
            instance = new MedicService(connection);
        return instance;
    }
    public void save(Medic medic) throws SQLException, IOException {
        medicRepository.save(medic);
        actionWrite.writeAction("addMedic", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public void delete(int cnp) throws SQLException, IOException {
        medicRepository.delete(cnp);
        actionWrite.writeAction("deleteMedic", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public List<Medic> getAll() throws SQLException, IOException {
        actionWrite.writeAction("getAllMedics", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return medicRepository.getAll();
    }
    public List<Medic> getAllByDepartment(String department) throws SQLException, IOException {
        actionWrite.writeAction("getAllMedicsByDepartment", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return medicRepository.getAllByDepartment(department);
    }
    public void update(int cnp, int salary) throws SQLException, IOException {
        medicRepository.update(cnp,salary);
        actionWrite.writeAction("updateMedic", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
}
