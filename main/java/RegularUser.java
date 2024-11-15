import java.io.IOException;

public class RegularUser extends User {
    public RegularUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void performAction() throws IOException {
        System.out.println(this.getUsername() + " is a regular user.");
        System.out.println("Reading the users file...\n");
        FileUtility.readFile("Users.csv");
    }
}
