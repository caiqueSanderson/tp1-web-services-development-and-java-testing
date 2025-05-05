package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScientificCalculatorTest {

    private ScientificCalculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new ScientificCalculator();
    }

    @Test
    @DisplayName("Teste do metodo add")
    void addTwoPositiveNumbers_shouldReturnTheirSum(){
        ScientificCalculator calculator = new ScientificCalculator();
        double actual = calculator.add(5,5);
        Assertions.assertEquals(10,actual);
    }

    @Test
    @DisplayName("Teste do metodo subtract")
    void subtractTwoNumbersTen_shouldReturnTheNumberZero(){
        //ARRANGE ou SETUP
        ScientificCalculator calculator = new ScientificCalculator();
        double numberA = 10;
        double numberB = 10;
        double expected = 0;

        //ACT ou EXECUTION
        double actual = calculator.subtract(numberA,numberB);

        //ASSERT ou ASSERTION
        Assertions.assertEquals(expected,actual);

        //CLEANUP ou TEARDOWN
    }

    @Test
    @DisplayName("Teste do metodo Square Root de um numero positivo")
    void squareRootOfPositiveNumber_shouldReturnCorrectValue(){
        //ARRANGE
        double a = 9.0;
        double expected = 3;

        //ACT
        double actual = calculator.squareRoot(a);

        //ASSERT
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Teste do method Square Root de um numero negativo")
    void testSquareRootNegative(){
        //ARRANGE
        double a = -64.0;

        //ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot(a);
        });
    }

    @Test
    @DisplayName("Teste do method divide ao tentar dividir por zero")
    void testDivideByZero(){
        //ARRANGE
        double numerator = 10.0;
        double denominator = 0.0;

        //ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(numerator, denominator);
        });
    }

    @Test
    @DisplayName("Teste do method log com numero positivo")
    void calculateLogValue_ShouldReturnPositiveNumber(){
        //ARRANGE
        double a = Math.E;
        double expected = 1.0;

        //ACT
        double actual = calculator.log(a);

        //ASSERT
        Assertions.assertEquals(expected, actual, 0.0001);
    }

    @Test
    @DisplayName("Teste do method sin com numero positivo")
    void calculateSinValue_ShouldReturnPositiveNumber(){
        //ARRANGE
        double a = 30.0;
        double expected = 0.5;

        //ACT
        double actual = calculator.sin(a);

        //ASSERT
        Assertions.assertEquals(expected, actual,0.0001);
    }
}
