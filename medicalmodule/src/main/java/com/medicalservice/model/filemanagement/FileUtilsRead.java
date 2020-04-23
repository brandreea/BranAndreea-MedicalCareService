package com.medicalservice.model.filemanagement;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
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
    public List<T> readFile(String filePath, T elem) throws IOException {

        CsvSchema objectSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        MappingIterator<T> genericClassType = csvMapper.readerFor(elem.getClass())
                .with(objectSchema)
                .readValues(new File(filePath));

        List<T> elementList= genericClassType.readAll();
        return elementList;
    }
}
