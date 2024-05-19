package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Seller extends Employee {

    private final BigDecimal baseSalary = BigDecimal.valueOf(12000);
    private final BigDecimal benefit = BigDecimal.valueOf(0.3);
    private final BigDecimal annualBonus = BigDecimal.valueOf(1800);
    private final Map<String, BigDecimal> sales = new HashMap<>();

    public Seller(String name, LocalDate hiringDate) {
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

    public Map<String, BigDecimal> getSales() {
        return sales;
    }

    @Override
    public BigDecimal calculateSalary(int serviceTime) {
        BigDecimal bonusForServiceTime = annualBonus.multiply(BigDecimal.valueOf(serviceTime));
        return getBaseSalary().add(bonusForServiceTime);
    }

    public void addSale(String date, BigDecimal value) {
        sales.put(date, value);
    }

    public BigDecimal getTotal(int serviceTime, int month, int year) {
        BigDecimal salary = calculateSalary(serviceTime);
        return salary.add(calculateCommission(month, year));
    }

    public BigDecimal calculateCommission(int mes, int ano) {

        BigDecimal commission = BigDecimal.ZERO;
        String searchedDate = String.format("%02d/%d", mes, ano);

        for (Map.Entry<String, BigDecimal> entry : this.getSales().entrySet()) {
            String saleDate = entry.getKey();
            if (saleDate.equals(searchedDate)) {
                commission = commission.add(entry.getValue().multiply(getBenefit()));
            }
        }
        return commission;
    }

    @Override
    public String toString() {
        return "Seller{" +
                super.toString() +
                "baseSalary=" + baseSalary +
                ", benefit=" + benefit +
                ", annualBonus=" + annualBonus +
                ", sales=" + sales +
                "} " ;
    }
}
