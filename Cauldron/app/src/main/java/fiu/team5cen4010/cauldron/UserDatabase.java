package fiu.team5cen4010.cauldron;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDatabase implements Serializable {
    ArrayList<User> userList = new ArrayList<>();

    public UserDatabase() {
        userList.add(new User("mehdi", "pass123","2018"));
        userList.add(new User("ale", "pass123","2018"));
        userList.add(new User("jean", "pass123","2018"));
        userList.add(new User("carl", "pass123","2018"));
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user){
        userList.add(user);
    }

}

