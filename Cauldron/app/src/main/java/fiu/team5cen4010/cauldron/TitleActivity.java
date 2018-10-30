package fiu.team5cen4010.cauldron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class TitleActivity extends AppCompatActivity implements Serializable {

    UserDatabase users;
    EditText usernameLogin;
    EditText passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);

        if(getIntent().hasExtra("databaseupdated")){
            users = (UserDatabase) getIntent().getSerializableExtra("databaseupdated");
        }else{
            users = new UserDatabase();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public void onClickLogin(View view){

        Intent login = new Intent(this, MainActivity.class);

        String username = usernameLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        int id = indexofUser(username);

        if(username.trim().equals("")){
            usernameLogin.setError("Username is required");
        }else if(password.trim().equals("")){
            passwordLogin.setError("Password is required");
        }else if(userinList(username) && users.getUserList().get(id).getPassword().equals(password)){
            MessageBox("Login Success!\n"+"Welcome: <"+username+">");
            startActivity(login);
        }else {
            MessageBox("Login Failed: Account not found!");
        }

    }

    public void onClickSignUp(View view) {
        //Used to debug how many users are being added.
        //This display from login when we click 'SignUp' button.
        //MessageBox(getUsernames());

        Intent signup = new Intent(this, SignUpActivity.class);

        signup.putExtra("userdatabase", users);
        startActivity(signup);
    }

    public void onClickForgotPassword(View view) {
        Intent forgotpw = new Intent(this, ForgotPassword.class);
        forgotpw.putExtra("userdatabase", users);
        startActivity(forgotpw);
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    public int indexofUser(String key){
        boolean found = false;
        int i = 0;
        int index = 0;
        while(!found && i < users.getUserList().size())
        {
            User u = users.getUserList().get(i);
            if(u.getName().equals(key))
            {
                found = true;
                index = i;
            }
            else {i++;}
        }
        return index;
    }

    public String getUsernames(){
        int i = 0;
        String str = "";
        while(i < users.getUserList().size())
        {
            User u = users.getUserList().get(i);
            str = str + u.getName()+"\n";
            i++;

        }
        return str;
    }


}
