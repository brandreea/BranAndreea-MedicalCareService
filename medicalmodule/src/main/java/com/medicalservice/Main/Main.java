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

      FileUtilsWrite write=FileUtilsWrite.getInstance();
      FileUtilsRead read=FileUtilsRead.getInstance();

      List<Medic> retrievedMedics=read.readFile("medics.csv", new Medic("","",123));
      for(int i=0;i<retrievedMedics.size();i++)
        s.addMedic(retrievedMedics.get(i));

      //2
      List<Nurse> retrievedNurses=read.readFile("nurses.csv", new Nurse("",0,""));
      for(int i=0;i<retrievedNurses.size();i++)
        s.addNurse(retrievedNurses.get(i));
      s.addNurse(new Nurse("Ionescu Theodor",3455,"Infectious Diseases"));
      //write.("nurse.csv",new Nurse("",0,""));
//      n.add(new Nurse("Dana Ionescu",3457,"Neurology"));
//      write.writeFile(n,"nurses.csv");

//      s.addNurse("Ana Popescu", 3456, "Infectious Diseases");
      s.addSubscribedPatient(9999, "Bill Gates", 56789,50);
      //3
      List<Consult> retrievedConsults=read.readFile("consults.csv",new Consult(0,0));
      for(int i=0;i<retrievedConsults.size();i++)
        s.addConsult(retrievedConsults.get(i));

      //4


     //5
      List<Long> someMedics=new ArrayList<Long>();
      someMedics.add((long) 234567);
      someMedics.add((long)111111);
      s.addProcedure(someMedics, "electroencephalogram",2000);

      //6
      List<Medication> retrievedMedication=read.readFile("medication.csv",new Medication("",0,0));
      for(int i=0;i<retrievedMedication.size();i++)
        s.addMedication(retrievedMedication.get(i));
      //7
      List<Medic> m= s.viewMedicsByDepartment("neurology");
      for(Medic medic: m)
          System.out.print(medic.getName()+" ");
      //8
        System.out.println("\n\nBy procedure:");
        m=s.viewMedicProcedure(1);
        for(Medic medic: m)
            System.out.println(medic.getName());
        //9
        System.out.println("\n\nPatients:");
        List<SubscribedPatient> p= s.viewSubscribedPatients();
        for(SubscribedPatient pat:p)
            System.out.println(pat.getName());
        //10
        System.out.println("\n\nUpdate Consult");
        String symp="hearing sounds where they are not, headache";


        String diagnose = "self stimulation in the brain of an unstimulated area; sane.";
        s.updateConsult(1,symp, diagnose, "none", 1);


        //test fileRead
//      write.writeFile(m,"medics.csv");
//      write.writeFile(p,"subscribedPatients.csv");
//      FileUtilsWrite writeNurse=FileUtilsWrite.getInstance
      //List<Medic> retrievedMedics=read.readFile("medics.csv", new Medic("","",123));
//      for(int i=0;i<retrievedMedics.size();i++)
//        System.out.println(retrievedMedics.get(i).toString());
//      System.out.println(retrievedMedics);

    }
}
