package org.example.tp2;

import java.util.List;

public interface ConsultationHistory {
    void scheduleAppointment(Appointment appointment);
    List<Appointment> getPatientAppointments(Patient patient);
}
