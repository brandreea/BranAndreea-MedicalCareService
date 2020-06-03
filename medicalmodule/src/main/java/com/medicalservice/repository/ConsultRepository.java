package com.medicalservice.repository;
import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.consults.Prescription;
import com.medicalservice.model.medication.Medication;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class ConsultRepository {
    private final Connection connection;
    HashMap<Long,Consult> consults=new HashMap<Long,Consult>();
    public ConsultRepository(Connection connection){
        this.connection = connection;
    }

    public List<Consult>getAll() throws SQLException {
        List<Consult> consults = new ArrayList<>();
        String sqlGet = "SELECT * FROM consult";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            Consult consult = new Consult(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));
            consults.add(consult);
        }
        return consults;
    }
    public List<Consult> viewByMedic(long cnp)
    {
        List<Consult> aux= new ArrayList<Consult>();
        consults.forEach((key,value)-> aux.add(value));
        for(int i=0;i<aux.size();i++)
            if(aux.get(i).getDoctor_CNP()!=cnp)
                aux.remove(i);
            System.out.println(aux);
        return aux;
    }
    public void updateConsult(long id, String symptoms, String diagnose, int p)
    {
        Consult aux=consults.get(id);
        Consult c=consults.get(id);
        c.setDiagnose(diagnose);
        c.setSymptoms(symptoms);
        c.setPrescription(p);
        consults.replace(id, aux, c);
    }

    private void getHighestId() throws SQLException {
        String sqlAdd = "SELECT MAX(id_consult) FROM consult";
        PreparedStatement statement = connection.prepareStatement(sqlAdd);
        statement.executeUpdate();
    }
    public void save(Consult consult) throws SQLException {
        String sqlAdd = "INSERT INTO consult VALUES (?, ?, ?,?)";
        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setInt(1,  consult.getDoctor_CNP());
        statement.setInt(2,  consult.getPatient_CNP());
        statement.setString(3,consult.getSymptoms());
        statement.setString(4, consult.getDiagnose());
        statement.executeUpdate();
    }
    public void delete(int id){
        
    }

    public List<Consult> getAllByMedic(int doctorCNP) throws SQLException {
        List<Consult> consults = new ArrayList<>();
        String sqlGet = "SELECT * FROM consult WHERE cnp_doctor = (?)";
        PreparedStatement statement = connection.prepareStatement(sqlGet);
        statement.setInt(1,doctorCNP);
        ResultSet rs = statement.executeQuery();
        while (rs.next())   {
            Consult consult = new Consult(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));
            consults.add(consult);
        }
        return consults;
    }
}
