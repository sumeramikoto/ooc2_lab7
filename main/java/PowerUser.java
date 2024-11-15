import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PowerUser extends User implements Writable {
    public PowerUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void addUserData(String filePath, String data) throws IOException {
        FileUtility.appendToFile(filePath, data);
    }

    @Override
    public void performAction() throws IOException {
        System.out.println(this.getUsername() + " is a power user.");
        System.out.println("Select an option: 1. Read users file    2. Add user details");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Reading users file...");
                FileUtility.readFile("Users.csv");
                break;
            case 2:
                System.out.println("Enter user data (UserID,Username,Email,Password,UserType): ");
                String input = scanner.nextLine();
                FileUtility.appendToFile("Users.csv", input);
                break;
            default:
                System.out.println("Wrong input!");
                break;
        }
    }
}
