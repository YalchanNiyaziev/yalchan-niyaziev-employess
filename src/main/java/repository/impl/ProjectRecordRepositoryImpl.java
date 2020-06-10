package main.java.repository.impl;

import main.java.model.Employee;
import main.java.model.ProjectRecord;
import main.java.model.Project;
import main.java.repository.ProjectRecordRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectRecordRepositoryImpl implements ProjectRecordRepository {
    private static final String filename = "src/main/resources/employee_project_relation.txt";

    public ProjectRecordRepositoryImpl() {

    }

    @Override
    public void save(ProjectRecord epr) {
        File file = new File(filename);
        PrintStream fileWriter = null;
        try {
            fileWriter = new PrintStream(file, "UTF-8");
            fileWriter.println(
                    epr.getEmployee().getId() + ", "
                            + epr.getProject().getId() + ", "
                            + epr.getDateFrom() + ", "
                            + epr.getDateTo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }

    }

    @Override
    public List<ProjectRecord> getAll() {

        List<ProjectRecord> eprList = new ArrayList<>();
        File file = new File(filename);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file, "UTF-8");
            while (fileReader.hasNextLine()) {
                ProjectRecord epr = new ProjectRecord();
                String recordLine = fileReader.nextLine();
                String[] recordElements = recordLine.split("[ ,]+");
                int employeeId = Integer.parseInt(recordElements[0]);
                int projectId = Integer.parseInt(recordElements[1]);
                LocalDate dateFrom = LocalDate.parse(recordElements[2]);
                LocalDate dateTo = LocalDate.parse(recordElements[3]);
                epr.setEmployee(new Employee(employeeId, "Test" + employeeId));
                epr.setProject(new Project(projectId,"Project"+projectId));
                epr.setDateFrom(dateFrom);
                epr.setDateTo(dateTo);
                eprList.add(epr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
        }
        return eprList;
    }

}
