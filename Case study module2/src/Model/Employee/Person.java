package Model.Employee;

public class Person {
    private String name;
    private String dateOfBirth;
    private String address;
    private String gender;

    public Person() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person(String name, String dateOfBirth, String address, String gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String setAddress(String address) {
        this.address = address;
        return address;
    }
}
