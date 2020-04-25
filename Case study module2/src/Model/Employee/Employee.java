package Model.Employee;

public class Employee extends Person implements Payroll {
    private String employeeCode;
    private String numberPhone;
    private String email;
    private double salary;
    private String basicSalary;
    private String overtime;

    public Employee() {
        super();
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
        return employeeCode;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
        return numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public double setSalary(double salary) {
        this.salary = Double.parseDouble(String.valueOf(Double.parseDouble(this.basicSalary)
                + Double.parseDouble(this.overtime) * 200000));
        return salary;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public String setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
        return basicSalary;
    }

    public String getOvertime() {
        return overtime;
    }

    public String setOvertime(String overtime) {
        this.overtime = overtime;
        return overtime;
    }

    public Employee(String name, String dateOfBirth, String gender, String address, String employeeCode, String numberPhone, String email, double salary, String basicSalary, String overtime) {
        super(name, dateOfBirth, address, gender);
        this.employeeCode = employeeCode;
        this.numberPhone = numberPhone;
        this.email = email;
        this.salary = salary;
        this.basicSalary = basicSalary;
        this.overtime = overtime;
    }

    @Override
    public void payRoll() {
        this.salary = Double.parseDouble(String.valueOf(Double.parseDouble(this.basicSalary) + Double.parseDouble(this.overtime) * 2000000));
    }
}
