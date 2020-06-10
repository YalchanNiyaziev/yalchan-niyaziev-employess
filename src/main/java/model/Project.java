package main.java.model;

import java.util.Objects;

public class Project {
    private int id;
    private String name;

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        Project project = (Project) o;
       if(this.getId()==project.getId())
           return true;
       return false;
    }

}
