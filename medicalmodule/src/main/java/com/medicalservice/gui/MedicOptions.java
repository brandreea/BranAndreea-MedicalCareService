package com.medicalservice.gui;


import com.medicalservice.model.workers.Medic;
import com.medicalservice.service.MedicService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedicOptions extends JFrame {
        Connection connection;
        MedicService medicService;
        public MedicOptions(String title, Connection connection, MedicService medicService){
            super(title);
            this.connection = connection;
            this.medicService=medicService;
            JLabel label = new JLabel(title);
            JButton create = new JButton("Create medic");
            create.setBounds(150, 100, 150, 30);
            create.addActionListener(event-> createForm());
            JButton delete = new JButton("Delete medic");
            delete.setBounds(150, 150, 150, 30);
            delete.addActionListener(
                    event-> {
                        try {
                            deleteMedic();
                        } catch (SQLException | IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            JButton view = new JButton("View all medics");
            view.setBounds(150, 200, 150,30);
            view.addActionListener(event-> {
                try {
                    viewAll();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            });
            JButton viewByDep = new JButton("View all medics by department");
            viewByDep.setBounds(150, 250, 150,30);
            viewByDep.addActionListener(event -> {
                try {
                    viewAllByDepartment();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            });
            JButton update = new JButton("Update salary");
            update.setBounds(150, 300, 150,30);
            update.addActionListener(event -> {
                try {
                    update();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            });
            JButton back = new JButton("Back");
            back.setBounds(150, 350, 150,30);
           back.addActionListener(event-> {
               try {
                   goBack();
               } catch (SQLException | IOException e) {
                   e.printStackTrace();
               }
           });
            add(label);
            add(create);
            add(delete);
            add(update);
            add(view);
            add(viewByDep);
            add(back);
            setSize(500, 700);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);
            setVisible(true);
        }

    private void update() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide medic cnp: ");
        String salary= JOptionPane.showInputDialog("Please provide new salary: ");
        medicService.update(Integer.parseInt(cnp),Integer.parseInt(salary));
        JOptionPane.showMessageDialog(this,"Updated!");
    }

    private void goBack() throws IOException, SQLException {
            new ManageOptionsFrame(connection);
            setVisible(false);
    }

    private void viewAll() throws SQLException, IOException {
            List<Medic> medics = medicService.getAll();
            JOptionPane.showMessageDialog(this,medics.toString());
    }
    public void viewAllByDepartment() throws SQLException, IOException {
        String department= JOptionPane.showInputDialog("Please provide department: ");
        List<Medic> medics = medicService.getAllByDepartment(department);
        JOptionPane.showMessageDialog(this,medics.toString());

    }
    public void createForm(){
            new AddMedicForm(connection, medicService);
            setVisible(false); }
    private void deleteMedic() throws SQLException, IOException {
        String cnp= JOptionPane.showInputDialog("Please provide medic cnp: ");
        medicService.delete(Integer.parseInt(cnp));
        JOptionPane.showMessageDialog(this, "Deleted successfully!");
    }
}
