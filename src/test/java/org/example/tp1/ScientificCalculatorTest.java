package org.example.tp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScientificCalculatorTest {

    private ScientificCalculator calculator;
    private double numerator;

    @BeforeEach
    void setUp(){
        calculator = new ScientificCalculator();
    }

    // ---------------------------
    // Testes de operações básicas
    // ---------------------------

    @Test
    @DisplayName("Soma de dois números positivos")
    void addTwoPositiveNumbers_shouldReturnTheirSum(){
        ScientificCalculator calculator = new ScientificCalculator();
        double actual = calculator.add(5,5);
        Assertions.assertEquals(10,actual);
    }

    @Test
    @DisplayName("Subtração entre dois números iguais")
    void subtractTwoEqualNumbers_shouldReturnTheNumberZero(){
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

    // ---------------------------
    // Testes de funções matemáticas
    // ---------------------------

    @Test
    @DisplayName("Raiz quadrada de número positivo")
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
    @DisplayName("Logaritmo de e deve retornar 1")
    void logOfEulerNumber_shouldReturnOne(){
        //ARRANGE
        double a = Math.E;
        double expected = 1.0;

        //ACT
        double actual = calculator.log(a);

        //ASSERT
        Assertions.assertEquals(expected, actual, 0.0001);
    }

    @Test
    @DisplayName("Seno de 30 graus deve retornar 0.5")
    void calculateSinValue_ShouldReturnPositiveNumber(){
        //ARRANGE
        double a = 30.0;
        double expected = 0.5;

        //ACT
        double actual = calculator.sin(a);

        //ASSERT
        Assertions.assertEquals(expected, actual,0.0001);
    }

    // ---------------------------
    // Testes de tratamento de exceções
    // ---------------------------

    @Test
    @DisplayName("Raiz quadrada de número negativo deve lançar exceção")
    void squareRootOfNegativeNumber_shouldReturnException(){
        //ARRANGE
        double a = -64.0;

        //ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot(a);
        });
    }

    @Test
    @DisplayName("Divisão por zero deve lançar exceção")
    void divideByZero_shouldThrowIllegalArgumentException() {
        //ARRANGE
        double numerator = 10.0;
        double denominator = 0.0;

        //ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(numerator, denominator);
        });
    }
}
