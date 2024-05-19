package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Secretary extends Employee {

    private final BigDecimal baseSalary = BigDecimal.valueOf(7000);
    private final BigDecimal benefit = BigDecimal.valueOf(0.2);
    private final BigDecimal annualBonus = BigDecimal.valueOf(1000);

    public Secretary(String name, LocalDate hiringDate) {
        super(name, hiringDate);
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public BigDecimal getBenefit() {
        return benefit;
    }

    public BigDecimal getAnnualBonus() {
        return annualBonus;
    }

    @Override
    public BigDecimal calculateSalary(int serviceTime) {
        BigDecimal bonusForServiceTime = annualBonus.multiply(BigDecimal.valueOf(serviceTime));
        return getBaseSalary().add(bonusForServiceTime);
    }

    public BigDecimal calculateBenefit(int serviceTime) {
        if(serviceTime < 1) {
            return BigDecimal.ZERO;
        }
        return calculateSalary(serviceTime).multiply(benefit);
    }

    public BigDecimal getTotal(int serviceTime) {
        BigDecimal salary = calculateSalary(serviceTime);
        return salary.add(calculateBenefit(serviceTime));
    }

    @Override
    public String toString() {
        return "Secretary{" +
                super.toString() +
                "baseSalary=" + baseSalary +
                ", benefit=" + benefit +
                ", annualBonus=" + annualBonus +
                "} ";
    }
}
