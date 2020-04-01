package com.company.model.medication;

import java.util.ArrayList;
import java.util.List;
public class Medication {
    String name;
    int quantity;
    int price;
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
