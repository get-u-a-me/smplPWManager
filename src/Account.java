public class Account {
    private String platform;
    private String name;
    private String password;
    private String email;

    public Account(String platform, String name, String password, String email) {
        this.platform = platform;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getPlatform() {
        return platform;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "platform= " + platform + ", name= " + name + ", password= " + password + ", email= " + email;

    }
}