import java.io.*;
import java.util.HashMap;

public class FileHandler {
    private static FileHandler instance;

    private final HashMap<String, User> allUserMap = new HashMap<>();
    private final HashMap<String, User> regularUserMap = new HashMap<>();
    private final HashMap<String, User> powerUserMap = new HashMap<>();
    private final HashMap<String, AdminUser> adminMap = new HashMap<>();

    private FileHandler() {}

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    public void loadData() throws IOException {
        BufferedReader userReader = new BufferedReader(new FileReader("Users.csv"));
        String line;
        while ((line = userReader.readLine()) != null) {
            String[] details = line.split(",");
            switch (details[4]) {
                case "Admin":
                    AdminUser admin = new AdminUser(details[0], details[1], details[2], details[3]);
                    allUserMap.put(details[1], admin);
                    adminMap.put(details[1], admin); // Also add admin users to the adminMap
                    break;
                case "Power":
                    PowerUser powerUser = new PowerUser(details[0], details[1], details[2], details[3]);
                    allUserMap.put(details[1], powerUser);
                    powerUserMap.put(details[1], powerUser);
                    break;
                case "Regular":
                    RegularUser regularUser = new RegularUser(details[0], details[1], details[2], details[3]);
                    allUserMap.put(details[1], regularUser);
                    regularUserMap.put(details[1], regularUser);
                    break;
            }
        }
        userReader.close();

        BufferedReader adminReader = new BufferedReader(new FileReader("Admin.csv"));
        while ((line = adminReader.readLine()) != null) {
            String[] details = line.split(",");
            AdminUser admin = new AdminUser(details[0], details[1], details[2], details[3]);
            adminMap.put(details[1], admin);
        }
        adminReader.close();
    }

    public User authenticate(String username, String password) {
        User user = allUserMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean isAdmin(String username) {
        return adminMap.containsKey(username);
    }
}
