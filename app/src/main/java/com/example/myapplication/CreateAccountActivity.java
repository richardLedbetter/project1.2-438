package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

//====================================================================================================
//CreateAccountActivity class: Will allow user to create/add account to database of users.
//====================================================================================================
public class CreateAccountActivity extends AppCompatActivity {

    //=========================================
    //Variables
    //=========================================
    EditText create_account_username;
    EditText create_account_password;
    Button create_account_signup;
    Button returnButton;
    AppDatabase db;

    User local_user;
    Thread T_database;
    //=========================================

    //==========================================================================================
    //onCreate
    //==========================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //-----------------------------------------------------------
        //Components
        //-----------------------------------------------------------
        create_account_username = findViewById(R.id.CAID);
        create_account_password = findViewById(R.id.CAPassword);
        create_account_signup = findViewById(R.id.CASignUp);
        local_user = User.getInstance(this.getApplicationContext());
        returnButton = (Button)findViewById(R.id.CAReturnHome);
        //-----------------------------------------------------------

        System.out.println(local_user.ID);
        System.out.println(create_account_username.getText().toString());
        System.out.println(create_account_password.getText().toString());

        //----------------------------------------------------------------------------
        //create_account_signup.setOnClickListener: Will attempt to call signUp.
        //----------------------------------------------------------------------------
        create_account_signup.setOnClickListener(v1 -> {
            local_user.ID = create_account_username.getText().toString();
            local_user.password = create_account_password.getText().toString();
            try {
                signUp();   //attempt to call signUp...
            } catch (Exception e) { //...if it fails, do nothing; user already exists.
            }
        });
        //----------------------------------------------------------------------------

        //----------------------------------------------------------------------------------
        //returnButton.setOnClickListene: Will take user back to login activity.
        //----------------------------------------------------------------------------------
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //----------------------------------------------------------------------------------
    }
    //==========================================================================================

    //=======================================================================================
    //signUp(): Will take info provided by user to create a new account
    //=======================================================================================
    public void signUp() {

        //------------------------------------------------------------
        //Variables
        //------------------------------------------------------------
        User_db_Login tmp_user = new User_db_Login();
        tmp_user.userID = local_user.ID;
        tmp_user.password = local_user.password;
        database_interface local_inst = local_user.db.userDao();
        AtomicBoolean login_status = new AtomicBoolean(false);
        //------------------------------------------------------------

        //------------------------------------------------------------------------------------
        //Check if user already exists.
        //------------------------------------------------------------------------------------
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
        //------------------------------------------------------------------------------------

        T_database = new Thread(check);
        T_database.start();
        Looper.prepare();
        try {
            T_database.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //=======================================================================================
}
//====================================================================================================