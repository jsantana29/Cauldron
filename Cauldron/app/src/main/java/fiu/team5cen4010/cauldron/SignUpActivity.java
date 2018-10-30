package fiu.team5cen4010.cauldron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class SignUpActivity extends AppCompatActivity implements Serializable {

    EditText usernameView;
    EditText passwordView;
    EditText confirmView;
    EditText pinView;

    UserDatabase users;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameView = (EditText) findViewById(R.id.usernameInput);
        passwordView = (EditText) findViewById(R.id.passwordInput);
        confirmView = (EditText) findViewById(R.id.confirmInput);
        pinView =  (EditText) findViewById(R.id.recoverypinInput);

        users = (UserDatabase) getIntent().getSerializableExtra("userdatabase");
    }

    public void onClickRegister(View view){
        Intent signup_tologin = new Intent(this, TitleActivity.class);

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        String confirm = confirmView.getText().toString();
        String pin = pinView.getText().toString();

        boolean isConfirmed = confirmPassword(password, confirm);
        boolean userinlist = userinList(username);

        if(username.trim().equals("")){
            usernameView.setError("Username is required");
        }
        else if(userinlist){
            usernameView.setError("Username is required");
            MessageBox("Username already in use!");
        }
        else if(password.trim().equals("")){
            passwordView.setError("Password is required");
        }
        else if(confirm.trim().equals("")) {
            confirmView.setError("Confirm Password is required");
        }
        else if(!isConfirmed){
            confirmView.setError("Confirm Password must match");
            MessageBox("Passwords Do Not Match!");
        }
        else if(pin.trim().equals("")){
            pinView.setError("Recovery PIN is required");

        }else if(pin.length() != 4 || !pin.matches("\\d\\d\\d\\d")){
            pinView.setError("Recovery PIN must be 4 digits");
            MessageBox("Recovery PIN not allowed!");
        }
        else if(isConfirmed){
            MessageBox("Registration Success!" + "\n" + "Login Now! Enjoy Cauldron!");
            addAccount(username, password,pin);
            signup_tologin.putExtra("databaseupdated",users);
            startActivity(signup_tologin);
        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void addAccount(String username, String password, String pin){
         user = new User(username, password, pin);
         users.addUser(user);
    }

    public boolean confirmPassword(String pass, String confirm){
        if(pass.equals(confirm)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean userinList(String key){
        boolean found = false;
        int i = 0;
        while(!found && i < users.getUserList().size())
        {
            User u = users.getUserList().get(i);
            if(u.getName().equals(key))
            {
                found = true;
            }
            else {i++;}
        }
        return found;
    }

}
