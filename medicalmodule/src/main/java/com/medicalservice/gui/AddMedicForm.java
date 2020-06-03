package com.medicalservice.gui;

import com.medicalservice.model.workers.Medic;
import com.medicalservice.service.MedicService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class AddMedicForm extends JFrame {
    Connection connection;
    MedicService medicService;
    public AddMedicForm(Connection connection, MedicService medicService){
        super("Add a new medic");
        this.connection=connection;
        this.medicService = medicService;

        JLabel nameLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(0, 50, 200, 30);
        name.setBounds(60, 50, 200, 30);

        JLabel cnpLabel = new JLabel("CNP: " );
        JTextField cnp = new JTextField();
        cnpLabel.setBounds(0, 100, 200, 30);
        cnp.setBounds(60, 100, 200, 30);

        JLabel specialityLabel = new JLabel("Speciality: " );
        JTextField speciality = new JTextField();
        specialityLabel.setBounds(0, 150, 200, 30);
        speciality.setBounds(60, 150, 200, 30);

        JLabel salaryLabel = new JLabel("Salary: " );
        JTextField salary = new JTextField();
        salaryLabel.setBounds(0, 200, 200, 30);
        salary.setBounds(60, 200, 200, 30);
        JButton submit = new JButton("Save");
        submit.setBounds(30, 250, 150,30);
        submit.addActionListener(
                event->
                {
                    try {
                        addMedic(name.getText(),
                                Integer.parseInt(cnp.getText()),
                                speciality.getText(),
                                Integer.parseInt(salary.getText().toString()));
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
        add(specialityLabel);
        add(speciality);
        add(salaryLabel);
        add(salary);
        add(submit);
        add(cancel);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void addMedic(String name, int cnp, String speciality, int salary) throws SQLException, IOException {
        medicService.save(new Medic(salary, new Date(),speciality,name,cnp));
        JOptionPane.showMessageDialog(this,"Added medic succesfully!");
    }

    private void goBack() {
        new MedicOptions("Medic options", connection,medicService);
        setVisible(false);
    }
}
