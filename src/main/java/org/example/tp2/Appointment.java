package org.example.tp2;

public class Appointment {
    private Patient patient;
    private double value;
    private double coverage;

    public Appointment(Patient patient, double value, double coverage){
        this.patient = patient;
        this.value = value;
        this.coverage = coverage;
    }

    public Patient getPatient(){
        return patient;
    }

    public double getValue(){
        return value;
    }

    public double getCoverage(){
        return coverage;
    }


}
