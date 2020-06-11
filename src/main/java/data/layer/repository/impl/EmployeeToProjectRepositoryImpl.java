package main.java.data.layer.repository.impl;

import main.java.data.layer.model.Employee;
import main.java.data.layer.model.EmployeeToProject;
import main.java.data.layer.model.Project;
import main.java.data.layer.repository.EmployeeToProjectRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeToProjectRepositoryImpl implements EmployeeToProjectRepository {
    private static final String FILE_NAME = "src/main/resources/employee_project_relation.txt";
    private static final String ENCODING = "UTF-8";

    public EmployeeToProjectRepositoryImpl() {

    }

    @Override
    public void save(EmployeeToProject employeeToProject) {
        File file = new File(FILE_NAME);
        PrintStream fileWriter = null;
        try {
            fileWriter = new PrintStream(file, ENCODING);
            fileWriter.println(
                    employeeToProject.getEmployee().getId() + ", "
                            + employeeToProject.getProject().getId() + ", "
                            + employeeToProject.getDateFrom() + ", "
                            + employeeToProject.getDateTo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }

    }

    @Override
    public List<EmployeeToProject> getAllRecords() {

        List<EmployeeToProject> employeeToProjectList = new ArrayList<>();
        File file = new File(FILE_NAME);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file, ENCODING);
            while (fileReader.hasNextLine()) {
                EmployeeToProject employeeToProject = new EmployeeToProject();
                String recordLine = fileReader.nextLine();
                String[] recordElements = recordLine.split("[ ,]+");
                int employeeId = Integer.parseInt(recordElements[0]);
                int projectId = Integer.parseInt(recordElements[1]);
                LocalDate dateFrom = LocalDate.parse(recordElements[2]);
                LocalDate dateTo;
                if (recordElements[3].equals("null")) {
                    dateTo = LocalDate.now();
                } else {
                    dateTo = LocalDate.parse(recordElements[3]);
                }

                employeeToProject.setEmployee(new Employee("Employee" + employeeId, employeeId));
                employeeToProject.setProject(new Project(projectId, "Project" + projectId));
                employeeToProject.setDateFrom(dateFrom);
                employeeToProject.setDateTo(dateTo);

                employeeToProjectList.add(employeeToProject);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
        }
        return employeeToProjectList;
    }

}
