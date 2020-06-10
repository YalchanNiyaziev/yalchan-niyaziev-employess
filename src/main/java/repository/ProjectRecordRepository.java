package main.java.repository;

import main.java.model.ProjectRecord;

import java.util.List;

public interface ProjectRecordRepository {
    void save(ProjectRecord epr);
    List<ProjectRecord> getAll();
}
