package main.java.data.layer.model;

public class Employee extends Person{
    private int id;

    public Employee() {
    }

    public Employee(String name, int id) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
