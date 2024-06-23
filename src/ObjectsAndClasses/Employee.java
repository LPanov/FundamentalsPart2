package ObjectsAndClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Employee {
    private  String name;
    private  double salary;
    private String position;
    private  String department;
    private String email;
    private int age;

    public Employee(String name, double salary, String position, String department) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = "n/a";
        this.age = -1;
    }
    public Employee(String name, double salary, String position, String department, String email, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = age;
    }
    public Employee(String name, double salary, String position, String department, String email) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = -1;
    }
    public Employee(String name, double salary, String position, String department, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = "n/a";
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return name + ' ' + String.format("%.2f", salary) + " " + email + ' ' + age;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            Employee employee;
            if(s.length == 4) {
                employee = new Employee(s[0], Double.parseDouble(s[1]), s[2], s[3]);
                employees.add(employee);
            }
            else if (s.length == 6) {
                employee = new Employee(s[0], Double.parseDouble(s[1]), s[2], s[3], s[4], Integer.parseInt(s[5]));
                employees.add(employee);
            }
            else if (s[4].length() > 2 && s.length == 5) {
                employee = new Employee(s[0], Double.parseDouble(s[1]), s[2], s[3], s[4]);
                employees.add(employee);
            }
            else if (s[4].length() <= 2 && s.length == 5) {
                employee = new Employee(s[0], Double.parseDouble(s[1]), s[2], s[3], Integer.parseInt(s[4]));
                employees.add(employee);
            }

        }
        double max = 0;
        double sum = 0;
        String dep = "";
        for (int i = 0; i < employees.size(); i++) {
            sum = employees.get(i).getSalary();
            for(int j = i+1; j < employees.size(); j++) {
                if (employees.get(j).getDepartment().equals(employees.get(i).getDepartment())) {
                    sum += employees.get(j).getSalary();
                }
            }

            if (sum > max) {
                max = sum;
                dep = employees.get(i).getDepartment();
            }
        }

        employees.sort(Comparator.comparing(Employee::getSalary).reversed());

        System.out.println("Highest Average Salary: " + dep);
        for (Employee employee:employees) {
            if (employee.getDepartment().equals(dep)){
                System.out.println(employee);
            }
        }

        /*System.out.println();
        System.out.println(max + " " + dep);*/
    }
}
