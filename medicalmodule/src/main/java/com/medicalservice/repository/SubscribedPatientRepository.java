package com.medicalservice.repository;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.patients.SubscribedPatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubscribedPatientRepository {
    HashMap<Long, SubscribedPatient> patientsMap=new HashMap<Long,SubscribedPatient>();
    private List<SubscribedPatient> patients;
    private final Connection connection;
    public SubscribedPatientRepository(Connection connection)
    {
        this.connection=connection;
    }

    public void save(SubscribedPatient subscribedPatient) throws SQLException {
        String sqlAdd = "INSERT INTO subscribed_patient VALUES (?, ?, ?,?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1,  subscribedPatient.getName());
        statement.setInt(2,  subscribedPatient.getCNP());
        statement.setInt(3, subscribedPatient.getAge());
        statement.setString(4, subscribedPatient.getPhone_number());
        statement.executeUpdate();
    }
    public void delete(int cnp) throws SQLException {
        String sqlAdd = "DELETE FROM subscribed_patient WHERE cnp = (?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setInt(1,  cnp);
        statement.executeUpdate();
    }
    public List<SubscribedPatient> getAll() throws SQLException {
        List<SubscribedPatient> subscribedPatients = new ArrayList<>();
        String sqlGet = "SELECT * FROM subscribed_patient";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            SubscribedPatient p = new SubscribedPatient(rs.getInt(2),rs.getString(1),rs.getString(4),rs.getInt(3));
            subscribedPatients.add(p);
        }
        return subscribedPatients;
    }
    public void update(int cnp, int age) throws SQLException{
        String sqlGet = "UPDATE subscribed_patient SET age = (?) WHERE cnp=(?);";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        statement.setInt(1,age);
        statement.setInt(2,cnp);
        statement.executeUpdate();
    }
}
