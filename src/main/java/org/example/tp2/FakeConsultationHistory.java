package org.example.tp2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeConsultationHistory implements ConsultationHistory {
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void scheduleAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    @Override
    public List<Appointment> getPatientAppointments(Patient patient){
        return appointments.stream()
                .filter(c -> c.getPatient().equals(patient))
                .collect(Collectors.toList());
    }
}
