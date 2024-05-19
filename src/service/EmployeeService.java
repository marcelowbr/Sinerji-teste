package service;

import model.Employee;
import model.Manager;
import model.Secretary;
import model.Seller;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeService {

    public EmployeeService() {}

    public BigDecimal getTotalPaid(List<Employee> employees, int month, int year) {
        BigDecimal total = BigDecimal.ZERO;

        for(Employee emp : employees){
            if(emp.wasActive(month, year)){
                int yearsService = emp.getYearsOfService(month, year);
                total = total.add(emp.calculateSalary(yearsService));
            }
        }
        total = total.add(this.getTotalBenefit(employees, month, year));
        return total;
    }

    public BigDecimal getTotalSalary(List<Employee> employees, int month, int year) {
        BigDecimal total = BigDecimal.ZERO;
        for(Employee emp : employees){
            if(emp.wasActive(month, year)){
                int yearsService = emp.getYearsOfService(month, year);
                total = total.add(emp.calculateSalary(yearsService));
            }
        }
        return total;
    }

    public BigDecimal getTotalBenefit(List<Employee> employees, int month, int year) {
        BigDecimal totalBenefits = BigDecimal.ZERO;
        for(Employee emp : employees){
            if(emp instanceof Manager) continue;
            if(emp.wasActive(month, year)){
                int yearsService = emp.getYearsOfService(month, year);
                if(emp instanceof Seller seller) totalBenefits = totalBenefits.add(seller.calculateCommission(month, year));
                if(emp instanceof Secretary secretary ) totalBenefits = totalBenefits.add(secretary.calculateBenefit(yearsService));
            }
        }
        return totalBenefits;
    }

    public Employee findHighestPaidEmployee(List<Employee> employees, int month, int year) {
        Employee employee = null;
        BigDecimal highestValue = BigDecimal.ZERO;
        BigDecimal total;
        for (Employee emp : employees) {
            if (emp.wasActive(month, year)) {
                int yearsService = emp.getYearsOfService(month, year);

                if(emp instanceof Seller seller) total = seller.getTotal(yearsService, month, year);
                else if(emp instanceof Secretary secretary) total = secretary.getTotal(yearsService);
                else total = emp.calculateSalary(yearsService);

                if (total.compareTo(highestValue) > 0) {
                    highestValue = total;
                    employee = emp;
                }
            }
        }
        return employee;
    }

    public String findEmployeeMostBenefits(List<Employee> employees, int month, int year) {
        Employee employee = null;
        BigDecimal highestValue = BigDecimal.ZERO;

        for(Employee emp : employees){
            if(emp instanceof Manager) continue;
            if(emp.wasActive(month, year)){
                int yearsService = emp.getYearsOfService(month, year);
                BigDecimal total = BigDecimal.ZERO;

                if(emp instanceof Seller seller) total = total.add(seller.calculateCommission(month, year));
                if(emp instanceof Secretary secretary) total = total.add(secretary.calculateBenefit(yearsService));

                if (total.compareTo(highestValue) > 0) {
                    highestValue = total;
                    employee = emp;
                }
            }
        }
        return employee != null ? employee.getName() : null;
    }

    public Seller findTopSellingSeller(List<Seller> sellers, int month, int year) {
        Seller seller = null;
        BigDecimal highestValue = BigDecimal.ZERO;
        String date = String.format("%02d/%d", month, year);

        for (Seller sel: sellers) {
            if (sel.getSales().containsKey(date)) {
                BigDecimal sale = sel.getSales().get(date);
                if (sale.compareTo(highestValue) > 0) {
                    highestValue = sale;
                    seller = sel;
                }
            }
        }
        return seller;
    }
}
