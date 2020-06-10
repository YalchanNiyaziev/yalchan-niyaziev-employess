package main.java;


import main.java.repository.impl.ProjectRecordRepositoryImpl;
import main.java.service.ProjectRecordService;

public class Main {
    public static void main(String[] args) {
        ProjectRecordService projectRecordService =
                new ProjectRecordService(new ProjectRecordRepositoryImpl());
        projectRecordService.getTheBest();
    }
}
