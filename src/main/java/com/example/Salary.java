package com.example;

public class Salary {
    private double baseSalary;
    private double bonus;

    public Salary(double baseSalary, double bonus) {
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    public double calculateYearlySalary() {
        return (baseSalary * 12) + bonus;
    }

    // Getters and Setters (if needed)
    public double getBaseSalary() { return baseSalary; }
    public double getBonus() { return bonus; }
}
