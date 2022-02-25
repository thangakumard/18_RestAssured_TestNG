package entity;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String companyId;

    public String id() {
        return id;
    }

    public void id(String newName) {
        this.id = newName;
    }

    public String firstName() {
        return firstName;
    }

    public void firstName(String newName) {
        this.firstName = newName;
    }

    public String lastName() {
        return lastName;
    }

    public void lastName(String newName) {
        this.lastName = newName;
    }

    public String email() {
        return email;
    }

    public void email(String newName) {
        this.email = newName;
    }

    public int age() {
        return age;
    }

    public void age(int newName) {
        this.age = newName;
    }

    public String companyId() {
        return companyId;
    }

    public void companyId(String newName) {
        this.companyId = newName;
    }
}
