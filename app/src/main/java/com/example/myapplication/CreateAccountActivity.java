package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
}