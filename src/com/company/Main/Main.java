package com.company.Main;
import com.company.model.patients.SubscribedPatient;
import com.company.model.workers.Medic;
import com.company.service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
      Service s=Service.getInstance();
      //1
      s.addMedic("Oliver Sacks","neurology", 234567);
      s.addMedic("Alexander Luria","neurology", 111111);

      //2
      s.addNurse("Ana Popescu", 3456, "Infectious Diseases");

      //3
      s.addConsult(234567, 9999);

      //4
      s.addSubscribedPatient(9999, "Bill Gates", 56789,50);

     //5
      List<Long> someMedics=new ArrayList<Long>();
      someMedics.add((long) 234567);
      someMedics.add((long)111111);
      s.addProcedure(someMedics, "electroencephalogram",2000);

      //6
      s.addMedication("Nurofen", 100, 25);
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
        List<String> symp=new ArrayList<String>();
        symp.add("headache");
        symp.add("hearing sounds where they are not");
        List<String> diagnose = new ArrayList<String>();
        diagnose.add("self stimulation in the brain of an unstimulated area; sane.");
        s.updateConsult(1,symp, diagnose, "none", "rest", 0, 30,null);
    }
}
