package crud;
import java.util.UUID;

public class Role extends RoleAbs{
    protected UUID id;
    protected String role;

    public String getRole(){
        return role;
    }
}