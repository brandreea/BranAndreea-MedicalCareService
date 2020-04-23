package com.medicalservice.model.medication;

import com.medicalservice.model.workers.Medic;

import java.util.ArrayList;
import java.util.List;
public class Medication {
    String name;
    int quantity;
    int price;
    public Medication(){
        this.name="";
        this.quantity=0;
        this.price=0;
    }
    public Medication(String name, int quantity, int price)
    {
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
