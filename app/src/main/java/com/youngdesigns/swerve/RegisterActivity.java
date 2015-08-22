package com.youngdesigns.swerve;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import model.User;


public class RegisterActivity extends Activity {

    private EditText userNameField;
    private EditText passWordField;
    private EditText confirmPasswordField;
    private EditText secQuestionField;
    private EditText answerField;
    private EditText emailField;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private Button confirmButton;
    private ImageView infoButton;
    private EditText hometownField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


        userNameField = (EditText) findViewById(R.id.registerUserName);
        passWordField = (EditText) findViewById(R.id.registerPassword);
        confirmPasswordField = (EditText) findViewById(R.id.registerConfirmPassword);
        emailField = (EditText) findViewById(R.id.registerEmail);
        secQuestionField = (EditText) findViewById(R.id.registerQuestion);
        answerField = (EditText) findViewById(R.id.registerAnswer);
        hometownField = (EditText) findViewById(R.id.registerHometown);

        maleButton = (RadioButton) findViewById(R.id.male_radio_button);
        femaleButton = (RadioButton) findViewById(R.id.female_radio_button);

        confirmButton = (Button) findViewById(R.id.account_confirm_button);
        infoButton = (ImageView) findViewById(R.id.info_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInfo()) {
                    setupUser();
                }
            }
        });

    }

    private boolean validateInfo() {
        boolean isValid = false;

        //TODO:Database check for userName uniqueness

        if (emailField.getText().toString().contains("@") && (emailField.getText().toString().contains(".com") || emailField.getText().toString().contains(".net"))) {

            String pw = passWordField.getText().toString();
            if (pw.equals(confirmPasswordField.getText().toString()) && pw.length() > 7) {

                if (secQuestionField.getText().toString().length() != 0) {

                    if (answerField.getText().toString().length() != 0) {

                        if (maleButton.isSelected() || femaleButton.isSelected()) {

                            isValid = true;
                        }

                    }

                }

            }

        }

        //password checks are done in keylisteners, wont fire this method if they dont match or are too short etc.

        return isValid;
    }

    private void setupUser() {
        User current = new User();
        current.setName(userNameField.getText().toString());
        current.setEmail(emailField.getText().toString());
        current.setPassword(passWordField.getText().toString());
        current.setSecAnswer(answerField.getText().toString());
        current.setSecQuestion(secQuestionField.getText().toString());
        current.setHomeTown(hometownField.getText().toString());

        //TODO: Database call to add new user to the db
    }

}
