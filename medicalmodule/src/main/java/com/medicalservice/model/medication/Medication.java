package com.medicalservice.model.medication;

import com.medicalservice.model.workers.Medic;

import java.util.ArrayList;
import java.util.List;
public class Medication {
    static long count= 0;
    String name;
    int quantity;
    int price;
//    long id;
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
//        count++;
//        this.id=count;
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

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
