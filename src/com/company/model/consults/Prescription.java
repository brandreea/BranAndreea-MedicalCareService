package com.company.model.consults;
import java.util.List;
public class Prescription {
    String medication;
    String other_recommendations;
    int quantity_per_taking;
    int days_number;
    List<String> moments_of_day;

    public Prescription(String medication, String other_recommendations, int quantity_per_taking, int days_number, List<String> moments_of_day) {
        this.medication = medication;
        this.other_recommendations = other_recommendations;
        this.quantity_per_taking = quantity_per_taking;
        this.days_number = days_number;
        this.moments_of_day = moments_of_day;
    }

    public Prescription() {
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getOther_recommendations() {
        return other_recommendations;
    }

    public void setOther_recommendations(String other_recommendations) {
        this.other_recommendations = other_recommendations;
    }

    public int getQuantity_per_taking() {
        return quantity_per_taking;
    }

    public void setQuantity_per_taking(int quantity_per_taking) {
        this.quantity_per_taking = quantity_per_taking;
    }

    public int getDays_number() {
        return days_number;
    }

    public void setDays_number(int days_number) {
        this.days_number = days_number;
    }

    public List<String> getMoments_of_day() {
        return moments_of_day;
    }

    public void setMoments_of_day(List<String> moments_of_day) {
        this.moments_of_day = moments_of_day;
    }
    public void addMomentsOfDay(String moment)
    {
        this.moments_of_day.add(moment);
    }
}
