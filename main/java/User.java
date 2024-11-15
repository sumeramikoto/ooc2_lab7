import java.io.IOException;

abstract class User implements Readable {
    private String userId;
    private String username;
    private String email;
    private String password;

    public User(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public void readData(String filePath) throws IOException {
        FileUtility.readFile(filePath);
    }

    abstract public void performAction() throws IOException;
}
