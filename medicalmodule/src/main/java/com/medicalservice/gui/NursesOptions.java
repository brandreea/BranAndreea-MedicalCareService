package com.medicalservice.gui;

import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.service.MedicationService;
import com.medicalservice.service.NurseService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NursesOptions extends JFrame{
    Connection connection;
    NurseService nurseService;
    public NursesOptions(Connection connection, NurseService nurseService){
        super("Nurses");
        this.connection = connection;
        this.nurseService=nurseService;

        JLabel label = new JLabel("Medication menu");
        JButton create = new JButton("Create Nurse");
        create.setBounds(150, 100, 150, 30);
        create.addActionListener(event-> createForm());
        JButton delete = new JButton("Delete Nurse");
        delete.setBounds(150, 150, 150, 30);
        delete.addActionListener(
                event-> {
                    try {
                        deleteNurse();
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton view = new JButton("View all Nurses");
        view.setBounds(150, 200, 150,30);
        view.addActionListener(event-> {
            try {
                viewAll();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton update = new JButton("Update salary");
        update.setBounds(150, 250, 150,30);
        update.addActionListener(event -> {
            try {
                update();
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
        add(update);
        add(view);
        add(back);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void goBack() throws IOException, SQLException {
        new ManageOptionsFrame(connection);
        setVisible(false);
    }
    private void viewAll() throws SQLException, IOException {
        System.out.println("here");
        List<Nurse> nurses = nurseService.getAll();
        System.out.println("here");
        JOptionPane.showMessageDialog(this,nurses.toString());
    }

    private void createForm() {
        new AddNurseForm(connection, nurseService);
        setVisible(false);
    }
    private void deleteNurse() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide nurse cnp: ");
        System.out.print(cnp);
        nurseService.delete(Integer.parseInt(cnp));
        JOptionPane.showMessageDialog(this, "Deleted successfully!");
    }
    private void update() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide nurse cnp: ");
        String salary= JOptionPane.showInputDialog("Please provide new salary: ");
        nurseService.update(Integer.parseInt(cnp),Integer.parseInt(salary));
        JOptionPane.showMessageDialog(this,"Updated!");
    }
}
