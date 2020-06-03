package com.medicalservice.service;

import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.filemanagement.ActionsWrite;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.repository.ConsultRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ConsultService {
    private final Connection connection;
    private ConsultRepository consultRepository;
    private static ConsultService instance = null;
    private ActionsWrite actionWrite = ActionsWrite.getInstance();

    private ConsultService(Connection connection) throws IOException {
        this.connection = connection;
        consultRepository = new ConsultRepository(connection);

    }
    public static ConsultService getInstance(Connection connection) throws IOException {
        if(instance==null)
            instance = new ConsultService(connection);
        return instance;
    }
    public void save(Consult consult) throws SQLException, IOException {
        consultRepository.save(consult);
        actionWrite.writeAction("saveConsult", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }
    public void delete(int id) throws IOException {
        consultRepository.delete(id);
        actionWrite.writeAction("deleteConsult", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
    }

    public List<Consult> getAll() throws SQLException, IOException {
        actionWrite.writeAction("getAllConsults", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return consultRepository.getAll();

    }

    public List<Consult> getAllByMedic(int doctorCNP) throws SQLException, IOException {
        actionWrite.writeAction("getAllConsultsByMedic", new Timestamp(System.currentTimeMillis()),Thread.currentThread().getName());
        return consultRepository.getAllByMedic(doctorCNP);
    }
}
