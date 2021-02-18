package com.example.myapplication;

import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

//========================================================================
//This view will be for creating a new account.
//
//Traits: "Create Username" Field: Field to enter username.
//
//        "Create Password" Field: Field to enter password.
//
//        "Create" Button: To create a new user and add to user database.
//
//        "Cancel" Button: Return user to the Login screen(MainActivity)
//=======================================================================
public class CreateAccountActivity extends AppCompatActivity {

    EditText create_account_username;
    EditText create_account_password;
    Button create_account_signup;
    AppDatabase db;


        /*
        After Username and Password is entered, Validate Username doesn't
        exist in database. Afterwards return to login screen
*/
    // for(int i = 0; i < users.size(); i++){
//          if(users.get(i).getUsername().equals(userInputUserName) && users.get(i).getPassword() .equals(userInputPassword)){
//
//
//           Intent myIntent = new Intent(LoginActivity.this, UserActivity.class);
    User local_user;
    Thread T_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        create_account_username = findViewById(R.id.CAID);
        create_account_password = findViewById(R.id.CAPassword);
        create_account_signup = findViewById(R.id.CASignUp);
        local_user = User.getInstance(this.getApplicationContext());

        System.out.println(local_user.ID);
        System.out.println(create_account_username.getText().toString());
        System.out.println(create_account_password.getText().toString());


        create_account_signup.setOnClickListener(v1 -> {
            local_user.ID = create_account_username.getText().toString();
            local_user.password = create_account_password.getText().toString();
            try {
                signUp();
            } catch (Exception e) {
                //user already exist
            }
        });
    }

    public void signUp() {
        User_db_Login tmp_user = new User_db_Login();
        tmp_user.userID = local_user.ID;
        tmp_user.password = local_user.password;
        database_interface local_inst = local_user.db.userDao();
        AtomicBoolean login_status = new AtomicBoolean(false);
        Runnable check = () -> {
            if (local_inst.findByUserName(local_user.ID) == null) {
                local_inst.insert(tmp_user);
            }
            local_user.setID(create_account_username.getText().toString());
            local_user.setPassword(create_account_password.getText().toString());
            if (local_inst.login(local_user.getID(), local_user.getPassword()) != null) {
                login_status.set(true);
            }
        };
        T_database = new Thread(check);
        T_database.start();
        Looper.prepare();
        try {
            T_database.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}