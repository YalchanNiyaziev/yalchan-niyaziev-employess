package main.java;

import main.java.model.Employee;
import main.java.model.EmployeeProjectRelation;
import main.java.model.Project;
import main.java.repository.EmployeeProjectRelationRepository;
import main.java.repository.impl.EmployeeProjectRelationRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EmployeeProjectRelationRepositoryImpl epr = new EmployeeProjectRelationRepositoryImpl();
        epr.save(new EmployeeProjectRelation(new Employee(4,"Ivan"),new Project(33,"test"), LocalDate.now(),LocalDate.now()));

    }
}
