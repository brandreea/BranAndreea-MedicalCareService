package com.medicalservice.model.filemanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import com.google.gson.Gson;
public class FileUtilsWrite<T> {
    private static FileUtilsWrite instance;

    public static FileUtilsWrite getInstance(){
        if(instance==null){
            instance=new FileUtilsWrite();
        }
        return instance;
    }
    public void writeFile(List<T> elements, String path) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(elements);

        JsonNode jsonElements = objectMapper.readTree(jsonStr);
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

        jsonElements.elements().next().fieldNames()
                .forEachRemaining(f -> {csvSchemaBuilder.addColumn(f);} );

        System.out.println(jsonElements.fieldNames().toString());
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        System.out.println(jsonElements.toString());
        System.out.println(jsonElements.get(0).toString());
        jsonElements.fields().forEachRemaining(x->System.out.println(x.getKey()));

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(path), jsonElements);

    }

}
