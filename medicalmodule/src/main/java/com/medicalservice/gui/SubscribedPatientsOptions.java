package com.medicalservice.gui;

import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.service.NurseService;
import com.medicalservice.service.SubscribedPatientService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SubscribedPatientsOptions extends JFrame{
    Connection connection;
    SubscribedPatientService subscribedPatientService;

    public SubscribedPatientsOptions(SubscribedPatientService subscribedPatientService, Connection connection) {
        this.connection = connection;
        this.subscribedPatientService = subscribedPatientService;
        JButton create = new JButton("Create patient");
        create.setBounds(150, 100, 150, 30);
        create.addActionListener(event-> createForm());
        JButton delete = new JButton("Delete patient");
        delete.setBounds(150, 150, 150, 30);
        delete.addActionListener(
                event-> {
                    try {
                        deleteSubscribedPatient();
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton view = new JButton("View all patients");
        view.setBounds(150, 200, 150,30);
        view.addActionListener(event-> {
            try {
                viewAll();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton update = new JButton("Update age");
        update.setBounds(150, 250, 150,30);
        update.addActionListener(event -> {
            try {
                updatePatientAge();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton back = new JButton("Back");
        back.setBounds(150, 300, 150,30);
        back.addActionListener(event-> {
            try {
                goBack();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        add(create);
        add(delete);
        add(view);
        add(update);
        add(back);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void viewAll() throws SQLException, IOException {
        JOptionPane.showMessageDialog(this,subscribedPatientService.getAll().toString());
    }

    private void goBack() throws IOException, SQLException {
        new ManageOptionsFrame(connection);
        setVisible(false);

    }

    private void createForm() {
        new AddSubscribedPatientForm(connection, subscribedPatientService);
        setVisible(false);
    }
    private void deleteSubscribedPatient() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide patient cnp: ");
        subscribedPatientService.delete(Integer.parseInt(cnp));
        JOptionPane.showMessageDialog(this, "Deleted successfully!");
    }
    private void updatePatientAge() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide patient cnp: ");
        String age= JOptionPane.showInputDialog("Please provide patient age: ");
        subscribedPatientService.update(Integer.parseInt(cnp), Integer.parseInt(age));
        JOptionPane.showMessageDialog(this,"Updated!");
    }
}
