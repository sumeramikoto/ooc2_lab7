import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileHandler fileHandler = FileHandler.getInstance();
            fileHandler.loadData();

            User user = fileHandler.authenticate("user1", "pass");
            if (user != null) {
                System.out.println(user.getUsername() + " authenticated successfully.");
                user.performAction();
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
