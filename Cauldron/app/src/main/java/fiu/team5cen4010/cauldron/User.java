package fiu.team5cen4010.cauldron;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;
    private String pin;


    public User(String name, String password, String pin){
        this.name = name;
        this.password = password;
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
