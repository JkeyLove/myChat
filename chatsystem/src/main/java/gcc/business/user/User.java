package gcc.business.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;

    public static List<String> userList = new ArrayList<>();

    public static List<String> getUserList(){
        return userList;
    }


}
