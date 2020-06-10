package main.java.service;

import com.sun.source.util.Trees;
import main.java.model.Employee;
import main.java.model.ProjectRecord;
import main.java.model.Team;
import main.java.repository.ProjectRecordRepository;

import java.time.LocalDate;
import java.util.*;

public class ProjectRecordService {
    private ProjectRecordRepository projectRecordRepository;

    public ProjectRecordService(ProjectRecordRepository projectRecordRepository) {
        this.projectRecordRepository = projectRecordRepository;
    }

    public Team getTheBest() {
        Set<Team> teams = getTeams();
        return null;
    }

    private Set<Team> getTeams() {
        List<ProjectRecord> records = this.projectRecordRepository.getAll();
        Set<Team> teamSet = new HashSet<>();
        for (int i = 0; i < records.size(); i++) {
            for (int j = i + 1; j < records.size() - 1; j++) {
                if (records.get(i).getProject()
                        .equals(records.get(j).getProject())) {
                    if(
                            (records.get(i).getDateFrom().isAfter(records.get(j).getDateTo()))
                            || (records.get(j).getDateFrom().isAfter(records.get(i).getDateTo()))
                    ){
                        continue;
                    }
                    else {
                        Team team = createTeam(records.get(i),records.get(j));
                        if(!teamSet.contains(team)) {
                            teamSet.add(team);
                        }
                        else {
                            for (Team t:teamSet){

                                if(t.equals(team)){
                                    t.setTogetherWorkDays(t.getTogetherWorkDays()+team.getTogetherWorkDays());
                                }
                            }
                        }
                    }
                }
            }
        }
        return teamSet;
    }

    private Team createTeam(ProjectRecord ... records) {
        Team team = new Team();
        Employee[] employees = new Employee[2];
        employees[0]=records[0].getEmployee();
        employees[1]=records[1].getEmployee();
        int workedDays = computeWorkDays(records);
        team.setTeam(employees);
        team.setTogetherWorkDays(workedDays);
        return team;


    }

    private int computeWorkDays(ProjectRecord ... records) {
        int countWorkDays=5;

        return countWorkDays;
    }

}
