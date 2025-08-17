public class Librarian extends User {
    private String employeeId;
    private String department;

    public Librarian(String userId, String name, String email, String phone, String employeeId, String department) {
        super(userId, name, email, phone);
        this.employeeId = employeeId;
        this.department = department;
    }

    @Override
    public String getUserType() {
        return "Librarian";
    }

    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
}
