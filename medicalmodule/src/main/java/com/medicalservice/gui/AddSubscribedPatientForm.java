package com.medicalservice.gui;

import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.service.SubscribedPatientService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddSubscribedPatientForm extends JFrame {
    Connection connection;
    SubscribedPatientService subscribedPatientService;
    public AddSubscribedPatientForm(Connection connection, SubscribedPatientService subscribedPatientService){
        super("Add a subscribed patient");
        this.subscribedPatientService = subscribedPatientService;
        this.connection = connection;

        JLabel nameLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(0, 50, 200, 30);
        name.setBounds(60, 50, 200, 30);

        JLabel cnpLabel = new JLabel("CNP: " );
        JTextField cnp = new JTextField();
        cnpLabel.setBounds(0, 100, 200, 30);
        cnp.setBounds(60, 100, 200, 30);

        JLabel ageLabel = new JLabel("Age: " );
        JTextField age = new JTextField();
        ageLabel.setBounds(0, 150, 200, 30);
        age.setBounds(60, 150, 200, 30);

        JLabel phoneLabel = new JLabel("Phone number: " );
        JTextField phone= new JTextField();
        phoneLabel.setBounds(0, 200, 200, 30);
        phone.setBounds(60, 200, 200, 30);
        JButton submit = new JButton("Save");
        submit.setBounds(30, 250, 150,30);
        submit.addActionListener(
                event->
                {
                    try {
                        addSubscribedPatient(name.getText(),
                                Integer.parseInt(cnp.getText()),
                                Integer.parseInt(age.getText()),
                                phone.getText().toString());
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton cancel = new JButton("Back");
        cancel.setBounds(200, 250, 150,30);
        cancel.addActionListener(event->goBack());

        add(nameLabel);
        add(name);
        add(cnpLabel);
        add(cnp);
        add(ageLabel);
        add(age);
        add(phoneLabel);
        add(phone);
        add(submit);
        add(cancel);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void addSubscribedPatient(String name, int cnp, int age, String phone) throws SQLException, IOException {
        subscribedPatientService.save(new SubscribedPatient(cnp,name,phone,age));
        JOptionPane.showMessageDialog(this,"Patient saved!");
    }
    private void vewAll(){

    }
    private void goBack() {
        new SubscribedPatientsOptions(subscribedPatientService, connection);
        setVisible(false);
    }
}
