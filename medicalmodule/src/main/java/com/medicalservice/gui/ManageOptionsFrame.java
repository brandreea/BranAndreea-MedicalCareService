package com.medicalservice.gui;

import com.medicalservice.db.DbConnectionUtils;
import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.repository.NurseRepository;
import com.medicalservice.service.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ManageOptionsFrame extends JFrame {
    private MedicService medicService;
    private MedicationService medicationService;
    private NurseService nurseService;
    private SubscribedPatientService subscribedPatientService;
    private ConsultService consultService;
    private Connection connection;
    public ManageOptionsFrame(Connection connection) throws IOException, SQLException {
        super("Medical Service Options");
        this.connection = DbConnectionUtils.getDBConnection();
        this.medicationService =MedicationService.getInstance(connection);
        this.medicService =MedicService.getInstance(connection);
        this.nurseService = NurseService.getInstance(connection);
        this.subscribedPatientService = SubscribedPatientService.getInstance(connection);
        this.consultService = ConsultService.getInstance(connection);
        JButton medics = new JButton("Medics");
        medics.setBounds(150, 100, 150, 30);
        medics.addActionListener(event -> changeFrameMedic("medic",this.connection, this.medicService));
//        medics.addAncestorListener();
        JButton subscibedPatients = new JButton("Subscribed patients");
        subscibedPatients.setBounds(150, 150, 150,30);
        subscibedPatients.addActionListener(event -> changeFrameSubPatients(connection, this.subscribedPatientService));
        JButton consults = new JButton("Consults");
        consults.setBounds(150, 200, 150,30);
        consults.addActionListener(event -> changeFrameConsults(connection, consultService));
        JButton nurses = new JButton("Nurses");
        nurses.setBounds(150, 250, 150,30);
        nurses.addActionListener(event -> changeFrameNurse(this.connection, nurseService));
        JButton medication = new JButton("Medication");
        medication.setBounds(150, 300, 150,30);
        medication.addActionListener(event-> changeFrameMedication("Medication",this.connection,medicationService));

        add(subscibedPatients);
        add(nurses);
        add(medication);
        add(consults);
        add(medics);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void changeFrameConsults(Connection connection, ConsultService consultService) {
        new ConsultOptions(consultService,connection);
        setVisible(false);
    }

    private void changeFrameSubPatients(Connection connection, SubscribedPatientService subscribedPatientService) {
        new SubscribedPatientsOptions(subscribedPatientService, connection);
        setVisible(false);
    }

    private void changeFrameMedic(String medic, Connection connection, MedicService medicService) {
        new MedicOptions(medic,connection, medicService);
        setVisible(false);
    }
    private void changeFrameMedication(String medication, Connection connection, MedicationService medicationService) {
        new MedicationOptions(connection, medicationService);
        setVisible(false);
    }
    private void changeFrameNurse(Connection connection, NurseService nurseService) {
        new NursesOptions(connection, nurseService);
        setVisible(false);
    }

}
