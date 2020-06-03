package com.medicalservice.gui;

import com.medicalservice.model.medication.Medication;
import com.medicalservice.service.MedicationService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddMedicationForm extends JFrame {
    Connection connection;
    MedicationService medicationService;
    public AddMedicationForm(Connection connection, MedicationService medicationService){
        super("Add medication");
        this.connection = connection;
        this.medicationService = medicationService;
        JLabel nameLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(0, 50, 200, 30);
        name.setBounds(60, 50, 200, 30);

        JLabel quantityLabel = new JLabel("Quantity: " );
        JTextField quantity = new JTextField();
        quantityLabel.setBounds(0, 100, 200, 30);
        quantity.setBounds(60, 100, 200, 30);

        JLabel priceLabel = new JLabel("Price: " );
        JTextField price = new JTextField();
        priceLabel.setBounds(0, 150, 200, 30);
        price.setBounds(60, 150, 200, 30);

        JButton submit = new JButton("Save");
        submit.setBounds(30, 250, 150,30);
        submit.addActionListener(event->
        {
            try {
                addMedication(name.getText(),
                Integer.parseInt(quantity.getText()),
                Integer.parseInt(price.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        JButton cancel = new JButton("Back");
        cancel.setBounds(200, 250, 150,30);
        cancel.addActionListener(event->goBack());

        add(nameLabel);
        add(name);
        add(quantityLabel);
        add(quantity);
        add(priceLabel);
        add(price);
        add(submit);
        add(cancel);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void addMedication(String name, int quantity, int price) throws IOException, SQLException {
        medicationService.save(new Medication(name,quantity,price));
        JOptionPane.showMessageDialog(this,"Added medication succesfully!");
    }

    private void goBack() {
        new MedicationOptions(connection,medicationService);
        setVisible(false);
    }
}
