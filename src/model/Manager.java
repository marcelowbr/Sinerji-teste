package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Manager extends Employee {

    private final BigDecimal baseSalary = BigDecimal.valueOf(20000);
    private final BigDecimal annualBonus = BigDecimal.valueOf(3000);

    public Manager(String name, LocalDate hiringDate) {
        super(name, hiringDate);
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public BigDecimal getAnnualBonus() {
        return annualBonus;
    }

    @Override
    public BigDecimal calculateSalary(int serviceTime) {
        BigDecimal bonusForServiceTime = annualBonus.multiply(BigDecimal.valueOf(serviceTime));
        return getBaseSalary().add(bonusForServiceTime);
    }

    @Override
    public String toString() {
        return "Manager{" +
                super.toString()+
                "baseSalary=" + baseSalary +
                ", annualBonus=" + annualBonus +
                "} ";
    }
}
