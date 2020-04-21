package com.medicalservice.model.filemanagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FileUtilsRead<T> {

    private static FileUtilsRead instance;

    public static FileUtilsRead getInstance(){
        if(instance==null){
            instance=new FileUtilsRead();
        }
        return instance;
    }
    public List<T> readFile(String filePath){
        List<T> elementList=new ArrayList<T>();
        ObjectMapper objectMapper = new ObjectMapper();

        return elementList;
    }
}
