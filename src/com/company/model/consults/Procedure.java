package com.company.model.consults;
import java.util.List;
public class Procedure {
    List<Long> doctor_CNP;
    String procedure_name;
    long procedure_price;
    static long count=0;
    long id;

    static {
        count = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //This constructor is used to add procedures in datebase
    public Procedure(List<Long> doctor_CNP, String procedure_name, long procedure_price) {
        this.doctor_CNP = doctor_CNP;
        this.procedure_name = procedure_name;
        this.procedure_price = procedure_price;
        count++;
        this.id=count;
    }
    //This constructor is used to add TYPES of procedures
    public Procedure(String speciality, String name, long price) {
        this.procedure_name=name;
        this.procedure_price=price;
        count++;
        this.id=count;
    }

    public List<Long> getDoctor_CNP() {
        return doctor_CNP;
    }

    public void setDoctor_CNP(List<Long> doctor_CNP) {
        this.doctor_CNP = doctor_CNP;
    }


    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public long getProcedure_price() {
        return procedure_price;
    }

    public void setProcedure_price(long procedure_price) {
        this.procedure_price = procedure_price;
    }
}
