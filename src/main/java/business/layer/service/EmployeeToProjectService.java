package main.java.business.layer.service;

import main.java.data.layer.model.Employee;
import main.java.data.layer.model.Project;
import main.java.data.layer.model.EmployeeToProject;
import main.java.data.layer.model.Team;
import main.java.data.layer.repository.EmployeeToProjectRepository;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class EmployeeToProjectService {
    private EmployeeToProjectRepository employeeToProjectRepository;
    private List<EmployeeToProject> employeeToProjectList;
    private HashMap<String, Team> resultMap;
    private int recordCount;

    public EmployeeToProjectService(EmployeeToProjectRepository employeeToProjectRepository) {
        this.employeeToProjectRepository = employeeToProjectRepository;
        this.employeeToProjectList = employeeToProjectRepository.getAllRecords();
        this.recordCount = employeeToProjectList.size();
    }

    public Team getTheBestTeam() {
        this.resultMap = getAllTeams();
        return resultMap.values().stream().max(Comparator.comparing(Team::getTogetherWorkDays)).get();
    }

    private HashMap<String, Team> getAllTeams() {
        HashMap<String, Team> teamHashMap = new HashMap<>();
        EmployeeToProject firstEmployeeToProject = null;
        EmployeeToProject secondEmployeeToProject = null;
        for (int i = 0; i < recordCount; i++) {
            for (int j = i + 1; j < recordCount; j++) {
                firstEmployeeToProject = employeeToProjectList.get(i);
                secondEmployeeToProject = employeeToProjectList.get(j);
                if (isSameProject(firstEmployeeToProject, secondEmployeeToProject)) {
                    if (hasNotWorkTogether(firstEmployeeToProject, secondEmployeeToProject)) {
                        continue;
                    } else {
                        Team team = createTeam(firstEmployeeToProject, secondEmployeeToProject);
                        int firstTeammateId = firstEmployeeToProject.getEmployee().getId();
                        int secondTeammateId = secondEmployeeToProject.getEmployee().getId();
                        String teamIdVersionOne = firstTeammateId + "-" + secondTeammateId;
                        String teamIdVersionTwo = secondTeammateId + "-" + firstTeammateId;
                        if (!teamHashMap.containsKey(teamIdVersionOne)
                                && !teamHashMap.containsKey(teamIdVersionTwo)) {
                            teamHashMap.put(teamIdVersionOne, team);
                        } else {
                            updateTeam(teamHashMap, team, teamIdVersionOne, teamIdVersionTwo);
                        }
                    }
                }
            }
        }
        return teamHashMap;

    }

    private void updateTeam(HashMap<String, Team> teamHashMap, Team team, String key1, String key2) {

        Team existedTeam = findFirstNotNull(teamHashMap.get(key1), teamHashMap.get(key2));
        existedTeam.setTogetherWorkDays(existedTeam.getTogetherWorkDays() + team.getTogetherWorkDays());
        team.getProjects().forEach(p -> existedTeam.getProjects().add(p));
    }

    private Team findFirstNotNull(Team... teams) {
        if (teams != null) {
            Team[] teamArray = teams;
            int size = teams.length;
            for (int i = 0; i < size; i++) {
                Team team = teamArray[i];
                if (team != null)
                    return team;
            }
        }
        return null;
    }


    private boolean isSameProject(EmployeeToProject record1, EmployeeToProject record2) {
        boolean isSame = false;
        if (record1.getProject().equals(record2.getProject()))
            isSame = true;
        return isSame;
    }

    private boolean hasNotWorkTogether(EmployeeToProject record1, EmployeeToProject record2) {
        if (record1.getDateFrom().isAfter(record2.getDateTo())
                || record2.getDateFrom().isAfter(record1.getDateTo()))
            return true;
        return false;
    }

    private Team createTeam(EmployeeToProject record1, EmployeeToProject record2) {
        Team team = new Team();
        Employee[] employees = new Employee[2];
        Project project = record1.getProject();
        employees[0] = record1.getEmployee();
        employees[1] = record2.getEmployee();
        long workedDays = computeWorkDays(record1, record2);
        team.setTeam(employees);
        team.setTogetherWorkDays(workedDays);
        team.getProjects().add(project);
        return team;
    }

    private long computeWorkDays(EmployeeToProject... records) {
        EmployeeToProject employeeOne = records[0];
        EmployeeToProject employeeTwo = records[1];
        LocalDate partnershipStartDate;
        LocalDate partnershipEndDate;
        if (employeeOne.getDateFrom().isAfter(employeeTwo.getDateFrom())) {
            partnershipStartDate = employeeOne.getDateFrom();
        } else {
            partnershipStartDate = employeeTwo.getDateFrom();
        }
        if (employeeOne.getDateTo().isBefore(employeeTwo.getDateTo())) {
            partnershipEndDate = employeeOne.getDateTo();
        } else {
            partnershipEndDate = employeeTwo.getDateTo();
        }
        long countWorkDays = DAYS.between(partnershipStartDate, partnershipEndDate);
        return countWorkDays;
    }

}
