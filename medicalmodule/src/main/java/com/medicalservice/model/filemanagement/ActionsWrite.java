package com.medicalservice.model.filemanagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class ActionsWrite {
    private static ActionsWrite instance;
    private static String path="actions.csv";
    private ActionsWrite(){
        System.out.println("Created this instance");
    }
    public static ActionsWrite getInstance(){
        if(instance==null)
            instance=new ActionsWrite();
        return instance;
    }
    public void writeAction(String name, Timestamp time) throws IOException {
        FileWriter csv=new FileWriter(path,true);
        System.out.println("In writeactions");
        csv.append(name);
        csv.append(",");
        csv.append(time.toString());
        csv.append("\n");
        csv.flush();
        csv.close();
    }
}
