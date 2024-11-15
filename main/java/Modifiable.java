import java.io.IOException;

public interface Modifiable {
    void modifyUserData(String filePath, String data) throws IOException;
}