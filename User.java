public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;

    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    
    public abstract String getUserType();

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Email: %s | Phone: %s | Type: %s",
                userId, name, email, phone, getUserType());
    }
}
