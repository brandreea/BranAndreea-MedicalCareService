package com.medicalservice.repository;

import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.workers.Medic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
public class MedicRepository {
    private final Connection connection;
    public MedicRepository(Connection connection){
        this.connection=connection;
    }
    private HashMap<Integer, Medic> medics=new HashMap<Integer, Medic>();

    public void save(Medic m) throws SQLException {
        String sqlAdd = "INSERT INTO medic VALUES (?, ?, ?,?,?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1,  m.getName());
        statement.setString(2,  m.getSpeciality());
        statement.setDate(3, new java.sql.Date(m.getHire_date().getTime()));
        statement.setInt(4, m.getSalary());
        statement.setInt(5, m.getCNP());
        statement.executeUpdate();

    }
    public void delete(int cnp) throws SQLException{
        String sqlAdd = "DELETE FROM medic WHERE cnp = (?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setInt(1,cnp);
        statement.executeUpdate();
    }
    public List<Medic> getAll()throws  SQLException{
        List<Medic> medics = new ArrayList<>();
        String sqlGet = "SELECT * FROM medic";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            Medic m = new Medic(rs.getInt(4),rs.getDate(3),rs.getString(2) ,rs.getString(1),rs.getInt(5));
            medics.add(m);
        }
        return medics;
    }
    public List<Medic> getAllByDepartment(String departemnt) throws SQLException{
        List<Medic> medics = new ArrayList<>();
        String sqlGet = "SELECT * FROM medic WHERE speciality LIKE(?)";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        statement.setString(1,departemnt);
        ResultSet rs = statement.executeQuery();

        while (rs.next())   {
            Medic m = new Medic(rs.getInt(4),rs.getDate(3),rs.getString(2) ,rs.getString(1),rs.getInt(5));
            medics.add(m);
        }
        return medics;
    }
    public void update(int cnp, int salary) throws SQLException{
        String sqlGet = "UPDATE medic SET salary = (?) WHERE cnp=(?);";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        statement.setInt(1,salary);
        statement.setInt(2,cnp);
        statement.executeUpdate();
    }
}
