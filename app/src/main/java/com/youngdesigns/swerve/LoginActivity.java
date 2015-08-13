package com.youngdesigns.swerve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //Placeholder for before functionality is in place.
    public void loginClicked(View view) {
//        startActivity(new Intent(LoginActivity.this, NavActivity.class));
        if (checkLogin()) {
            startActivity(new Intent(LoginActivity.this, NavActivity.class));
        }
    }

    private boolean checkLogin() {
        return checkUserName();


    }

    private boolean checkUserName() {
        EditText nameField = (EditText) findViewById(R.id.userNameField);
        String text = nameField.getText().toString();
        return checkPassword(text);
    }

    private boolean checkPassword(String userName) {
//        EditText passwordField = (EditText) findViewById(R.id.passwordField);
//        Database.validatePassword(userName, passwordField.getText().toString());
        //check result;
        return true;
    }

    public void createNewAccount(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
