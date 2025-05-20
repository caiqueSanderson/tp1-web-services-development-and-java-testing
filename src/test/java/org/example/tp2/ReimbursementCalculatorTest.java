package org.example.tp2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReimbursementCalculatorTest {
    private ReimbursementCalculator calculator;
    private Patient dummyPatient;
    private FakeConsultationHistory history;


    @BeforeEach
    void setUp() {
        calculator = new ReimbursementCalculator();

        dummyPatient = new Patient();
        history = new FakeConsultationHistory();
    }

    private Appointment createDefaultAppointment(double value, HealthInsurance plan){
        double coverage = plan.getCoveragePercent();
        return new Appointment(dummyPatient, value, coverage);
    }

    private void assertEqualsWithMargin(double expected, double actual){
        double margin = 0.01;
        assertTrue(Math.abs(expected - actual) <= margin,
                "Esperado: " + expected + ", mas foi: " + actual);
    }

//    --- Testes comentados da versão antiga do metodo calculate ---
//    Mudança na assinatura do metodo esta causando erro


//    @Test
//    @DisplayName("Deve calcular corretamente o reembolso de R$ 200 com 70% de cobertura")
//    void calculateReimbursementGivenValue200AndCoverage70_shouldReturn140(){
//        //ARRANGE
//       ReimbursementCalculator calculator = new ReimbursementCalculator();
//        Patient dummyPatient = new Patient();
//
//        double value = 200.0;
//        double coverage = 0.7;
//
//        //ACT
//        double reimbursement = calculator.calculate(value,coverage, dummyPatient);
//
//        //ASSERT
//        assertEquals(140.0, reimbursement);
//
//        //CLEANUP
//    }
//
//    //Casos de Borda
//    @Test
//    void coverageOfZeroPercent_shouldReturnZeroReimbursement(){
//        //ARRANGE
//        double value = 200.0;
//        double coverage = 0.0;
//        double expected = 0.0;
//
//        //ACT
//        double result = calculator.calculate(value,coverage,dummyPatient);
//
//        //ASSERT
//       assertEquals(expected,result);
//
//    }
//
//    @Test
//    void coverageOfHundredPercent_shouldReturnFullConsultationValue(){
//        //ARRANGE
//        double value = 200.0;
//        double coverage = 1.0;
//        double expected = value;
//
//        //ACT
//        double result = calculator.calculate(value,coverage,dummyPatient);
//
//        //ASSERT
//       assertEquals(expected,result);
//    }
//
//    @Test
//    void consultationValueZero_shouldReturnZeroReimbursement(){
//        //ARRANGE
//        double value = 0;
//        double coverage = 1.0;
//        double expected = 0.0;
//
//        //ACT
//        double result = calculator.calculate(value,coverage,dummyPatient);
//
//        //ASSERT
//        assertEquals(expected,result);
//    }
//
//    @Test
//    @DisplayName("Consulta de R$ 100 com plano de 50% deve retornar R$ 50 de reembolso")
//    void consultationWithPlan50Percent_shouldReturn50(){
//        //ARRANGE
//        HealthInsurance stubInsurance = new StubHealthInsurance50Percent();
//        Patient patient = new Patient();
//
//        //ACT
//        double result = calculator.calculate(100.0, stubInsurance, patient);
//
//        //ASSERT
//        assertEquals(50.0, result);
//    }

//    --- Teste Spy de Audit ---
//    @Test
//    void scheduleAppointment_shouldCallAudit(){
//        //ARRANGE
//        SpyAudit audit = new SpyAudit();
//
//        double value = 100;
//        HealthInsurance plan = new StubHealthInsurance50Percent();
//
//        //ACT
//        calculator.calculate(value, plan, dummyPatient);
//        audit.scheduleAppointment(dummyPatient, value);
//
//        //ASSERT
//        assertTrue(audit.wasCalled());
//        assertEquals(1, audit.getNumberCalls());
//    }

//    --- Teste Mockito (Mock) para autorização ---

    @Test
    void calculateReimbursement_shouldThrowException_whenNotAuthorized() {
        //ARRANGE
        HealthInsurance plan = new StubHealthInsurance50Percent();
        ReimbursementAuthorizer mockAuthorizer = mock(ReimbursementAuthorizer.class);
        when(mockAuthorizer.authorizer(dummyPatient, 200.0)).thenReturn(false);

        //ACT + ASSERT
        assertThrows(IllegalStateException.class, () -> {
            calculator.calculate(200.0, plan, dummyPatient, mockAuthorizer);
        });
    }

    @Test
    void calculateReimbursement_shouldReturnCorrectValue_whenAuthorized() {
        //ARRANGE
        HealthInsurance plan = new StubHealthInsurance50Percent();
        ReimbursementAuthorizer mockAuthorizer = mock(ReimbursementAuthorizer.class);
        when(mockAuthorizer.authorizer(dummyPatient, 100.0)).thenReturn(true);

        // ACT
        double result = calculator.calculate(100.0, plan, dummyPatient, mockAuthorizer);

        // ASSERT
        assertEqualsWithMargin(50.0,result);
    }

    @Test
    @DisplayName("Reembolso limitado a R$150")
    void reimbursementAboveCeiling_shouldBeLimitedTo150(){
        // ARRANGE
        HealthInsurance plan = new StubHealthInsurance80Percent();
        ReimbursementAuthorizer authorizer = mock(ReimbursementAuthorizer.class);
        when(authorizer.authorizer(dummyPatient, 1000.0)).thenReturn(true);

        // ACT
        double result = calculator.calculate(1000.0, plan, dummyPatient, authorizer);

        // ASSERT
        assertEqualsWithMargin(150.0,result);
    }

    @Test
    @DisplayName("Reembolso de R$280 com plano de 80% deve retornar R$80")
    void reimbursementBelowCeiling_shouldReturnCalculatedValue(){
        // ARRANGE
        double value = 100.0;

        HealthInsurance plan = new StubHealthInsurance80Percent();
        ReimbursementAuthorizer authorizer = mock(ReimbursementAuthorizer.class);
        when(authorizer.authorizer(dummyPatient, value)).thenReturn(true);

        // ACT
        double result = calculator.calculate(value, plan, dummyPatient, authorizer);

        // ASSERT
        assertEqualsWithMargin(80.0, result);
    }

    @Test
    void fullIntegrationTest_withStubMockAndHelper(){
        // Stub
        HealthInsurance stubInsurance = new HealthInsurance() {
            @Override
            public double getCoveragePercent() {
                return 0.9;
            }
        };

        // ARRANGE
        double consultationValue = 360.0;
        Appointment appointment = createDefaultAppointment(consultationValue, stubInsurance);

        // Mock
        ReimbursementAuthorizer mockAuthorizer = mock(ReimbursementAuthorizer.class);
        when(mockAuthorizer.authorizer(dummyPatient, consultationValue)).thenReturn(true);

        // ACT
        double reimbursement = calculator.calculate(
                appointment.getValue(),
                stubInsurance,
                appointment.getPatient(),
                mockAuthorizer
        );

        // ASSERT
        assertEqualsWithMargin(150, reimbursement);

        verify(mockAuthorizer, times(1)).authorizer(dummyPatient, consultationValue);
    }
}
