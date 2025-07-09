package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaryTest {

    @Test
    public void testBasicYearlySalary() {
        Salary salary = new Salary(3000, 1200); // monthly salary, bonus
        double expected = (3000 * 12) + 1200;
        assertEquals(expected, salary.calculateYearlySalary(), 0.01);
    }

    @Test
    public void testZeroBonus() {
        Salary salary = new Salary(4000, 0);
        assertEquals(48000, salary.calculateYearlySalary(), 0.01);
    }

    @Test
    public void testZeroSalary() {
        Salary salary = new Salary(0, 500);
        assertEquals(500, salary.calculateYearlySalary(), 0.01);
    }

    @Test
    public void testNegativeSalary() {
        Salary salary = new Salary(-1000, 0);
        double result = salary.calculateYearlySalary();
        assertTrue(result < 0);
    }
}
