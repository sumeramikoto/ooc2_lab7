import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private FileHandler instance = FileHandler.getInstance();

    @Test
    public void authenticationFailureTest() throws IOException {
        instance.loadData();
        User user = instance.authenticate("dummy", "dummy");
        assertNull(user);
    }

    @Test
    public void authenticationSuccessTest() throws IOException {
        instance.loadData();
        User user = instance.authenticate("pasta", "pass");
        assertNotNull(user);
    }

    @Test
    public void adminAuthenticationTest() throws IOException {
        instance.loadData();
        User user = instance.authenticate("admindude", "pass");
        boolean adminStatus = instance.isAdmin(user.getUsername());
        assertTrue(adminStatus);
    }

    @Test
    public void adminFailure() throws IOException {
        instance.loadData();
        User user = instance.authenticate("pasta", "pass");
        boolean adminStatus = instance.isAdmin(user.getUsername());
        assertFalse(adminStatus);
    }
}
