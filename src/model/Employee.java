package model;

public class Employee extends Person {
    private int id;
    private String email;

    public Employee(int id, String name, String email) {
        super(name);
        this.id = id;
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email!");
        }
        this.email = email;
    }
}
