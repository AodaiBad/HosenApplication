package com.aga.hosen.hosenapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aga.hosen.hosenapplication.Data.Queue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQueueActivity extends AppCompatActivity {

    private EditText etName,etPhone,etAddress;
    private Button btnQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_queue);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etAddress=(EditText)findViewById(R.id.etAddress);

        btnQueue = (Button)findViewById(R.id.btnSave);

        btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        });
    }

    private void dataHandler() {
        String stName=etName.getText().toString();
        String stPhone=etPhone.getText().toString();
        String stAddress=etAddress.getText().toString();

        Queue q = new Queue();
        q.setName(stName);
        q.setPhone(stPhone);
        q.setAddress(stAddress);


        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email=user.getEmail();
        email=email.replace('.','*');
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        q.setEmail(email);
        String key=reference.child("mylist").push().getKey();
        q.setKeyId(key);
        reference.child("mylist").child(key).setValue(q).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AddQueueActivity.this, "Add Queue Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(AddQueueActivity.this, "Add Queue Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}