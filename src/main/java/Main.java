package main.java;


import main.java.data.layer.repository.impl.EmployeeToProjectRepositoryImpl;
import main.java.business.layer.service.EmployeeToProjectService;

public class Main {
    public static void main(String[] args) {
        EmployeeToProjectService employeeToProjectService =
                new EmployeeToProjectService(new EmployeeToProjectRepositoryImpl());
        System.out.println("The Best Team is:"+employeeToProjectService.getTheBestTeam());
    }
}
