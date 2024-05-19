import model.Employee;
import model.Manager;
import model.Secretary;
import model.Seller;
import service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();

        Secretary sec = new Secretary("Jorge Carvalho", LocalDate.of(2018, 1, 1));
        Secretary sec2 = new Secretary("Maria Souza", LocalDate.of(2015, 12, 1));
        Seller seller = new Seller("Ana Silva", LocalDate.of(2021, 12, 1));
        Seller seller2 = new Seller("João Mendes", LocalDate.of(2021, 12, 1));
        Manager manager = new Manager("Juliana Alves", LocalDate.of(2017, 7, 1));
        Manager manager2 = new Manager("Bento Albino", LocalDate.of(2014, 3, 1));

        seller.addSale("12/2021", BigDecimal.valueOf(5200.00));
        seller.addSale("01/2022", BigDecimal.valueOf(4000.00));
        seller.addSale("02/2022", BigDecimal.valueOf(4200.00));
        seller.addSale("03/2022", BigDecimal.valueOf(5850.00));
        seller.addSale("04/2022", BigDecimal.valueOf(7000.00));

        seller2.addSale("12/2021", BigDecimal.valueOf(3400.00));
        seller2.addSale("01/2022", BigDecimal.valueOf(7700.00));
        seller2.addSale("02/2022", BigDecimal.valueOf(5000.00));
        seller2.addSale("03/2022", BigDecimal.valueOf(5900.00));
        seller2.addSale("04/2022", BigDecimal.valueOf(6500.00));
        
        List<Employee> employees = List.of(sec, sec2, seller, seller2, manager, manager2);
        List<Seller> sellers = List.of(seller, seller2);
        
        int month = 5;
        int year = 2024;

        BigDecimal totalPaid = employeeService.getTotalPaid(employees, month, year);
        System.out.println("Total pago aos funcionários: " + totalPaid);

        BigDecimal totalSalary = employeeService.getTotalSalary(employees, month, year);
        System.out.println("Total pago em salários: " + totalSalary);

        BigDecimal totalBenefit = employeeService.getTotalBenefit(employees, month, year);
        if (totalBenefit.compareTo(BigDecimal.valueOf(0)) > 0)
            System.out.println("Total pago em benefícios: " + totalBenefit);
        else System.out.println("Nenhum funcionário recebeu benefício no mês especificado.");

        Employee receivedTheMost = employeeService.findHighestPaidEmployee(employees, month, year);
        if(receivedTheMost != null) System.out.println("Funcionário que mais recebeu no mês: " + receivedTheMost);
        else System.out.println("Nenhum funcionário estava ativo no mês especificado.");

        String receivedMostBenefit = employeeService.findEmployeeMostBenefits(employees, month, year);
        if(receivedMostBenefit != null) System.out.println("Funcionário que mais recebeu em benefícios: " + receivedMostBenefit);
        else System.out.println("Nenhum funcionário recebeu benefício no mês especificado.");

        Seller soldTheMost = employeeService.findTopSellingSeller(sellers, month, year);
        if (soldTheMost != null) System.out.println("Vendedor que mais vendeu: " + soldTheMost.getName());
        else System.out.println("Nenhum vendedor vendeu no mês especificado.");
    }
}
