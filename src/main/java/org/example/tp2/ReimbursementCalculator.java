package org.example.tp2;

public class ReimbursementCalculator {
    public double calculate(double consultationValue, HealthInsurance insurance, Patient patient, ReimbursementAuthorizer authorizer){
        if(!authorizer.authorizer(patient, consultationValue)){
            throw new IllegalStateException("Consulta não autorizada para reembolso");
        }

        double coveragePercentage = insurance.getCoveragePercent();

        if (consultationValue < 0 || coveragePercentage < 0 || coveragePercentage > 1) {
            throw new IllegalArgumentException("Valores inválidos para cálculo de reembolso");
        }

        double reimbursement = consultationValue * coveragePercentage;
        if(reimbursement > 150){
            reimbursement= 150;
        }

        return reimbursement;
    }
}
