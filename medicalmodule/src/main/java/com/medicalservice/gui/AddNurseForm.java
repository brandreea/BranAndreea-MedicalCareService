package com.medicalservice.gui;

import com.medicalservice.model.workers.Nurse;
import com.medicalservice.service.MedicService;
import com.medicalservice.service.NurseService;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class AddNurseForm extends JFrame{
    Connection connection;
    NurseService nurseService;
    public AddNurseForm(Connection connection, NurseService medicService){
        super("Add a new nurse");
        this.connection=connection;
        this.nurseService = medicService;

        JLabel nameLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(0, 50, 200, 30);
        name.setBounds(60, 50, 200, 30);

        JLabel cnpLabel = new JLabel("CNP: " );
        JTextField cnp = new JTextField();
        cnpLabel.setBounds(0, 100, 200, 30);
        cnp.setBounds(60, 100, 200, 30);

        JLabel departmentLabel = new JLabel("Department: " );
        JTextField department = new JTextField();
        departmentLabel.setBounds(0, 150, 200, 30);
        department.setBounds(60, 150, 200, 30);

        JLabel salaryLabel = new JLabel("Salary: " );
        JTextField salary = new JTextField();
        salaryLabel.setBounds(0, 200, 200, 30);
        salary.setBounds(60, 200, 200, 30);

        JButton submit = new JButton("Save");
        submit.setBounds(30, 250, 150,30);
        submit.addActionListener(event->
        {
            try {
                addNurse(name.getText(),
                Integer.parseInt(cnp.getText()),
                department.getText(),
                Integer.parseInt(salary.getText()));
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(200, 250, 150,30);
        cancel.addActionListener(event->goBack());

        add(nameLabel);
        add(name);
        add(cnpLabel);
        add(cnp);
        add(departmentLabel);
        add(department);
        add(salaryLabel);
        add(salary);
        add(submit);
        add(cancel);
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void addNurse(String name, int cnp, String department, int salary) throws SQLException, IOException {
        nurseService.save(new Nurse(name,new Date(), salary,cnp,department));
        JOptionPane.showMessageDialog(this,"Added nurse succesfully!");
    }


    private void goBack() {
        new NursesOptions( connection,nurseService);
        setVisible(false);
    }
}
