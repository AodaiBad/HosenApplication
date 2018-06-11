package com.aga.hosen.hosenapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPass;
    private Button btnLogin;
    private Button btnSignUp;
    private Button btnmanager;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnmanager = (Button)findViewById(R.id.btnmanager);
        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnmanager.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

    }

    private void dataHandler() {
        String email = etEmail.getText().toString();
        String passw = etPass.getText().toString();
        signIn(email, passw);
    }

    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LogInActivity.this, "signIn Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LogInActivity.this, "signIn failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    public void onClick(View view) {
        if (view == btnLogin) {
            dataHandler();
        }

        if (view== btnSignUp){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }

        if (view==btnmanager){
            Intent intent2 = new Intent(this,ListOfQueues.class);
            startActivity(intent2);
        }
    }
}
