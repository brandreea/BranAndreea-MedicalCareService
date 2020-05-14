package com.medicalservice.Main;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.medicalservice.model.consults.Consult;
import com.medicalservice.model.filemanagement.FileUtilsRead;
import com.medicalservice.model.filemanagement.FileUtilsWrite;
import com.medicalservice.model.medication.Medication;
import com.medicalservice.model.patients.SubscribedPatient;
import com.medicalservice.model.workers.Medic;
import com.medicalservice.model.workers.Nurse;
import com.medicalservice.service.*;
import com.medicalservice.service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
      Service s=Service.getInstance();
      NurseService nurseService=NurseService.getInstance();
      MedicationService medicationService=MedicationService.getInstance();
      FileUtilsWrite write=FileUtilsWrite.getInstance();
      FileUtilsRead read=FileUtilsRead.getInstance();
      //1

      s.addMedic(new Medic("Test Doctor","Infectious diseases", 1234667));
      //2
      nurseService.addNurse(new Nurse("Test Nurse",345588,"Infectious Diseases"));

      //4
      s.addSubscribedPatient(999999, "Test Patient", 56788,40);
      //3
      List<Consult> retrievedConsults=read.readFile("consults.csv",new Consult(0,0));
      for(int i=0;i<retrievedConsults.size();i++)
        s.addConsult(retrievedConsults.get(i));


      //5
      List<Long> someMedics=new ArrayList<Long>();
      someMedics.add((long) 234567);
      someMedics.add((long)111111);
      s.addProcedure(someMedics, "electroencephalogram",2000);

      //6
      List<Medic> m= s.viewMedicsByDepartment("neurology");
      for(Medic medic: m)
        System.out.print(medic.getName()+" ");

      //7
      System.out.println("By consult");
      List<Consult>c=s.viewConsultsByMedic(234567);
      for(int i=0;i<c.size();i++)
        System.out.println("Consult to"+c.get(i).getDoctor_CNP()+" "+c.get(i).getPatient_CNP()+"id:"+c.get(i).getId());
      System.out.println();

      //8
        medicationService.addMedication(new Medication("Test medication",100,25));

      //9
        System.out.println("\n\nBy procedure:");
        m=s.viewMedicProcedure(1);
        for(Medic medic: m)
            System.out.println(medic.getName());
        //11
        System.out.println("\n\nPatients:");
        List<SubscribedPatient> p= s.viewSubscribedPatients();
        for(SubscribedPatient pat:p)
            System.out.println(pat.getName());
        //10
        System.out.println("\n\nUpdate Consult");
        String symp="hearing sounds where they are not, headache";
        String diagnose = "self stimulation in the brain of an unstimulated area; sane.";
        s.updateConsult(1,symp, diagnose, "none", 1);

        //12

        //13
        medicationService.addQuantity("Nurofen", 100);

        //14
        medicationService.deleteQuantity("Nurofen", 10);

    }
}
