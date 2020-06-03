package com.medicalservice.gui;

import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.service.MedicService;
import com.medicalservice.service.MedicationService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedicationOptions extends JFrame{
    Connection connection;
    MedicationService medicationService;
    public MedicationOptions(Connection connection, MedicationService medicationService){
        this.connection = connection;
        this.medicationService=medicationService;
        JLabel label = new JLabel("Medication menu");
        JButton create = new JButton("Create medication");
        create.setBounds(150, 100, 150, 30);
        create.addActionListener(event-> createForm());
        JButton delete = new JButton("Delete medication");
        delete.setBounds(150, 150, 150, 30);
        delete.addActionListener(
                event-> {
                    try {
                        deleteMedication();
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton update = new JButton("Update qunatity");
        update.setBounds(150, 200, 150, 30);
        update.addActionListener(
                event-> {
                    try {
                        updateMedication();
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton view = new JButton("View all medication");
        view.setBounds(150, 250, 150,30);
        view.addActionListener(event-> {
            try {
                viewAll();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton back = new JButton("Back");
        back.setBounds(150,300,150,30);
        back.addActionListener(event-> {
            try {
                goBack();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        add(label);
        add(create);
        add(delete);
        add(view);
        add(back);
        add(update);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void updateMedication() throws SQLException, IOException {
        String name= JOptionPane.showInputDialog("Please provide medication name: ");
        String quantity= JOptionPane.showInputDialog("Please provide quantity: ");
        medicationService.update(name,Integer.parseInt(quantity));
        JOptionPane.showMessageDialog(this,"Updated!");
    }

    private void deleteMedication() throws SQLException, IOException {
        String name= JOptionPane.showInputDialog("Please provide medication name: ");
        medicationService.delete(name);
        JOptionPane.showMessageDialog(this, "Deleted successfully!");
    }

    private void goBack() throws IOException, SQLException {
        new ManageOptionsFrame(connection);
        setVisible(false);
    }

    private void viewAll() throws SQLException, IOException {
        List<Medication> medication = medicationService.getAll();
        JOptionPane.showMessageDialog(this,medication.toString());
    }

    private void createForm() {
        new AddMedicationForm(connection,medicationService);
        setVisible(false);
    }
}
