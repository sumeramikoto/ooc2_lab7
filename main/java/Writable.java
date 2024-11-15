import java.io.IOException;

public interface Writable {
    void addUserData(String filePath, String data) throws IOException;
}