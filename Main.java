package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> emplist = new ArrayList<Employee>();
        emplist.add(new Employee(101, "John", "FSE", 20000, "Male", 25, Arrays.asList("8302838381", "782102384")));
        emplist.add(new Employee(102, "James", "ADM", 21000, "Male", 26, Arrays.asList("8302838561", "735602384")));
        emplist.add(new Employee(103, "Jenny", "FSE", 19000, "Female", 25, Arrays.asList("8325838381", "782108954")));
        emplist.add(new Employee(104, "Jacob", "CIS", 22000, "Male", 26, Arrays.asList("8856838381", "785352384")));
        emplist.add(new Employee(105, "Megan", "SAP", 21000, "Female", 22, Arrays.asList("8336838381", "782186984")));
        emplist.add(new Employee(106, "Christe", "FSE", 20000, "Female", 23, Arrays.asList("8636838381", "782865384")));
        emplist.add(new Employee(107, "Justin", "ADM", 20000, "Male", 23, Arrays.asList("8302838681", "782108654")));

        //list of employees whose id between 101 and 105
        List<Employee> employees = emplist.stream().filter(e->e.getId()>=101 && e.getId()<=105).collect(Collectors.toList());
        System.out.println(employees);

        //list of employees whose name starts with j and sort them based on salary ascending order
        List<Employee> employees1 = emplist.stream().filter(e->e.getName().startsWith("J"))
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println(employees1);

        List<Employee> employees2 = emplist.stream().filter(e->e.getName().startsWith("J"))
                .sorted((e1, e2)->(int)(e1.getSalary()-e2.getSalary()))
                .collect(Collectors.toList());
        System.out.println(employees2);

        //list of unique department names
        List<String> employees3 = emplist.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
        System.out.println(employees3);

        //get all contacts of employees
        List<List<String>> contacts = emplist.stream().map(Employee::getContacts).collect(Collectors.toList());
        System.out.println(contacts);

        //get contacts into one list
        List<String> contactList =  emplist.stream().flatMap(e->e.getContacts().stream()).collect(Collectors.toList());
        System.out.println(contactList);

        //group employees by department
        Map<String, List<Employee>> employeeMap = emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toList()));
        System.out.println(employeeMap);

        //no of employees in each department
        Map<String, Long> mapcount = emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(mapcount);

        //department with max number of employees
        Map.Entry<String, Long> result = emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(result);

        //average salary of male and female employees
        Map<String, Double> avgList = emplist.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgList);

        //employee with highest salary in each department
        Map<String, Optional<Employee>> salarylist =  emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(salarylist);

        //employee with 2nd highest salary
        Employee employee = emplist.stream().sorted(Comparator.comparing(Employee::getSalary)).skip(1).findFirst().get();
        System.out.println(employee);
    }
}