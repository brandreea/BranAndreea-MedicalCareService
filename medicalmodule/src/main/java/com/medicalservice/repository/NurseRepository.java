package com.medicalservice.repository;

import com.medicalservice.model.workers.Nurse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
public class NurseRepository {
    private final Connection connection;
    private HashMap<Long, Nurse> nurses=new HashMap<>();
    public NurseRepository(Connection connection){
        this.connection=connection;
    }
    public void removeNurse(long cnp)
    {
        if(nurses.containsKey(cnp))
            nurses.remove(cnp);
    }

    public List<Nurse> getNurses()
    {
        List<Nurse> aux= new ArrayList<Nurse>();
        nurses.forEach((key,value)-> aux.add(value));
        return aux;
    }

    public void save(Nurse n) throws SQLException {
        String sqlAdd = "INSERT INTO nurse VALUES (?, ?, ?,?,?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1,  n.getName());
        statement.setDate(2, new java.sql.Date(n.getHire_date().getTime()));
        statement.setInt(3,n.getCNP());
        statement.setInt(4,n.getSalary());
        statement.setString(5,n.getDepartment());
        statement.executeUpdate();

    }

    public void delete(int cnp) throws SQLException {
        String sqlAdd = "DELETE FROM nurse WHERE CNP=(?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setInt(1,  cnp);
        statement.executeUpdate();
    }

    public List<Nurse> getAll() throws SQLException {
        List<Nurse>nurses= new ArrayList<>();
        String sqlGet = "SELECT * FROM nurse";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            Nurse n = new Nurse(rs.getString(1),new java.util.Date(rs.getDate(2).getTime()), rs.getInt(3), rs.getInt(4),rs.getString(5));
            nurses.add(n);
        }
        return nurses;
    }
    public void update(int cnp, int salary) throws SQLException{
        String sqlGet = "UPDATE nurse SET salary = (?) WHERE cnp=(?);";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        statement.setInt(1,salary);
        statement.setInt(2,cnp);
        statement.executeUpdate();
    }
}
