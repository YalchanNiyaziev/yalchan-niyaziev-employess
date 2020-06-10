package main.java.model;

import java.util.Arrays;

public class Team {
    private Employee[] team = new Employee[2];
    private long togetherWorkDays;

    public Team() {
    }

    public Employee[] getTeam() {
        return team;
    }

    public void setTeam(Employee[] team) {
        this.team = team;
    }

    public long getTogetherWorkDays() {
        return togetherWorkDays;
    }

    public void setTogetherWorkDays(long togetherWorkDays) {
        this.togetherWorkDays = togetherWorkDays;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        if ((team.getTeam()[0].getId() == this.team[0].getId())
                && (team.getTeam()[1].getId() == this.getTeam()[1].getId()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(team);
    }
}
