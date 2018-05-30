package dfoode.partnerapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;
    private String userId;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        editUserName = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationField();
            }
        });


    }

    private void validationField() {

        userId = editUserName.getText().toString();
        password = editPassword.getText().toString();

        if (userId.equals("") || userId.equals(null)) {
            Toast.makeText(LoginActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
        } else if (password.equals("") || password.equals(null)) {
            Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


    }

}
