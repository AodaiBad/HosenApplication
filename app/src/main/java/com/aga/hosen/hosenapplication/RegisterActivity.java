package com.aga.hosen.hosenapplication;

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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail;
    private EditText etPass;
    private Button btnSave;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPass=(EditText)findViewById(R.id.etPass);
        btnSave=(Button)findViewById(R.id.btnSave);
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        btnSave.setOnClickListener(this);
    }

    private void dataHandler() {
        String email = etEmail.getText().toString();
        String passw = etPass.getText().toString();

        createAccount(email,passw);
    }



    private void createAccount(String email,String passw){
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Authentication failed"+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });




    }

    @Override
    public void onClick(View view) {
        if (view==btnSave)
        {
            dataHandler();
        }

    }
}
