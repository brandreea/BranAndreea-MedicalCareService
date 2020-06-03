package com.medicalservice.gui;

import com.medicalservice.model.consults.Consult;
import com.medicalservice.service.ConsultService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddConsultForm extends JFrame {
    private Connection connection;
    private ConsultService consultService;
    public AddConsultForm(Connection connection, ConsultService consultService){
        super("Add a consult");
        this.connection = connection;
        this.consultService = consultService;
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        JLabel doctorCNPLabel = new JLabel("Doctor CNP: " );
        JTextField doctorCNP = new JTextField();
        doctorCNPLabel.setBounds(0, 50, 200, 30);
        doctorCNP.setBounds(60, 50, 200, 30);

        JLabel patientCNPLabel = new JLabel("Patient CNP: " );
        JTextField patientCNP = new JTextField();
        patientCNPLabel.setBounds(0, 100, 200, 30);
        patientCNP.setBounds(60, 100, 200, 30);

        JLabel symptomsLabel = new JLabel("Symptoms: " );
        JTextField symptoms = new JTextField();
        symptomsLabel.setBounds(0, 150, 200, 30);
        symptoms.setBounds(60, 150, 200, 30);

        JLabel diagnoseLabel = new JLabel("Diagnose: " );
        JTextField diagnose = new JTextField();
        diagnoseLabel.setBounds(0, 200, 200, 30);
        diagnose.setBounds(60, 200, 200, 30);
        JButton submit = new JButton("Save");
        submit.setBounds(30, 250, 150,30);
        submit.addActionListener(
                event->
                {
                    try {
                        addConsult(Integer.parseInt(doctorCNP.getText()),
                                Integer.parseInt(patientCNP.getText()),
                                symptoms.getText(),
                                diagnose.getText());
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton cancel = new JButton("Back");
        cancel.setBounds(200, 250, 150,30);
        cancel.addActionListener(event->goBack());

        add(doctorCNPLabel);
        add(doctorCNP);
        add(patientCNPLabel);
        add(patientCNP);
        add(symptomsLabel);
        add(symptoms);
        add(diagnoseLabel);
        add(diagnose);
        add(submit);
        add(cancel);
    }
    private void addConsult(int doctorCNP, int patientCNP, String symptoms, String diagnose) throws SQLException, IOException {
        consultService.save(new Consult(doctorCNP,patientCNP,symptoms,diagnose));
        JOptionPane.showMessageDialog(this,"Added consult succesfully!");

    }
    private void goBack() {
        new ConsultOptions(consultService,connection);
        setVisible(false);
    }
}
