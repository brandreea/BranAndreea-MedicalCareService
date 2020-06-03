package com.medicalservice.gui;

import com.medicalservice.model.consults.Consult;
import com.medicalservice.service.ConsultService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsultOptions extends JFrame {
    Connection connection;
    ConsultService consultService;
    public ConsultOptions(ConsultService consultService, Connection connection){
        super("Consults");
        this.connection = connection;
        this.consultService = consultService;
        JButton create = new JButton("Create consult");
        create.setBounds(150, 100, 150, 30);
        create.addActionListener(event-> createForm());
        JButton delete = new JButton("Delete consult");
        delete.setBounds(150, 150, 150, 30);
        JButton view = new JButton("View all consults");
        view.setBounds(150, 200, 150,30);
        view.addActionListener(event-> {
            try {
                viewAll();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton viewConsultsByMedic = new JButton("View consults by medic");
        viewConsultsByMedic.setBounds(150, 250, 150,30);
        viewConsultsByMedic.addActionListener(event-> {
            try {
                viewAllByMedic();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton back = new JButton("Back");
        back.setBounds(150, 300, 150,30);
        back.addActionListener( event-> {
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
        add(viewConsultsByMedic);
        add(back);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void viewAllByMedic() throws SQLException, IOException {
        String doctorCNP= JOptionPane.showInputDialog("Please provide medic CNP: ");
        JOptionPane.showMessageDialog(this,consultService.getAllByMedic(Integer.parseInt(doctorCNP)).toString());
    }

    private void viewAll() throws SQLException, IOException {
        JOptionPane.showMessageDialog(this,consultService.getAll().toString());
    }

    private void createForm() {
        new AddConsultForm(connection,consultService);
        setVisible(false);
    }

    private void goBack() throws IOException, SQLException {
        new ManageOptionsFrame(connection);
        setVisible(false);

    }
//    private void deleteConsult() throws SQLException {
//        String cnp= JOptionPane.showInputDialog("Please provide patient cnp: ");
//        subscribedPatientService.delete(Integer.parseInt(cnp));
//        JOptionPane.showMessageDialog(this, "Deleted successfully!");
//    }
}
