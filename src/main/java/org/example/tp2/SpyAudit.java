package org.example.tp2;

public class SpyAudit implements Audit {
    private boolean wasCalled = false;
    private int calls = 0;

    @Override
    public void scheduleAppointment(Patient patient, double value){
        wasCalled = true;
        calls++;
    }

    public boolean wasCalled(){
        return wasCalled;
    }

    public int getNumberCalls(){
        return calls;
    }
}
