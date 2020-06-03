package com.medicalservice.main;
import com.medicalservice.db.DbConnectionUtils;

import com.medicalservice.gui.ManageOptionsFrame;
import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.medication.Medication;

import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.service.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static PrescriptionService prescriptionService;
    private static ProcedureService procedureService;
    private static UnsubscribedPatientService unsubscribedPatientService;

    public static void main(String[] args) throws IOException, SQLException {
      connection = DbConnectionUtils.getDBConnection();
      prescriptionService = PrescriptionService.getInstance();
      procedureService = ProcedureService.getInstance();
      unsubscribedPatientService = UnsubscribedPatientService.getInstance();
      new ManageOptionsFrame(connection);
//      DbConnectionUtils.closeDBConnection(connection);

    }

//
}
