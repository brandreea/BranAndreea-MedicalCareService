package com.medicalservice.model.consults;
import java.util.List;
public class Prescription {

    String medication;
    String otherRecommendations;
    int quantityPerTaking;
    int daysNumber;
    String momentsOfDay;
    int prescriptionId;

    public Prescription(String medication, String other_recommendations, int quantity_per_taking, int days_number, String moments_of_day) {
        this.medication = medication;
        this.otherRecommendations = other_recommendations;
        this.quantityPerTaking = quantity_per_taking;
        this.daysNumber = days_number;
        this.momentsOfDay = moments_of_day;
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
        return otherRecommendations;
    }

    public void setOther_recommendations(String other_recommendations) {
        this.otherRecommendations = other_recommendations;
    }

    public int getQuantityPerTaking() {
        return quantityPerTaking;
    }

    public void setQuantityPerTaking(int quantity_per_taking) {
        this.quantityPerTaking = quantity_per_taking;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getDaysNumber() {
        return this.daysNumber;
    }

    public void setDaysNumber(int days_number) {
        this.daysNumber = days_number;
    }

    public String getMomentsOfDay() {
        return momentsOfDay;
    }

    public void setMomentsOfDay(String moments_of_day) {
        this.momentsOfDay = moments_of_day;
    }

}
