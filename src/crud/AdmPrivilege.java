package crud;

import java.io.IOException;

public interface AdmPrivilege {
    void assignRoles(User user) throws IOException;
    void deleteUser(User user) throws IOException;
}
