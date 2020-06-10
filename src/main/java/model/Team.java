package main.java.model;

public class Team {
    private Employee[] team = new Employee[2];
    private int togetherWorkDays;

    public Team() {
    }

    public Employee[] getTeam() {
        return team;
    }

    public void setTeam(Employee[] team) {
        this.team = team;
    }

    public int getTogetherWorkDays() {
        return togetherWorkDays;
    }

    public void setTogetherWorkDays(int togetherWorkDays) {
        this.togetherWorkDays = togetherWorkDays;
    }

    @Override
    public boolean equals(Object obj) {
        Team team = (Team) obj;
        if (    (team.getTeam()[0].getId() == this.team[0].getId())
                && (team.getTeam()[1].getId() == this.getTeam()[1].getId()) )
            return true;
        return false;
    }


}
