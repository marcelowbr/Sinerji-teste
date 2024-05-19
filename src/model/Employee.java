package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Employee {

    private String name;
    private LocalDate hiringDate;

    protected Employee(String name, LocalDate hiringDate) {
        this.name = name;
        this.hiringDate = hiringDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getFormattedHiringDate() {
        return hiringDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public boolean wasActive(int mes, int ano) {
        LocalDate searchedDate = LocalDate.of(ano, mes, 1);
        return (searchedDate.isAfter(hiringDate) || searchedDate.isEqual(hiringDate));
    }

    public int getYearsOfService(int mes, int ano) {
        LocalDate date = LocalDate.of(ano, mes, 1);
        return Period.between(hiringDate, date).getYears();
    }

    public abstract BigDecimal calculateSalary(int serviceTime);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return  "nome='" + name + '\'' +
                ", data de contratação=" + getFormattedHiringDate()
                + ", ";

    }


}
