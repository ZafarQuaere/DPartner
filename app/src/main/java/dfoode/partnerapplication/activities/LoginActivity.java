package dfoode.partnerapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import dfoode.partnerapplication.R;
import dfoode.partnerapplication.utils.Utils;


public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private RelativeLayout lytParent;
    private LinearLayout lytTop;
    private Button btnLogin;
    private String userId;
    private String password;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mContext = this;
        if (Utils.isLoggedIn(mContext)){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }else {
            initUI();
        }
    }

    private void initUI() {
        editUserName = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        lytParent = (RelativeLayout) findViewById(R.id.lytParent);
        lytTop = (LinearLayout) findViewById(R.id.lytTop);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validationField();
            }
        });
        lytParent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Utils.hideKeyboard(LoginActivity.this,lytTop);
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
            Utils.setLoggedIn(mContext,true);
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

    }

}
