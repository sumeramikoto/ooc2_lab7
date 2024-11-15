import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminUser extends User implements Writable, Modifiable, Renamable {
    public AdminUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void addUserData(String filePath, String data) throws IOException {
        FileUtility.appendToFile(filePath, data);
    }

    @Override
    public void modifyUserData(String filePath, String data) throws IOException {
        FileUtility.overwriteFile(filePath, data);
    }

    @Override
    public void renameFile(String oldName, String newName) {
        if (!FileUtility.renameFile(oldName, newName)) {
            System.out.println("Failed to rename file.");
        }
    }

    @Override
    public void performAction() throws IOException {
        System.out.println(this.getUsername() + " is an admin.");
        System.out.println("Select an option: 1. Read users file    2. Add user details    3. Update user information    4. Update user privilege");
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
            case 3:
                System.out.println("Enter user ID: ");
                String userId = scanner.nextLine();
                System.out.println("Enter new username: ");
                String updatedUsername = scanner.nextLine();
                updateUserInfo(userId, updatedUsername);
                break;
            case 4:
                System.out.println("Enter user ID: ");
                userId = scanner.nextLine();
                System.out.println("Enter new privilege: ");
                String updatedPrivilege = scanner.nextLine();
                updateUserPrivilege(userId, updatedPrivilege);
                break;
            default:
                System.out.println("Wrong input!");
                break;
        }
    }


    private void updateUserInfo(String userId, String updatedUsername) {
        try {
            File originalFile = new File("Users.csv");
            File tempFile = new File("TempUsers.csv");
            Scanner dataReader = new Scanner(originalFile);
            FileWriter writer = new FileWriter(tempFile);
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                String[] parts = fileData.split(",");
                if (parts.length == 5) {
                    if (parts[0].equals(userId)) {
                        parts[1] = updatedUsername;
                        String updatedData = parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + '\n';
                        writer.write(updatedData);
                        continue;
                    }
                    String newData = fileData + '\n';
                    writer.write(newData);
                }
            }
            writer.close();
            tempFile.renameTo(originalFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUserPrivilege(String userId, String updatedPrivilege) {
        try {
            File originalFile = new File("Users.csv");
            File tempFile = new File("TempUsers.csv");
            Scanner dataReader = new Scanner(originalFile);
            FileWriter writer = new FileWriter(tempFile);
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                String[] parts = fileData.split(",");
                if (parts.length == 5) {
                    if (parts[0].equals(userId)) {
                        parts[4] = updatedPrivilege;
                        String updatedData = parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + '\n';
                        writer.write(updatedData);
                        continue;
                    }
                    String newData = fileData + '\n';
                    writer.write(newData);
                }
            }
            writer.close();
            tempFile.renameTo(originalFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
