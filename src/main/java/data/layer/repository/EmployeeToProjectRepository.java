package main.java.data.layer.repository;

import main.java.data.layer.model.EmployeeToProject;

import java.util.List;

public interface EmployeeToProjectRepository {
    void save(EmployeeToProject employeeToProject);
    List<EmployeeToProject> getAllRecords();
}
