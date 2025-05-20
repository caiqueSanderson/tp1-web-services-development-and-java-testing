package org.example.tp2;

public interface ReimbursementAuthorizer {
    boolean authorizer(Patient patient, double value);
}
