package com.medicalservice.repository;

import com.medicalservice.model.medication.Medication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicationRepository {
    private final Connection connection;
    public MedicationRepository(Connection connection){
        this.connection=connection;
    }

    public List<Medication> getAll() throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sqlGet = "SELECT * FROM medication";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            Medication m = new Medication(rs.getString(1),rs.getInt(2), rs.getInt(3));
            medications.add(m);
        }
        return medications;
    }

    public void save(Medication m) throws SQLException {
       // medication.add(m);
        String sqlAdd = "INSERT INTO medication VALUES (?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1,  m.getName());
        statement.setInt(2,  m.getQuantity());
        statement.setInt(3,m.getPrice());
        statement.executeUpdate();
    }


    public void delete(String name) throws SQLException {
        String sqlAdd = "DELETE FROM medication WHERE name LIKE(?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1,  name);
        statement.executeUpdate();
    }
//    private int getQuantity(String name) throws SQLException {
//        String sqlAdd = "SELECT quantity FROM medication WHERE name LIKE(?)";
//        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
//        statement.setString(1,  name);
//        ResultSet rs = statement.executeQuery();
//        return rs.next().(1);
//
//    }
    public void update(String name, int quantity) throws SQLException {
            String sqlGet = "UPDATE medication SET quantity = (?) WHERE name LIKE(?);";
            PreparedStatement statement = connection.prepareStatement(sqlGet);
            statement.setString(2,name);
            statement.setInt(1, quantity);
            statement.executeUpdate();
    }
}
