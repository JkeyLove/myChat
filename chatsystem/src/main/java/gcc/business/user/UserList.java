package gcc.business.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserList {


    public static List<User> userList = new ArrayList<>();

    public static List<User> getUserList(){
        return userList;
    }

    public static List<String> getUsernameList(){

        return getUserList().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    //根据姓名找User
    public static User getUserByUsername(String username){
        for (User user :getUserList()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }

        return null;
    }

}
