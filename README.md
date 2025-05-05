# Teste de Performance 1 - Calculadora Científica em Java

**Instituição:** Instituto Infnet  
**Curso:** Graduação em Engenharia de Software  
**Disciplina:** Desenvolvimento de Serviços Web e Testes com Java – 25E2_3  
**Aluno:** Caique Sanderson de Sá Borges  
**Data de Entrega:** 5 de maio de 2025

---

## 📌 Descrição do Projeto

Este projeto tem como objetivo a aplicação prática de testes unitários em Java utilizando a biblioteca JUnit 5. A classe central testada é uma **Calculadora Científica**, que implementa operações matemáticas básicas e avançadas, incluindo soma, subtração, divisão, logaritmo, seno, entre outras.

Ao longo dos exercícios propostos, os conceitos de **boas práticas de testes**, **tratamento de exceções**, **reutilização de código de teste** e **análise de cobertura de código** são explorados.

---

## 🧪 Funcionalidades da Calculadora

A classe `ScientificCalculator` possui os seguintes métodos:

- `add(double a, double b)`
- `subtract(double a, double b)`
- `multiply(double a, double b)`
- `divide(double a, double b)`
- `power(double base, double exponent)`
- `squareRoot(double a)`
- `log(double a)`
- `sin(double degrees)`
- `cos(double degrees)`

---


A classe de testes cobre os seguintes cenários:

- Teste de sucesso para soma e subtração
- Teste com uso de `@BeforeEach` para setup de instância
- Teste de cenário de exceção: divisão por zero, raiz de número negativo
- Teste de funções trigonométricas e logaritmo
- Aplicação da estrutura: Arrange, Act, Assert
- Uso de `assertThrows` para validação de exceções

---

## 🧩 Ferramentas Utilizadas

- **Java 17+**
- **JUnit 5 (Jupiter)**
- **IntelliJ IDEA** como IDE
- **Git + GitHub** para versionamento

---

## 📈 Análise de Cobertura

A cobertura de código foi utilizada para identificar:

- Métodos ainda não testados
- Fluxos alternativos não cobertos (ex: exceções)
- Oportunidades de refatoração dos testes

**Métodos críticos com maior prioridade de teste:**
- `divide()`, `squareRoot()`, `log()`

---

## 💻 Execução

### Requisitos

- Java 17+
- Maven ou suporte a dependências via IntelliJ