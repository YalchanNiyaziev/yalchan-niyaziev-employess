package main.java.repository.impl;

import main.java.model.EmployeeProjectRelation;
import main.java.repository.EmployeeProjectRelationRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EmployeeProjectRelationRepositoryImpl implements EmployeeProjectRelationRepository {
    @Override
    public void save(EmployeeProjectRelation epr) {


        File file = new File("src/main/resources/employee_project_relation.txt");
        PrintStream fileWriter=null;
        try {
            fileWriter = new PrintStream(file,"UTF-8");
            fileWriter.println(
                    epr.getEmployee().getId()+", "
                            +epr.getProject().getId()+", "
                            +epr.getDateFrom()+", "
                            +epr.getDateTo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }

    }

}
